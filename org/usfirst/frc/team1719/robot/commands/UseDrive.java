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
    
    private boolean shifted;
    
    private static final double SYNC_AMOUNT = 0.15;
    private static final double DEADZONE = 0.1;
    
    private PIDController leftController;
    private PIDController rightController;
    
    private double lowMaxSpeed = 50D;
    private double maxSpeed = lowMaxSpeed;
    private double MAX_SPEED_SCALING_FACTOR = 1.2;
    
    volatile double leftMotorOutput = 0;
    volatile double rightMotorOutput = 0;
    
    double leftkP = 0D;
    double leftkF = 1 / maxSpeed;
    double leftkD = 0D;
    double leftkI = 0D;
    
    double rightkP = 0D;
    double rightkF = 1 / maxSpeed;
    double rightkD = 0D;
    double rightkI = 0D;
    
    // PID Objects, these are used to get the current motor stuffs to those PIDs.
    private class LeftDrivePIDOutput implements PIDOutput {
        
        @Override
        public void pidWrite(double output) {
            leftMotorOutput = output;
        }
    }
    
    private class RightDrivePIDOutput implements PIDOutput {
        
        @Override
        public void pidWrite(double output) {
            rightMotorOutput = output;
        }
    }
    
    /**
     * The command for simple tank drive.
     * 
     * @param driveSystem - the subsystem controlling the drive train
     */
    
    public UseDrive(Drive _driveSystem) {
        driveSystem = _driveSystem;
        
        requires(driveSystem);
        
        driveSystem.getEncoderL().setPIDSourceType(PIDSourceType.kRate);
        driveSystem.getEncoderR().setPIDSourceType(PIDSourceType.kRate);
        
        leftController = new PIDController(leftkP, 0, leftkF, driveSystem.getEncoderL(), new LeftDrivePIDOutput());
        rightController = new PIDController(rightkP, 0, rightkF, driveSystem.getEncoderR(), new RightDrivePIDOutput());
        
        SmartDashboard.putNumber("Right_rate", driveSystem.getEncoderR().getRate());
        SmartDashboard.putNumber("Left_rate", driveSystem.getEncoderL().getRate());
        SmartDashboard.putData("LEFT_PID", leftController);
        SmartDashboard.putData("RIGHT_PID", rightController);
        
    }
    
    /* Not deprecated for bad reason. */
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
          
        leftController.enable();
        rightController.enable();
    }
    
    @Override
    protected void execute() {
        /* Raw Data */
        double left = -Robot.oi.getLeftY();
        double right = -Robot.oi.getRightY();
        SmartDashboard.putNumber("Right_rate", driveSystem.getEncoderR().getRate());
        SmartDashboard.putNumber("Left_rate", driveSystem.getEncoderL().getRate());
        
        /* Smooth curving */
        left = left * Math.abs(left);
        right = right * Math.abs(right);
        
        /* Synchronize the sticks */
        if (Math.abs(right - left) < SYNC_AMOUNT) {
            left = right = (left + right) / 2;
        }
        
        double desiredLeftRate = left * maxSpeed;
        double desiredRightRate = right * maxSpeed;
        
        /* Deadzoning */
        if (Math.abs(left) < DEADZONE) {
            left = 0;
            leftController.setSetpoint(0);
            leftController.reset();
            desiredLeftRate = 0;
        } else {
            leftController.enable();
            leftController.setSetpoint(desiredLeftRate);
        }
        if (Math.abs(right) < DEADZONE) {
            right = 0;
            rightController.setSetpoint(0);
            rightController.reset();
            desiredRightRate = 0;
        } else {
            rightController.enable();
            rightController.setSetpoint(desiredRightRate);
        }
        
        if (shifted != Robot.oi.driverGetShift()) {
            driveSystem.setShift(shifted = !shifted);
        }
        
        /* Apply PID output values */
        driveSystem.tankDrive(leftMotorOutput, rightMotorOutput);
        
        leftController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
        leftController.setPID(leftController.getP(), leftController.getI(), leftController.getD(), (1 / maxSpeed));
        
        rightController.setInputRange(-(maxSpeed * MAX_SPEED_SCALING_FACTOR), maxSpeed * MAX_SPEED_SCALING_FACTOR);
        rightController.setPID(rightController.getP(), rightController.getI(), rightController.getD(), (1 / maxSpeed));
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {}
    
    @Override
    protected void interrupted() {}
}
