package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem that runs the rolling intake/outtake.
 *
 * @author Quintin
 */
public class RollerIntake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private SpeedController leftIntake;
    private SpeedController rightIntake;
    
    /**
     * Receive the controllers that control the intakes.
     * 
     * @param _leftIntake - Speed controller for left intake
     * @param _rightIntake - Speed controller for right intake
     */
    public RollerIntake(SpeedController _leftIntake, SpeedController _rightIntake) {
        leftIntake = _leftIntake;
        rightIntake = _rightIntake;
    }
    
    /**
     * Set the speed of the intake for the roller intake
     * 
     * @param speed - speed to spin the intake
     */
    public void set(double speed) {
        leftIntake.set(speed);
        rightIntake.set(speed);
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
