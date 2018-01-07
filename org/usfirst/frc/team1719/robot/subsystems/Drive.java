package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Drive Controller class.
 * 
 * @author Gus (@gusg21) and Quintin (@a-bad-programmer)
 */
public class Drive {

    Encoder leftEncoder;
    Encoder rightEncoder;
    private SpeedController leftController;
    private SpeedController rightController;
    
    /**
     * Drive controller. Two SpeedController arguments, one for left and one for right.
     * 
     * @param leftController
     * @param rightController
     */
    public Drive(SpeedController _leftController, SpeedController _rightController) {
    	leftController = _leftController; // Not left drive
    	rightController = _rightController;
    	rightController.setInverted(true); // Invert right drive
    }
    
    /**
     * Set the speed of each individual side of motors.
     * 
     * @param left
     * @param right
     */
    public void tankDrive(float left, float right) {
    	leftController.set(left);
    	rightController.set(right); // Right is inverted :P
    }
 
}
