package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * A subsystem for the solenoids that control the claws
 * 
 * @author Hamara
 * @author gusg21
 * @author aaron
 *
 */
public class Claw extends Subsystem {
	private Solenoid claw;
	private Solenoid pusher;

	/**
	 * A solenoid for the claw pistons, one for the pusher
	 * 
	 * @param claw
	 *            - Solenoid for the claw
	 * @param pusher
	 *            - Solenoid for the pusher
	 */
	public Claw(Solenoid claw, Solenoid pusher) {
		this.claw = claw;
		this.pusher = pusher;
	}

	/**
	 * Open the claw
	 */
	public void open() {
		claw.set(false);
	}

	/**
	 * Close the claw
	 */
	public void close() {
		claw.set(true);
	}

	/**
	 * Is the claw currently open?
	 * 
	 * @return is it
	 */
	public boolean isOpen() {
		return !claw.get();
	}

	/**
	 * Extend the pusher
	 */
	public void push() {
		pusher.set(true);
	}

	/**
	 * Bring the pusher back in
	 */
	public void retract() {
		pusher.set(false);
	}

	@Override
	protected void initDefaultCommand() {
		// No default action
	}

}
