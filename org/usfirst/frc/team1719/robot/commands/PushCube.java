package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * A command for ejecting the cube
 * 
 * @author gusg21
 *
 */
public class PushCube extends Command {

	Claw claw;

	private Timer timer;
	private boolean done = false;
	private double OUTTIME = 0.2D;

	/**
	 * 
	 * Requires a claw subsystem
	 * 
	 * @param _claw
	 * 
	 */
	public PushCube(Claw _claw) {
		timer = new Timer();

		claw = _claw;
		requires(claw);
	}

	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (timer.get() == 0) {
			timer.start();
		}

		if (timer.get() <= OUTTIME) {
			claw.push();
		} else {
			done = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return done;
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected void end() {
		claw.retract();
		timer.stop();
		timer.reset();
		done = false;
	}
}
