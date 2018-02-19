package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

/**
 * Auton for getting to the switch
 * @author BennyRubin
 *
 */
public class AutoSwitchCr extends AbstractAutonomous2018{
    
    public AutoSwitchCr(Position _position, Drive _drive) {
        addSequential(new MoveToPosition(55, 100, _position, _drive, false, true));
    }
    
    
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
        // TODO Auto-generated method stub
        
    }
    
}
