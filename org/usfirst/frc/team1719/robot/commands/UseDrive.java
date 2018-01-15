package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive Command
 * 
 * @author gusg21
 *
 */
public class UseDrive extends Command {
    
    private Drive driveSystem;
    
    private final double SYNC_AMOUNT = 0.15;
    private final double DEADZONE = 0.1;
    static PIDController leftController;
    PIDController rightController;
    
    private double highMaxSpeed = 250D;
    private double lowMaxSpeed = 50D;
    private double maxSpeed = highMaxSpeed;
    private double MAX_SPEED_SCALING_FACTOR = 1.2;
    
    volatile double leftMotorOutput = 0;
    volatile double rightMotorOutput = 0;
    
    double leftkP = 0;
    double leftkF = 1 / maxSpeed;
    double leftkD = 0;
    double leftkI = 0;
    
    double rightkP = 0;
    double rightkF = 1 / maxSpeed;
    double rightkD = 0;
    double rightkI = 0;
    
    // PID Objects, these are used to get the current motor stuffs to those PIDs.
    private class leftDrivePIDOutput implements PIDOutput {
        
        @Override
        public void pidWrite(double output) {
            leftMotorOutput = output;
        }
    }
    
    private class rightDrivePIDOutput implements PIDOutput {
        
        @Override
        public void pidWrite(double output) {
            rightMotorOutput = output;
        }
    }

    
    /**
     * The command for simple tank drive.
     * 
     * @param driveSystem
     */

    public UseDrive(Drive _driveSystem) {
        driveSystem = _driveSystem;
        
        requires(driveSystem);
        
        leftController = new PIDController(leftkP, 0, leftkF, driveSystem.getEncoderL(), new leftDrivePIDOutput());
        rightController = new PIDController(rightkP, 0, rightkF, driveSystem.getEncoderL(), new leftDrivePIDOutput());
    }
    
    // Not deprecated for bad reason.
    @SuppressWarnings("deprecation")
    @Override
    protected void initialize() {
        driveSystem.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
        driveSystem.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
        
        leftController.setOutputRange(-1, 1);
        rightController.setOutputRange(-1, 1);
        
        leftController.setContinuous(false);
        rightController.setContinuous(false);
        
        leftController.setToleranceBuffer(20);
        rightController.setToleranceBuffer(20);
        
        leftController.setPercentTolerance(5);
        rightController.setPercentTolerance(5);
        
    }
    
    @Override
    protected void execute() {
        // Raw Datas
        double left = -Robot.oi.getLeftY();
        double right = -Robot.oi.getRightY();
        
        // Smooth curving
        left = left * Math.abs(left);
        right = right * Math.abs(right);
        
        // Synchronize the sticks
        if (Math.abs(right - left) < SYNC_AMOUNT) {
            left = right = (left + right) / 2;
        }
        
        // Deadzoning
        if (Math.abs(left) < DEADZONE) {
            left = 0;
        }
        if(Math.abs(right) < DEADZONE) {
            right = 0;
        }
        
        // Apply
        driveSystem.tankDrive(left, right);
        
        leftController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
        leftController.setPID(leftController.getP(), leftController.getI(), leftController.getD(), (1 / maxSpeed));
        
        rightController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
        rightController.setPID(rightController.getP(), rightController.getI(), rightController.getD(), (1 / maxSpeed));
        
        // System.out.println(
        // Robot.oi.getLeftY()
        // );
        // System.out.println(
        // left
        // );
        
    }
    
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {}
    @Override
    protected void interrupted() {}
    
    /**
     * Get all of the PID Constants for nice tuning.
     */
    public void setPIDConstantsFromDashboard() {
        leftkP = SmartDashboard.getNumber("LEFT_DRIVE_KP", 0);
        leftkF = SmartDashboard.getNumber("LEFT_DRIVE_KF", 0);
        leftkI = SmartDashboard.getNumber("LEFT_DRIVE_KI", 0);
        leftkD = SmartDashboard.getNumber("LEFT_DRIVE_KI", 0);
        
        rightkP = SmartDashboard.getNumber("RIGHT_DRIVE_KP", 0);
        rightkF = SmartDashboard.getNumber("RIGHT_DRIVE_KF", 0);
        rightkI = SmartDashboard.getNumber("RIGHT_DRIVE_KI", 0);
        rightkD = SmartDashboard.getNumber("LEFT_DRIVE_KD", 0);
    }
    
}
