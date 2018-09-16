package org.usfirst.frc.team1719.robot.controllers;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Attack3Joystick extends GenericOperator{
    
    public Attack3Joystick() {
        wristButton = 2;
        climberDeploy = 8;
        climberClimb = 9;
        elevatorToggleButton = 10;
        rollerIn = 4;
        rollerOut = 5;
        testAutonFire = 8;
        openClaw = 4;
        closeClaw = 5;
        toggleClaw = 3;
        xAxis = 0;
        yAxis = 1;
        zAxis = 2;
    }
    
}

/*
Button wristButton = new JoystickButton(operator, 2);
//Button climberDeploy = new JoystickButton(operator, 8);
//Button climberClimb = new JoystickButton(operator, 9);
Button shiftLowButton = new JoystickButton(driver, driverControl.shiftLowButton);
Button shiftHighButton = new JoystickButton(driver, driverControl.shiftHighButton);
Button elevatorToggleButton = new JoystickButton(operator, 10);
//Button rollerIn = new JoystickButton(operator, 4);
//Button rollerOut = new JoystickButton(operator, 5);
Button testAutonFire = new JoystickButton(operator, 8);
Button openClaw = new JoystickButton(operator, 4);
Button closeClaw = new JoystickButton(operator, 5);
Button toggleClaw = new JoystickButton(operator, 3);
*/