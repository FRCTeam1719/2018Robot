package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * The servo that holds the claw up when we aren't using it.
 * 
 * @author gus
 *
 */
public class ClawHolder extends Subsystem {

    Servo clawHolder;
    
    /**
     * 
     * The constructor for the ClawHolder. 
     * 
     * @param _clawHolder - Servo for the claw holder
     *
     */
    public ClawHolder(Servo _clawHolder) {
        clawHolder = _clawHolder;
    }
    
    /**
     * 
     * Put the servo out, holding the claw up.
     * 
     */
    public void hold() {
        clawHolder.set(1.0D);
    }
    
    /**
     * 
     * Pull the servo back, letting the claw down.
     * 
     */
    public void release() {
        clawHolder.set(0.0D);
    }

    @Override
    protected void initDefaultCommand() {} // Nothing needed here
}

