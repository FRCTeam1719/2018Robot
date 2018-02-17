package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseClimber;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Skeleton of the subsystem representing the climbing device
 */
public class Climber extends Subsystem {
	
    private SpeedController climber;

    /**
     * Creates a new Climber subsystem
     * 
     * @param _climber the motor controller that spins the winch
     */
	public Climber(SpeedController _climber) {
		climber = _climber;
	}
	
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new UseClimber(this));
    }
	
	/**
	 * Call when you want it to climb
	 */
	public void climb() {
		climber.set(1.0);
	}
	
	/**
	 * Abort! Abort!
	 */
	public void stop () {
		climber.set(0.0);
	}
	
}

