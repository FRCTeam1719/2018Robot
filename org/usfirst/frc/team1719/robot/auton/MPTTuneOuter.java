package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.MTPTunerLoopOuter;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

public class MPTTuneOuter extends AbstractAutonomous2018 {
    
    public MPTTuneOuter(Robot robot, Drive drive, Position position, double desiredX, double desiredY) {
        addSequential(new MTPTunerLoopOuter(desiredX, desiredY, position, drive, robot));
    }
    
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {}
    
}
