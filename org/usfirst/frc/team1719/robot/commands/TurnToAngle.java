package org.usfirst.frc.team1719.robot.commands;

import java.util.ArrayDeque;
import java.util.Queue;

import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command implements PIDSource, PIDOutput {

    private static final double TOLERANCE = 2.0D;

    private static final int MOV_AVG_COUNT = 50;
    
    private final double targetHeading;
    private final Position posTracker;
    private final Drive drive;
    
    private PIDController pid;
    
    private double pidout;
    
    public TurnToAngle(double _targetHeading, Position _posTracker, Drive _drive) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        targetHeading = _targetHeading;
        posTracker = _posTracker;
        drive = _drive;
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pid = new PIDController(SmartDashboard.getNumber("TurnToHeading K[P]", 0.02),
                SmartDashboard.getNumber("TurnToHeading K[I]", 0.006),
                SmartDashboard.getNumber("TurnToHeading K[D]", 0.1), this, this) {
            /* Hack -- use RMSE for onTarget */
            @Override
            protected void calculate() {
                _updateRMSE_hack();
                super.calculate();
            }
            @Override
            public synchronized double getAvgError() {
                return _getRMSE_hack();
            }
        };
        pid.setInputRange(-180.0D, 180.0D);
        pid.setContinuous();
        pid.setOutputRange(-1.0D, 1.0D);
        pid.setSetpoint(targetHeading);
        pid.setAbsoluteTolerance(TOLERANCE);
        pid.setToleranceBuffer(MOV_AVG_COUNT);
        pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
        if(true) {//oi.getResetPIDConstants()) {
            pid.setPID(SmartDashboard.getNumber("TurnToHeading K[P]", 0.2),
                    SmartDashboard.getNumber("TurnToHeading K[I]", 0.006),
                    SmartDashboard.getNumber("TurnToHeading K[D]", 0.1));
        }
        if(!pid.isEnabled()) {
            pid.enable();
        }
        System.out.println("Turn to heading power: " + pidout);
        SmartDashboard.putNumber("TTH err", pid.getError());
        drive.tankDrive(pidout, -pidout);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.reset();
        drive.setShift(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        pid.reset();
        drive.setShift(false);
    }

    @Override
    public void pidWrite(double output) {
        pidout = output;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
        return posTracker.getHeading();
    }
    
    /* Hack -- use RMSE for onTarget() */
    private Queue<Double> errs = new ArrayDeque<Double>(MOV_AVG_COUNT + 1);
    private double sqerr = 0.0D;
    
    private synchronized double _getRMSE_hack() {
        return Math.sqrt(sqerr / errs.size());
    }
    
    private synchronized void _updateRMSE_hack() {
        double err = pid.getError();
        errs.add(err);
        sqerr += err * err;
        if(errs.size() > MOV_AVG_COUNT) {
            double staleerr = errs.remove();
            sqerr -= staleerr * staleerr;
        }
    }
}