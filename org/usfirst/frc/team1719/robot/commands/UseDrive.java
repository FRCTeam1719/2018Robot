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
    	driveSystem.tankDrive(Robot.oi.getLeftY(), Robot.oi.getRightY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
