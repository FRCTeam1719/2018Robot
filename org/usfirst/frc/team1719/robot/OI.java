package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.CloseClaw;
import org.usfirst.frc.team1719.robot.commands.OpenClaw;
import org.usfirst.frc.team1719.robot.commands.PushCube;
import org.usfirst.frc.team1719.robot.commands.ToggleClaw;

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
     */
    private Joystick driver = new Joystick(0);
    private Joystick operator = new Joystick(1);
    
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
        return driver.getRawAxis(4);
    }
    
    /**
     * @return the vertical position of the right thumb-joystick
     */
    public double getRightY() {
        return driver.getRawAxis(5);
    }
    
    public double operatorGetY() {
        return operator.getRawAxis(1);
    }
    
    public double operatorGetX() {
        return operator.getRawAxis(0);
    }
    public double operatorGetZ() {
        return operator.getRawAxis(2);
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
        Button fireButton = new JoystickButton(operator, 2);
        
        //toggleButton.whenReleased(new ToggleClaw(robot.claw));
        //dropButton.whenPressed(new OpenClaw(robot.claw));
        //pickupButton.whenPressed(new CloseClaw(robot.claw));
        //fireButton.whenPressed(new PushCube(robot.claw));
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
