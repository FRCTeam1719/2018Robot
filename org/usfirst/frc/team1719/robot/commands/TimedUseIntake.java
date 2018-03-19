package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.RollerIntake;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *@author quintin
 */
public class TimedUseIntake extends TimedCommand {

    private RollerIntake intake;
    private double speed;
    /**
     * Spin the intake for a time at a speed.
     * @param Intake
     * @param Speed
     * @param Timeout
     */
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
    protected void end() {
        intake.set(0.33D);
    }
}
