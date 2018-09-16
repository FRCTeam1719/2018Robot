package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.RobotMap;
import org.usfirst.frc.team1719.robot.subsystems.Claw;
import org.usfirst.frc.team1719.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Original attempt at start-of-AUTO claw deployment.
 * @deprecated We use a line in {@link Robot#autonomousInit()} instead.
 */
@Deprecated
public class ExtendGrabLift extends Command {

    Wrist wristSystem;
    Claw clawSystem;
    
    boolean done = false;
    
    public ExtendGrabLift() {
        wristSystem = new Wrist(RobotMap.wristSolenoid);
        requires(wristSystem);
        
        clawSystem = new Claw(RobotMap.clawSolenoid);
        requires(clawSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        wristSystem.putDown();
        clawSystem.close();
        //elevator lift!!
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
