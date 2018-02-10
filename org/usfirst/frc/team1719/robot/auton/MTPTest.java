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
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new MoveToPosition(0.0D, 0.0D, position, drive, true, false));
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    /* IGNORE */
    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
        // TODO Auto-generated method stub

    }
}
