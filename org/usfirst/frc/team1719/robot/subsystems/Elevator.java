package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseElevator;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Elevator subsystem for controlling the elevator
 * 
 * @author bennyrubin
 *
 */
public class Elevator extends Subsystem {
    private IEncoder positionEncoder;
    private SpeedController elevatorController;
    
    /**
     * Takes in an Encoder for the position, a motor to control it, and two switches
     * for when it reaches the top and bottom of the elevator
     * 
     * @param position
     *            - Encoder for current position of the elevator
     * @param top
     *            - top limit switch
     * @param bottom
     *            - bottom limit switch
     * @param speedController
     *            - elevator speed controller
     */
    public Elevator(IEncoder position, SpeedController _elevatorController) {
        positionEncoder = position;
        elevatorController = _elevatorController;
        
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseElevator(this));
    }
    
    /**
     * takes a speed and sets the motor to it
     * 
     * @param speed
     *            - speed to set motor at
     */
    public void moveElevator(double speed) {
        if (speed < -1) {
            speed = -1;
        }
        if (speed > 1) {
            speed = 1;
        }
        
        elevatorController.set(speed / 2);
        /* System.out.println(speed / 2); */
        
    }
    
    /**
     * stops the elevator from moving
     */
    public void stop() {
        elevatorController.set(0);
    }
    
}
