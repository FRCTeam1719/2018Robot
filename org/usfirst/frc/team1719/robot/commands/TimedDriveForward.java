package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedDriveForward extends Command {

    private Timer timer;
    
    private Drive drive;
    private long millis;
    private double speed;
    
    public TimedDriveForward(Drive _drive, double _speed, long _millis) {
        timer = new Timer();
        
        millis = _millis;
        speed = _speed;
        drive = _drive;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.tankDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() * 1000) > millis;
    }
}
