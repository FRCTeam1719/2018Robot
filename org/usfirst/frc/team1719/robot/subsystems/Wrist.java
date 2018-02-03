package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

    private Solenoid wristSolenoid;
    
    public Wrist(Solenoid _wristSolenoid) {
        wristSolenoid = _wristSolenoid;
    }

    public void initDefaultCommand() {}
    
    public void putUp() {
        wristSolenoid.set(false);
    }
    
    public void putDown() {
        wristSolenoid.set(true);
    }
}