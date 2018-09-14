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
import org.usfirst.frc.team1719.robot.subsystems.RollerIntake;

public class LeftAutonomous extends AbstractAutonomous2018 { 
    private Drive drive;
    private Position position;
    private Claw claw;
    private RollerIntake intake;
    /**
     * Left auto mode.
     * @param _drive
     * @param _position
     * @param elevator
     * @param _claw
     * @param _intake
     */
    public LeftAutonomous(Drive _drive, Position _position, Elevator elevator, Claw _claw, RollerIntake _intake) {
        drive = _drive;
        position = _position;
        claw = _claw;
        intake = _intake;
        
        addParallel(new SetElevatorPosition(elevator, 2.0));
        //addParallel(new UseIntake(intake, 0.33D));
    }
    
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
        
        /* true: right */
        if (ownSwitch) {
            addSequential(new TimedDriveForward(drive, 0.5, 7.0));
        } else {
            addSequential(new MoveToPosition(0, 60, position, drive, true, true));
            addSequential(new TurnToAngle(0, position, drive));
            addSequential(new TimedDriveForward(drive, 0.5, 1.5));
            
            //addSequential(new TimedUseIntake(intake, -1.0D, 1.0D));
            addSequential(new OpenClaw(claw));

        }
    }
    
}
