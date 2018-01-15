package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * A command for opening the claw
 * 
 * @author gusg21
 *
 */
public class OpenClaw extends Command {
	private Claw claw;

	/**
	 * 
	 * An instance of the claw subsystem
	 * 
	 * @param claw
	 */
	public OpenClaw(Claw claw) {
		this.claw = claw;
	}

	@Override
	protected void execute() {
		System.out.println("Reached!");
		claw.open();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
