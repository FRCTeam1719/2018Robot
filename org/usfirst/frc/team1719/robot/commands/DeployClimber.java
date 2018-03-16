package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Skeleton of a command to climb at the end
 */
public class DeployClimber extends InstantCommand {
	
	private Climber climber;
	
    public DeployClimber(Climber _climber) {
    	climber = _climber;
    	
        requires(climber);
    }
    
    public void execute() {
        climber.deploy();
    }

    
}
