package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UseIntake extends Command {

    private RollerIntake intake;
    private double intakeSpeed;
    
    public UseIntake(RollerIntake _intake, double _intakeSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
            intake = _intake;
            intakeSpeed = _intakeSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        intake.set(intakeSpeed);
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
}
