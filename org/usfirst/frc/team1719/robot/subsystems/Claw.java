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

	/**
	 * A solenoid for the claw pistons, one for the pusher
	 * 
	 * @param claw - Solenoid for the claw
	 * @param pusher - Solenoid for the pusher
	 */
	public Claw(Solenoid claw, Solenoid pusher) {
		this.claw = claw;
	}

	/**
	 * Open the claw
	 */
	public void open() {
		claw.set(true);
	}

	/**
	 * Close the claw
	 */
	public void close() {
		claw.set(false);
	}

	/**
	 * Is the claw currently open?
	 * 
	 * @return is it
	 */
	public boolean isOpen() {
		return claw.get();
	}

	@Override
	protected void initDefaultCommand() {
		// No default action
	}

}
