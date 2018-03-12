package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The subsystem that runs the rolling intake/outtake.
 *
 * @author Quintin
 */
public class RollerIntake extends Subsystem {
    
    private SpeedController motor;
    
    /**
     * Receive the controllers that control the intakes.
     * 
     * @param _motor - Speed controller for left intake
     * @param _rightIntake - Speed controller for right intake
     */
    public RollerIntake(SpeedController _motor) {
        motor = _motor;
    }
    
    /**
     * Set the speed of the intake for the roller intake
     * 
     * @param speed - speed to spin the intake
     */
    public void set(double speed) {
        motor.set(speed);
    }
    /**
     * Reset the intake speed to 0
     */
    public void reset() {
        motor.set(0);
    }
    
    public void initDefaultCommand() {}
}
