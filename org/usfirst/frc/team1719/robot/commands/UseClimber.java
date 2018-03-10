package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Skeleton of a command to climb at the end
 */
public class UseClimber extends Command {
	
	private Climber climber;
	
    public UseClimber(Climber _climber) {
    	climber = _climber;
    	
        requires(climber);
    }

    @Override
    protected void initialize() {}

    @Override
    protected void execute() {
    	climber.climb();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    	climber.stop();
    }

    @Override
    protected void interrupted() {
    	end();
    }
}
