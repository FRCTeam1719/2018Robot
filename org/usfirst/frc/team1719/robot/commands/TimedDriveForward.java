package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class TimedDriveForward extends TimedCommand {
    
    private Drive drive;
    private double speed;
    /**
     * Drive forward on a timer.
     * @param _drive
     * @param _speed
     * @param time
     */
    public TimedDriveForward(Drive _drive, double _speed, double time) {
        super(time);
        speed = _speed;
        drive = _drive;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        drive.tankDrive(speed, speed);
    }
}
