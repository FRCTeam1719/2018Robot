package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedUseIntake extends TimedCommand {

    private RollerIntake intake;
    private double speed;
    
    public TimedUseIntake(RollerIntake _intake, double _speed, double timeout) {
        super(timeout);
        intake = _intake;
        speed = _speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        intake.set(speed);
    }
}
