package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.CloseClaw;
import org.usfirst.frc.team1719.robot.commands.HighShifter;
import org.usfirst.frc.team1719.robot.commands.LowShifter;
import org.usfirst.frc.team1719.robot.commands.OpenClaw;
import org.usfirst.frc.team1719.robot.commands.ToggleClaw;
import org.usfirst.frc.team1719.robot.commands.ToggleWrist;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    /*
     *** CREATING BUTTONS *** One type of button is a joystick button which is any
     * button on a joystick. You create one by telling it which joystick it's on and
     * which button number it is. Joystick stick = new Joystick(port); Button button
     * = new JoystickButton(stick, buttonNumber);
     * 
     * 
     */
    private Joystick driver = new Joystick(0);
    private Joystick operator = new Joystick(1);
    
    private boolean shifterState = false;
    
    /**
     * @return the horizontal position of the left thumb-joystick
     */
    public double getLeftX() {
        return driver.getRawAxis(0);
    }
    
    /**
     * @return the vertical position of the left thumb-joystick
     */
    public double getLeftY() {
        return driver.getRawAxis(1);
    }
    
    /**
     * @return the horizontal position of the right thumb-joystick
     */
    public double getRightX() {
        return driver.getRawAxis(2);
    }
    
    /**
     * @return the vertical position of the right thumb-joystick
     */
    public double getRightY() {
        return driver.getRawAxis(3);
    }

    /**
     * @return the vertical position of the operator joystick
     */
    public double operatorGetY() {
        return operator.getRawAxis(1);
    }
    
    /**
     * @return the vertical position of the operator joystick
     */
    public double operatorGetX() {
        return operator.getRawAxis(0);
    }
    
    /**
     * @return the fader position of the operator joystick
     */
    public double operatorGetZ() {
        return operator.getRawAxis(2);
    }
    
    /**
     * Get the state of the shifter. This also updates it
     * if it needs to be.
     * 
     * @return
     */
    public boolean driverGetShift() {
        if (driver.getRawButtonReleased(3)) {
            shifterState = !shifterState;
        }
        return shifterState;
    }
    
    /**
     * Set the rumble on the driver's controller.
     * 
     * @param rumble - Amount to rumble (0 - 1)
     */
    public void setRumble(double rumble) {
        driver.setRumble(RumbleType.kLeftRumble, rumble);
        driver.setRumble(RumbleType.kRightRumble, rumble);
    }
    
    /**
     * Setup the buttons. Requires the instance of the Robot
     * 
     * @param robot
     *            - Instance of the Robot
     */
	public void init(Robot robot) {
		Button toggleButton = new JoystickButton(operator, 3);
		Button dropButton = new JoystickButton(operator, 5);
		Button pickupButton = new JoystickButton(operator, 4);
		Button shiftLowButton = new JoystickButton(driver, 5);
		Button shiftHighButton = new JoystickButton(driver, 6);
		Button wristButton = new JoystickButton(operator, 2);
//		Button fireButton = new JoystickButton(operator, 2);
//		Button climber = new JoystickButton(operator, 1);
		
		toggleButton.whenReleased(new ToggleClaw(robot.claw));		
		dropButton.whenPressed(new OpenClaw(robot.claw));
		pickupButton.whenPressed(new CloseClaw(robot.claw));
		shiftLowButton.whenPressed(new LowShifter(robot.drive));
		shiftHighButton.whenPressed(new HighShifter(robot.drive));
		wristButton.whenPressed(new ToggleWrist(robot.wrist));
//		fireButton.whenPressed(new PushCube(robot.claw));
//		climber.whileHeld(new UseClimber(robot.climber)); not yet
	}

	/*
	 *** CREATING BUTTONS *** One type of button is a joystick button which is any
	 * button on a joystick. You create one by telling it which joystick it's on and
	 * which button number it is. Joystick stick = new Joystick(port); Button button
	 * = new JoystickButton(stick, buttonNumber);
	 * 
	 * There are a few additional built in buttons you can use. Additionally, by
	 * subclassing Button you can create custom triggers and bind those to commands
	 * the same as any other Button.
	 *** 
	 * TRIGGERING COMMANDS WITH BUTTONS *** Once you have a button, it's trivial to
	 * bind it to a button in one of three ways:
	 * 
	 * Start the command when the button is pressed and let it run the command until
	 * it is finished as determined by it's isFinished method.
	 * button.whenPressed(new ExampleCommand());
	 * 
	 * Run the command while the button is being held down and interrupt it once the
	 * button is released. button.whileHeld(new ExampleCommand());
	 * 
	 * Start the command when the button is released and let it run the command
	 * until it is finished as determined by it's isFinished method.
	 * button.whenReleased(new ExampleCommand());
	 */
}
