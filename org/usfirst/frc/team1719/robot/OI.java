package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.commands.CloseClaw;
import org.usfirst.frc.team1719.robot.commands.HighShifter;
import org.usfirst.frc.team1719.robot.commands.LowShifter;
import org.usfirst.frc.team1719.robot.commands.OpenClaw;
import org.usfirst.frc.team1719.robot.commands.ToggleClaw;
import org.usfirst.frc.team1719.robot.commands.ToggleElevatorMode;
import org.usfirst.frc.team1719.robot.commands.ToggleWrist;
import org.usfirst.frc.team1719.robot.controllers.Attack3Joystick;
import org.usfirst.frc.team1719.robot.controllers.RedController;

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
    private Joystick operator = new Joystick(1); // Now operator console
    
    
    /*
     ***USING CONTROLLER OBJECTS*** To make swapping controllers, control tweaks easier the controller
     *has been separated into a generic controller for the driver and operator, where the buttons are
     *defined, a separate controller implementation where the buttons are actually mapped to the
     *individual controller
     */
    private RedController driverControl;
    private Attack3Joystick operatorControl;
    
    private boolean shifterState = false;
    
    /**
     * @return the horizontal position of the left thumb-joystick
     */
    public double getLeftX() {
        return driver.getRawAxis(driverControl.leftX);
    }
    
    /**
     * @return the vertical position of the left thumb-joystick
     */
    public double getLeftY() {
        return driver.getRawAxis(driverControl.leftY);
    }
    
    /**
     * @return the horizontal position of the right thumb-joystick
     */
    public double getRightX() {
        return driver.getRawAxis(driverControl.rightX);
    }
    
    /**
     * @return the vertical position of the right thumb-joystick
     */
	public double getRightY() {
		return driver.getRawAxis(driverControl.rightY);
	}
    /**
     * @return the vertical position of the operator joystick
     */
    public double operatorGetY() {
        return operator.getRawAxis(operatorControl.yAxis);
    }
    
    /**
     * @return the vertical position of the operator joystick
     */
    public double operatorGetX() {
        return operator.getRawAxis(operatorControl.xAxis);
    }
    
    public double operatorGetZ() {
        return operator.getRawAxis(operatorControl.zAxis);
    }
    
    /**
     * Get the state of the shifter. This also updates it if it needs to be.
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
     * @param rumble
     *            - Amount to rumble (0 - 1)
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
        Button wristButton = new JoystickButton(operator, operatorControl.wristButton);
        //Button climberDeploy = new JoystickButton(operator, operatorControl.climberDeploy);
        //Button climberClimb = new JoystickButton(operator, operatorControl.climberClimb);
        Button shiftLowButton = new JoystickButton(driver, driverControl.shiftLowButton);
        Button shiftHighButton = new JoystickButton(driver, driverControl.shiftHighButton);
        Button elevatorToggleButton = new JoystickButton(operator, operatorControl.elevatorToggleButton);
	    //Button rollerIn = new JoystickButton(operator, operatorControl.rollerIn);
	    //Button rollerOut = new JoystickButton(operator, operatorControl.rollerOut);
	    Button testAutonFire = new JoystickButton(operator, operatorControl.testAutonFire);
	    Button openClaw = new JoystickButton(operator, operatorControl.openClaw);
	    Button closeClaw = new JoystickButton(operator, operatorControl.closeClaw);
	    Button toggleClaw = new JoystickButton(operator, operatorControl.toggleClaw);

	    
	    //rollerIn.whileHeld(new UseIntake(robot.intake,1.00));
	    //rollerOut.whileHeld(new UseIntake(robot.intake,-1.00));
	    //testAutonFire.whenPressed(new TimedUseIntake(robot.intake, -1.0D, 1.0));
	    
	    /*
	     * Bind actions to the buttons
	     */
	    
	    openClaw.whenPressed(new OpenClaw(robot.claw));
	    closeClaw.whenPressed(new CloseClaw(robot.claw));
	    toggleClaw.whenPressed(new ToggleClaw(robot.claw));
	    
	    elevatorToggleButton.whenPressed(new ToggleElevatorMode(robot.elevator));
	    
	    
	    
	    
        shiftLowButton.whenPressed(new LowShifter(robot.drive));
        shiftHighButton.whenPressed(new HighShifter(robot.drive));
        wristButton.whenPressed(new ToggleWrist(robot.wrist));
        
        //climberClimb.whileHeld(new UseClimber(robot.climber));
        //climberDeploy.whileHeld(new DeployClimber(robot.climber));
    }
}
