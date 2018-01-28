package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.AbstractAutonomous2018;
import org.usfirst.frc.team1719.robot.commands.MTPTunerLoopInner;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

public class MPTTuneInner extends AbstractAutonomous2018 {
    
    public MPTTuneInner(Robot robot, Drive drive, Position position) {
        addSequential(new MTPTunerLoopInner(position, drive, robot));
    }
    
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
        // TODO Auto-generated method stub
        
    }
    
}
