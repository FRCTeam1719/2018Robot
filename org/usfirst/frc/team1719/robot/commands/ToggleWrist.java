package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
<<<<<<< HEAD
 * command for toggling the wrist
 * @author bennyrubin
 *
 */
public class ToggleWrist extends Command{
    Wrist wrist;
    boolean state;
    /**
     * takes a wrist and sets it to the wrist
     * @param _wrist
     */
    
    public ToggleWrist(Wrist _wrist) {
        wrist = _wrist;
        requires(wrist);
    }

    protected void initialize() {
    }
    
    /**
     * toggles the wrist
     */
    protected void execute() {
       wrist.toggle();
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
