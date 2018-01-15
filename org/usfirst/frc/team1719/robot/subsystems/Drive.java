package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseDrive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive Controller class.
 * 
 * @author Gus (@gusg21)
 * @author Quintin (@a-bad-programmer)
 */
public class Drive extends Subsystem {
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	private SpeedController leftController;
	private SpeedController rightController;

	/**
	 * Drive controller. Two SpeedController arguments, one for left and one for
	 * right.
	 * 
	 * @param leftController
	 * @param rightController
	 */
	public Drive(SpeedController _leftController, SpeedController _rightController) {
		leftController = _leftController; // Not left drive
		rightController = _rightController;
		rightController.setInverted(true); // Invert right drive
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new UseDrive(this));
	}

	/**
	 * Set the speed of each individual side of motors.
	 * 
	 * @param left
	 * @param right
	 */
	public void tankDrive(double left, double right) {
		leftController.set(left);
		rightController.set(right); // Right is inverted :P
	}
}
