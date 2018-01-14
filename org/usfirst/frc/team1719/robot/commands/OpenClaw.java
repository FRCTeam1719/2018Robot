package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {
	private Claw claw;

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
