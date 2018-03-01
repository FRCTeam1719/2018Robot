package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;
import org.usfirst.frc.team1719.robot.sensors.RangeFinder45LMS;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/* PWM */
	public static final SpeedController leftDrive = new Spark(0);
	public static final SpeedController rightDrive = new Spark(1); 
	public static final SpeedController elevator = new Spark(2); //Not sure if this is Permanent Spark
	public static final SpeedController climberMotor = new Spark(3);
	
	//public static final Servo clawHolder = new Servo(5);

	/* MXP */
	public static final AHRS navx = new AHRS(I2C.Port.kMXP);

	/* DIO */
	public static final E4TOpticalEncoder leftDriveEnc = new E4TOpticalEncoder(0, 1, true);
	public static final E4TOpticalEncoder rightDriveEnc = new E4TOpticalEncoder(2, 3, false);
	public static final DigitalInput lowerLimit = new DigitalInput(4);
	public static final DigitalInput upperLimit = new DigitalInput(5);


	/* PCM */
	public static final Solenoid shifterSolenoid = new Solenoid(4);
	public static final Solenoid clawSolenoid = new Solenoid(5);
	public static final Solenoid wristSolenoid = new Solenoid(6);

	/* AIN */
	public static final RangeFinder45LMS rangeFinder = new RangeFinder45LMS(0);
}
