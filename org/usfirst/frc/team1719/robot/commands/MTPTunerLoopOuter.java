package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
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
 * Auxilliary class for tuning the MoveToPosition command
 * 
 * @author Duncan
 */
public class MTPTunerLoopOuter extends Command implements PIDSource, PIDOutput {
    
    /**
     * Class used to store a PID output and apply it as a setpoint for a PID
     * input
     * 
     * @author Duncan
     */
    private class PIDHelper implements PIDSource, PIDOutput {
        private volatile double val = 0;
        
        @Override
        public void pidWrite(double output) {
            val = output % 360;
        }
        
        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {}
        
        @Override
        public PIDSourceType getPIDSourceType() {
            return PIDSourceType.kDisplacement;
        }
        
        @Override
        public double pidGet() {
            return posTracker.getHeading() - pathAngle - val;
        }
    }
    
    private static final double SPD = 0.5D;
    
    private Position posTracker;
    private Drive drive;
    
    private double desiredX = 0;
    private double desiredY = 0;
    private double errX = 0;
    private double errY = 0;
    private double pathAngle = 0;
    private double distOffPath = 0;
    private double rotSpd = 0;
    private volatile PIDHelper pidhelper;
    private PIDController desiredHeadingController;
    private PIDController rotateController;
    
    /**
     * Initializes the command
     * 
     * @param _desiredX
     *            - target X, used only for 'desired line' calculations
     * @param _desiredY
     *            - target Y, used only for 'desired line' calculations
     * @param _posTracker
     *            - the subsystem to track the robot's position
     * @param _drive
     *            - the subsystem controlling the drive train
     * @param _robot
     *            - the robot class
     */
    public MTPTunerLoopOuter(double _desiredX, double _desiredY, Position _posTracker, Drive _drive, Robot _robot) {
        desiredX = _desiredX;
        desiredY = _desiredY;
        posTracker = _posTracker;
        drive = _drive;
        try {
            requires((Subsystem) drive);
        } catch(ClassCastException e) {
            System.out.println("Running Unit test on MoveToPosition");
        }
        pidhelper = new PIDHelper();
        desiredHeadingController = new PIDController(5, 0, 0, this, pidhelper);
        rotateController = new PIDController(0.01, 0.0003, 0.001, pidhelper, this);
        desiredHeadingController.setSetpoint(0);
        desiredHeadingController.setOutputRange(-90.0D, 90.0D);
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D, 180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        desiredHeadingController.enable();
        rotateController.enable();
        SmartDashboard.putData("Rotation", rotateController);
        SmartDashboard.putData("Desired Heading", desiredHeadingController);
    }
    
    @Override
    protected void initialize() {
        pathAngle = Math.toDegrees(Math.atan2(desiredX, desiredY));
    }
    
    @Override
    protected void execute() {
        rotateController = (PIDController) SmartDashboard.getData("Rotation");
        double kp = rotateController.getP();
        System.out.println("KP: " + kp);
        
        desiredHeadingController = (PIDController) SmartDashboard.getData("Desired Heading");
        System.out.println("Updating PID");
        System.out.println("Actual KP" + desiredHeadingController.getP() + "/KI" + desiredHeadingController.getI()
                + "/KD" + desiredHeadingController.getD());
        System.out.println("OUTPUT " + desiredHeadingController.get());
        errX = desiredX - posTracker.getX();
        errY = desiredY - posTracker.getY();
        double offPathAngle = -(Math.atan2(errX, errY) - Math.toRadians(pathAngle));
        distOffPath = Math.sin(offPathAngle) * Math.sqrt(errX * errX + errY * errY);
        // System.out.println("Following path : power " + rotSpd + "Rotator " +
        // rotateController.get());
        drive.arcadeDrive(SPD, -rotSpd);
        SmartDashboard.putNumber("MTP Desired angle", pidhelper.val);
        SmartDashboard.putNumber("MTP e\u27c2", distOffPath);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading() - pathAngle);
        
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {}
    
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
        return PIDSourceType.kDisplacement;
    }
    
    @Override
    public double pidGet() {
        return distOffPath;
    }
    
}