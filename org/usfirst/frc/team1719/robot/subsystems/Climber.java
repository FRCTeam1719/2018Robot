package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Skeleton of the subsystem representing the climbing device
 */
public class Climber extends Subsystem {
	
    private SpeedController climber;
    private DoubleSolenoid deploy;

    /**
     * Creates a new Climber subsystem
     * 
     * @param _climber the motor controller that spins the winch
     */
	public Climber(SpeedController _climber, DoubleSolenoid _deploy) {
		climber = _climber;
		deploy = _deploy;
        deploy.set(Value.kReverse);
	}
	
	@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
	
	public void deploy() {
	    deploy.set(Value.kForward);
	}
	
}

