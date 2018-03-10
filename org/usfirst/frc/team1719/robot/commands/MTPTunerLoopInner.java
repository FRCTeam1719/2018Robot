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
 * Helper class to tune the inner PID loop of {@link MoveToPosition}
 */
public class MTPTunerLoopInner extends Command implements PIDSource, PIDOutput {
    
    private static final double SPD = 0.5D;
    
    private Position posTracker;
    private Drive drive;
    private PIDController rotateController;
    
    private double targetAngle;
    private double rotSpd;
    
    /**
     * Initializes the command.
     * 
     * @param _posTracker - the subsystem to track the robot's position
     * @param _drive - the subsystem controlling the drive train
     * @param _robot - the robot class
     */
    public MTPTunerLoopInner(Position _posTracker, Drive _drive, Robot _robot) {
        posTracker = _posTracker;
        drive = _drive;
        requires((Subsystem) drive);
        SmartDashboard.putNumber("MTPLT1 target angle", targetAngle = 0.0D);
        rotateController = new PIDController(0.01, 0.0003, 0.001, this, this);
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D, 180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        rotateController.enable();
        SmartDashboard.putData("ROTATION_PID_IN", rotateController);
    }
    
    @Override 
    protected void initialize() {}
    
    @Override
    protected void execute() {
        rotateController.enable();
        rotateController = (PIDController) SmartDashboard.getData("ROTATION_PID_IN");
        System.out.println("ITS WORKING!!");
        
        System.out.println(rotateController.getP());
        targetAngle = SmartDashboard.getNumber("MTPLT1 target angle", targetAngle);
        drive.arcadeDrive(SPD, -rotSpd);
        SmartDashboard.putNumber("MTP Desired angle", targetAngle);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading());
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
        rotateController.disable();
    }
    
    @Override
    protected void interrupted() {
        rotateController.disable();
        
    }
    
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
        return posTracker.getHeading() - targetAngle;
    }
}
