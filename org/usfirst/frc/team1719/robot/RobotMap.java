package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.sensors.E4TOpticalEncoder;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    /*
	 * For example to map the left and right motors, you could define the
	 * following variables to use with your drivetrain subsystem.
	 * public static int leftMotor = 1;
	 * public static int rightMotor = 2;

	 * If you are using multiple modules, make sure to define both the port
	 * number and the module. For example you with a rangefinder:
	 * public static int rangefinderPort = 1;
	 * public static int rangefinderModule = 1;
	 */
	
	/* PWM */
	public static final SpeedController leftDrive = new Spark(0);
	public static final SpeedController rightDrive = new Spark(1); 
	public static final SpeedController elevatorSpeed = new Spark(3); //Not sure if this is Permanent Spark
	
	
	
	
	
	/* MXP */
	public static final AHRS navx = new AHRS(I2C.Port.kMXP);

	/* DIO */
    public static final E4TOpticalEncoder leftDriveEnc = new E4TOpticalEncoder(6, 7, true);
    public static final E4TOpticalEncoder rightDriveEnc = new E4TOpticalEncoder(2, 3, false);
    public static final E4TOpticalEncoder elevatorEnc = new E4TOpticalEncoder(4,5, false);
    
    public static final DigitalInput topSwitch = new DigitalInput(20);//temp port 
	public static final DigitalInput bottomSwitch = new DigitalInput(21);//temp port
}
