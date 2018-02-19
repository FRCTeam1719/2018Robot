package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Put the wrist up if down, down if up, etc.
 * 
 * @author gusg21
 *
 */
public class ToggleWrist extends Command {

    Wrist wrist;
    
    public ToggleWrist(Wrist _wrist) {
        wrist = _wrist;
        requires(wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        wrist.put(!wrist.getState());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
