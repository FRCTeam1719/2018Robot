package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.commands.TimedDriveForward;
import org.usfirst.frc.team1719.robot.commands.TurnToAngle;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

public class LeftAutonomous extends AbstractAutonomous2018 {
    
    private boolean ownSwitch;
    private boolean scale;
    private boolean oppSwitch;
    
    private Drive drive;
    private Position position;
    
    public LeftAutonomous(Drive _drive, Position _position) {
        drive = _drive;
        position = _position;
    }
    
    @Override
    public void setFieldState(boolean _ownSwitch, boolean _scale, boolean _oppSwitch) {
        ownSwitch = _ownSwitch;
        scale = _scale;
        oppSwitch = _oppSwitch;
        
        /* true: right */
        if (ownSwitch) {
            addSequential(new TimedDriveForward(drive, 0.5, 7.0));
        } else {
            addSequential(new MoveToPosition(0, 50, position, drive, true, true));
            addSequential(new TurnToAngle(0, position, drive));
            addSequential(new TimedDriveForward(drive, 0.5, 1.5));
        }
    }
    
}
