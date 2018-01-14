package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RollerIntake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private SpeedController leftIntake;
    private SpeedController rightIntake;
    
    
        public RollerIntake(SpeedController _leftIntake, SpeedController _rightIntake) {
            leftIntake = _leftIntake;
            rightIntake = _rightIntake;
        }
        
        public void set(double speed) {
            leftIntake.set(speed);
            rightIntake.set(speed);
            
        }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

