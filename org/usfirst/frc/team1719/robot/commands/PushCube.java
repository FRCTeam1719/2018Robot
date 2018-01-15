package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PushCube extends Command {

	Claw claw;
	
    public PushCube(Claw _claw) {
        claw = _claw;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	claw.push();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	claw.retract();
    }
}
