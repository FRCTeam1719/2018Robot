package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Drive;

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
	
	/**
	 * The command for simple tank drive.
	 * 
	 * @param driveSystem
	 */
    public UseDrive(Drive _driveSystem) {
    	driveSystem = _driveSystem;
    	
        requires(driveSystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	// Raw Datas
    	double left = -Robot.oi.driverGetLeftY();
    	double right = -Robot.oi.driverGetRightY();
    	
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
    
//    	System.out.println( 
//    		Robot.oi.getLeftY()
//    	);
//    	System.out.println( 
//        	left
//      );
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
