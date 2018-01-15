package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * A command for closing the claw
 * 
 * @author gusg21
 *
 */
public class CloseClaw extends Command {
	private Claw claw;

	/**
	 * Requires an instance of the Claw subsystem
	 * 
	 * @param claw
	 */
	public CloseClaw(Claw _claw) {
		claw = _claw;
		requires(claw);
	}

	@Override
	protected void execute() {
		claw.close();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
