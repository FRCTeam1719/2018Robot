package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

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
    PIDController leftController;
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
    double righkD = 0;
    
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
    }
    //Not deprecated for bad reason.
    @SuppressWarnings("deprecation")
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
    
    protected void execute() {
        // Raw Datas
        double left = Robot.oi.getLeftY();
        double right = Robot.oi.getRightY();
        
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
        if (Math.abs(right) < DEADZONE) {
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
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {}
    
    protected void interrupted() {}
}
