package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Simple Wrist subsystem for the robot.
 * 
 * @author gusg21
 *
 */
public class Wrist extends Subsystem {

    private Solenoid wristSolenoid;
    
    public Wrist(Solenoid _wristSolenoid) {
        wristSolenoid = _wristSolenoid;
    }

    public void initDefaultCommand() {}
    
    /**
     * Put the wrist up.
     * 
     */
    public void putUp() {
        wristSolenoid.set(false);
    }
    
    /**
     * Put the wrist down.
     * 
     */
    
    public void putDown() {
        wristSolenoid.set(true);
    }
    
    /**
     * Set the state of the wrist
     * 
     * @param wrist
     */
    public void put(boolean wrist) {
        wristSolenoid.set(wrist);
    }
}