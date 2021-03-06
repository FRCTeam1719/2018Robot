package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Move to position using nested PID
 * 
 * @author Duncan, implemented in 2018 Robot by Gus
 *
 */
public class MoveToPosition extends Command implements PIDSource, PIDOutput {
    
    /**
     * Class used to store a PID output and apply it as a setpoint for a PID input
     * 
     * @author Duncan
     */
    private class PIDHelper implements PIDSource, PIDOutput {
        private double val = 0;
        
        @Override
        public void pidWrite(double output) {
            val = output;
        }
        
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}
        
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
        
        @Override
        public double pidGet() {
            return position.getHeading() - pathAngle - val;
        }
    }
    
    private static final double SQ_TOLERANCE = 82.0D;
    private static final double SPD = 0.5D;
    private static final double MAX_ANGLE_TOLERANCE = 45.0D;
    private static final double MIN_ANGLE_TOLERANCE = 18.0D;
    
    private double initX = 0;
    private double initY = 0;
    
    private Position position;
    private Drive drive;
    
    private double desiredX = 0;
    private final double parX;
    private double desiredY = 0;
    private final double parY;
    private double errX = 0;
    private double errY = 0;
    private double pathAngle = 0;
    private double distOffPath = 0;
    private double rotSpd = 0;
    private PIDHelper pidhelper;
    private PIDController desiredHeadingController;
    private PIDController rotateController;
    private PIDController rotateControllerStill;
    
    private boolean init = false;
    private boolean turning = false;
    private final boolean absolute;
    private final boolean doHardTurns;
   
    /**
     * Creates a new move-to-position command.
     * 
     * @param _desiredX - the X-coordinate of the target position
     * @param _desiredY - the Y-coordinate of the target position
     * @param _position - the subsystem which tracks the current position
     * @param _drive - the subsystem which manages the drive train
     * @param _absolute - whether the target coordinates are absolute or relative to the position when the command is initiated
     * @param _doHardTurns - {@code true} if the robot should turn sharply if significantly off course
     */
    public MoveToPosition(double _desiredX, double _desiredY, Position _position, Drive _drive, boolean _absolute,
            boolean _doHardTurns) {
        absolute = _absolute;
        parX = desiredX = _desiredX;
        parY = desiredY = _desiredY;
        doHardTurns = _doHardTurns;
        position = _position;
        drive = _drive;
        
        requires((Subsystem) drive);
        
        pidhelper = new PIDHelper();
        desiredHeadingController = new PIDController(5, 0, 0, this, pidhelper);
        rotateController = new PIDController(0.008, 0.0002, 0.001, pidhelper, this);
        rotateControllerStill = new PIDController(0, 0, 0, pidhelper, this);
    }
    
    @Override
    protected void initialize() {
        SmartDashboard.putBoolean("MTP Running", true);
        initX = position.getX();
        initY = position.getY();

        if (!absolute) {
            desiredX = parX + initX;
            desiredY = parY + initY;
        }
        
        pathAngle = Math.toDegrees(Math.atan2(desiredX - initX, desiredY - initY));
        
        desiredHeadingController.setPID(SmartDashboard.getNumber("MoveToPos K[0][P]", 5.0),
                SmartDashboard.getNumber("MoveToPos K[0][I]", 0), SmartDashboard.getNumber("MoveToPos K[0][D]", 0));
        
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                SmartDashboard.getNumber("MoveToPos K[1][I]", 0), SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
        
        rotateControllerStill.setPID(SmartDashboard.getNumber("TurnToHeading K[P]", 0.02),
                SmartDashboard.getNumber("TurnToHeading K[I]", 0.006),
                SmartDashboard.getNumber("TurnToHeading K[D]", 0.1));
        
        desiredHeadingController.setSetpoint(0);
        desiredHeadingController.setOutputRange(-90.0D, 90.0D);
        
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D, 180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        
        rotateControllerStill.setSetpoint(0);
        rotateControllerStill.setInputRange(-180.0D, 180.0D);
        rotateControllerStill.setContinuous();
        rotateControllerStill.setOutputRange(-1.0D, 1.0D);
        
        desiredHeadingController.enable();
        
        rotateController.enable();
        SmartDashboard.putData("Desired Heading", desiredHeadingController);
        SmartDashboard.putData("Rotation", rotateController);
        SmartDashboard.putData("Rotation Still", rotateControllerStill);
    }
    
    @Override
    protected void execute() {
        SmartDashboard.putNumber("Right_rate", drive.getEncoderR().getRate());
        SmartDashboard.putNumber("Left_rate", drive.getEncoderL().getRate());
        
        
        rotateControllerStill = (PIDController) SmartDashboard.getData("Desired Heading");
        desiredHeadingController = (PIDController) SmartDashboard.getData("Rotation");
        rotateController = (PIDController) SmartDashboard.getData("Rotation Still");
        
        
        if (init) {
            initialize();
            init = false;
        }
        errX = desiredX - position.getX();
        errY = desiredY - position.getY();
        double atanXY = Math.toDegrees(Math.atan2(errX, errY));
        /* Hack -- check the angular heading compared to where the target is */
        if (doHardTurns) {
            double head_m_atxy = (atanXY - position.getHeading()) % 360.0D;
            if (head_m_atxy > 180.0D) {
                head_m_atxy -= 360.0D;
            }
            if (head_m_atxy < -180.0D) {
                head_m_atxy += 360.0D;
            }
            if (Math.abs(head_m_atxy) > MAX_ANGLE_TOLERANCE) { /*
                                                                * Are we so far off target that the 1094 algorithm won't
                                                                * work well?
                                                                */
                turning = true;
                desiredHeadingController.disable();
                rotateController.disable();
                rotateControllerStill.enable();
                pidhelper.pidWrite(0.0D);
            } else if (Math.abs(head_m_atxy) < MIN_ANGLE_TOLERANCE) { /*
                                                                       * Have we gotten to almost directly the direction
                                                                       * we wish to go?
                                                                       */
                turning = false;
                desiredHeadingController.enable();
                rotateController.enable();
                rotateControllerStill.disable();
            }
        }
        if (doHardTurns && turning) { /* Just use one PID loop to turn in place */
            // drive.shift(true);
            pathAngle = atanXY;
            
            rotSpd = rotateControllerStill.get();
            System.out.println("Turning to heading " + pathAngle + "; power " + rotSpd + " ctrl "
                    + rotateControllerStill.get() + "FROM" + rotateControllerStill.getError() + "K[P]="
                    + rotateControllerStill.getP() + "enabled=" + rotateControllerStill.isEnabled());
            
            drive.tankDrive(rotSpd, -rotSpd);
        } else { /* Use 1094 algorithm */
            // drive.shift(false);
            double offPathAngle = Math.atan2(errX, errY) - Math.toRadians(pathAngle);
            
            distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);
            
            System.out.println("Following path : power " + rotSpd + "Rotator " + rotateController.get());
            
            drive.arcadeDrive(SPD, -rotSpd);
        }
        
        SmartDashboard.putNumber("MTP e\u27c2", distOffPath);
        SmartDashboard.putNumber("MTP Desired angle", pidhelper.val);
        SmartDashboard.putNumber("MTP current angle", position.getHeading() - pathAngle);
        System.out.println("MTP current angle: " + position.getHeading());
    }
    
    @Override
    protected boolean isFinished() {
        return (errX * errX + errY * errY) < SQ_TOLERANCE;
    }
    
    @Override
    protected void end() {
        SmartDashboard.putBoolean("MTP Running", false);
    }
    
    @Override
    protected void interrupted() {}
    
    @Override
    public void pidWrite(double output) {
        rotSpd = output;   
    }
    
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}
    
    @Override
    public PIDSourceType getPIDSourceType() {
        return null;    
    }
    
    @Override
    public double pidGet() {
        // TODO Auto-generated method stub
        return 0;
    }
}
