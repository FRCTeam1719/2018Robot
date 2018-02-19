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
    
    /**
     * 
     * @param _wristSolenoid
     *            - solenoid for wrist
     */
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
    
    /**
     * toggles the wrist solenoid
     * @author Benny Rubin
     */
    public void toggle() {
        wristSolenoid.set(!(wristSolenoid.get()));
    }
    /**
     * Get the state of the wrist
     * 
     * @return boolean - wrist state
     */
    public boolean getState() {
        return wristSolenoid.get();
    }
}
