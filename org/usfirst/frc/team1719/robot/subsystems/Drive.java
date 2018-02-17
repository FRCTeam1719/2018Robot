package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseDrive;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive Controller class.
 * 
 * @author Gus (@gusg21)
 * @author Quintin (@a-bad-programmer)
 */
public class Drive extends Subsystem {
    
    private IEncoder leftEncoder;
    private IEncoder rightEncoder;
    private SpeedController leftController;
    private SpeedController rightController;
    private Solenoid shifter;
    
    private static final double WHEEL_DIAMETER = 4D;
    
    /**
     * Drive controller. Two SpeedController arguments, one for left and one for
     * right.
     * 
     * @param leftController - The Left drive motor contoller
     * @param rightController - the Right drive motor controller
     * @param leftEncoder - the left encoder
     * @param rightEncoder - the right encoder
     */
    public Drive(SpeedController _leftController, SpeedController _rightController, IEncoder _leftEncoder,
            IEncoder _rightEncoder, Solenoid _shifter) {
        leftController = _leftController; // Not left drive
        rightController = _rightController;
        leftEncoder = _leftEncoder;
        rightEncoder = _rightEncoder;
        rightController.setInverted(true); // Invert right drive
        leftEncoder.config(Math.PI * WHEEL_DIAMETER * 4);
        rightEncoder.config(Math.PI * WHEEL_DIAMETER * 4);
        leftEncoder.setReverseDirection(true);
        rightEncoder.setReverseDirection(false);
    	shifter = _shifter;
    }
    
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new UseDrive(this));
    }
    
    /**
     * Set the speed of each individual side of motors.
     * 
     * @param left - The power to give to the left motor, normalized to the range [-1, 1]
     * @param right - The power to give to the right motor, normalized to the range [-1, 1]
     */
    public void tankDrive(double left, double right) {
        leftController.set(left);
        rightController.set(right);
    }
    
    /**
     * A wrapper providing arcade controls
     * 
     * @param speed - The net forward power, normalized to the range [-1, 1]
     * @param rotation - The rotational power, normalized to the range [-1, 1]
     */
    public void arcadeDrive(double speed, double rotation) {
    	tankDrive(speed - rotation, speed + rotation);
    }
    
    /**
     * Gets the values of the right encoder.
     */
    public IEncoder getEncoderR() {
        return rightEncoder;
    }
    
    /**
     * Gets the values of the left encoder.
     */
    public IEncoder getEncoderL () {
        return leftEncoder;
    }
    
    /**
     * Set the state of the shifter.
     * 
     * @param shifted
     */
    public void setShift(boolean shifted) {
        shifter.set(shifted);
    }
}
