package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.commands.MoveToPosition;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

/**
 *
 */
public class MTPTest extends AbstractAutonomous2018 {

    /**
     * Move to Position Test
     * 
     * @param robot - Our robot
     * @param drive - drive subsystem
     * @param position - navX positioning.
     */
    public MTPTest(Robot robot, Drive drive, Position position) {
        addSequential(new MoveToPosition(0.0D, 0.0D, position, drive, true, false));
    }

    protected void initialize() {
        
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    /* IGNORE */
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {}
}
