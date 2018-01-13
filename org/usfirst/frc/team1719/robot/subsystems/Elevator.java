package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Elevator subsystem for controlling the elevator
 * @author bennyrubin
 *
 */
public class Elevator extends Subsystem {
	private Encoder positionEncoder;
	private DigitalInput topSwitch;
	private DigitalInput bottomSwitch;
	private SpeedController speedController;
	
	/**
	 * Takes in an Encoder for the position, a motor to control it, and two switches for when it reaches the top and bottom of the elevator
	 * @param position
	 * @param top
	 * @param bottom
	 * @param speedController
	 */
	public Elevator(Encoder position,DigitalInput top, DigitalInput bottom, SpeedController _speedController) {
		positionEncoder = position;
		topSwitch = top;
		bottomSwitch = bottom;
		speedController = _speedController;
		
	}

	@Override
	protected void initDefaultCommand() {
		
		
	}

	/**
	 * takes a speed and sets the motor to it 
	 * @param speed
	 */
	public void moveElevator(double speed) {
		if(speed < -1) {
			speed = -1;
		}
		if(speed > 1) {
			speed = 1;
		}

		if(topSwitch.get() && speed > 0) {
			speedController.set(0);
		}
		
		else if(bottomSwitch.get() && speed < 0) {
			speedController.set(0);
		}
		
		else {
			speedController.set(speed);
		}
		
		
		
		
	}
	
	/**
	 * stops the elevator from moving
	 */
	public void stop() {
		speedController.set(0);
	}
	
	
	
	

}
