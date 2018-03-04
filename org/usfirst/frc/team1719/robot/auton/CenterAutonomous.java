package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.commands.OpenClaw;
import org.usfirst.frc.team1719.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team1719.robot.commands.TimedDriveForward;
import org.usfirst.frc.team1719.robot.commands.TurnToAngle;
import org.usfirst.frc.team1719.robot.subsystems.Claw;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;
import org.usfirst.frc.team1719.robot.subsystems.Position;

public class CenterAutonomous extends AbstractAutonomous2018 {
    
    private Drive drive;
    private Position position;
    private Claw claw;
    
    public CenterAutonomous(Drive _drive, Position _position, Elevator elevator, Claw _claw) {
        drive = _drive;
        position = _position;
        claw = _claw;
        
        addParallel(new SetElevatorPosition(elevator, 2.0));
    }
    
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
        
        /* true: right */
        if (ownSwitch) {
            addSequential(new MoveToPosition(28, 65, position, drive, true, true));
            addSequential(new TurnToAngle(0, position, drive));
            addSequential(new TimedDriveForward(drive, 0.5, 1.5));
        } else {
            addSequential(new MoveToPosition(-45, 65, position, drive, true, true));
            addSequential(new TurnToAngle(0, position, drive));
            addSequential(new TimedDriveForward(drive, 0.5, 1.5));
        }
        
        addSequential(new OpenClaw(claw));
    }
    
}
