package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A simple command to toggle the state of the claw.
 */
public class ToggleClaw extends Command {

	private enum states {
		START, WAIT_FOR_TIMER, CLEANUP
	};

	private Claw claw;
	private states curState;
	private boolean done;
	private Timer timer = new Timer();
	private final double WAIT_TIME = 0.2;

	public ToggleClaw(Claw claw) {
		this.claw = claw;
		requires(claw);
	}

	@Override
	protected void initialize() {
		curState = states.START;
		done = false;
		timer.reset();
	}

	@Override
	protected void execute() {
		switch (curState) {
		case START:
			if (claw.isOpen()) {
				claw.close();
				done = true;
				curState = states.CLEANUP;

			} else {
				claw.open();
//				claw.push();
				timer.start();
				curState = states.WAIT_FOR_TIMER;

			}
			
			break;

		case WAIT_FOR_TIMER:
			timer.get();
			if (timer.get() > WAIT_TIME) {
				curState = states.CLEANUP;

			}

			break;
		case CLEANUP:
			timer.stop();
//			claw.retract();
			done = true;
		}

	}

	@Override
	protected boolean isFinished() {
		return done;
	}

}
