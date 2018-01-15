package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	private Solenoid claw;
	private Solenoid pusher;

	public Claw(Solenoid claw, Solenoid pusher) {
		this.claw = claw;
		this.pusher = pusher;
	}

	public void open() {
		claw.set(false);
	}

	public void close() {
		claw.set(true);
	}

	// Return whether or not the claw is open
	public boolean isOpen() {
		return !claw.get();
	}

	public void push() {
		pusher.set(true);
	}

	public void retract() {
		pusher.set(false);
	}

	@Override
	protected void initDefaultCommand() {
		// No default action
	}

}
