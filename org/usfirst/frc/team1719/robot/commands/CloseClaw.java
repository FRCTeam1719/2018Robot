package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command {
	private Claw claw;

	public CloseClaw(Claw c) {
		claw = c;
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
