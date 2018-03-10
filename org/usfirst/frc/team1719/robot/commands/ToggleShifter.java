package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * A simple command to toggle the solenoid for high/low gear.
 * 
 * @author gusg21
 *
 */
public class ToggleShifter extends Command {

    Drive drive;
    
    /**
     * Initialize the ToggleShifter command.
     * 
     * @param _drive
     */
    public ToggleShifter(Drive _drive) {
        drive = _drive;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drive.toggleShift();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true; // end immediately
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {}
}
