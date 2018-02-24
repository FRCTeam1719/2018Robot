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

    private static final double TOLERANCE = 17.0D;

    private static final int MOV_AVG_COUNT = 50;
    
    private final double targetHeading;
    private final Position posTracker;
    private final Drive drive;
    
    private PIDController pid;
    
    private double pidout;
    
    public TurnToAngle(double _targetHeading, Position _posTracker, Drive _drive) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        SmartDashboard.putBoolean("NaN_staleerr", false);
        SmartDashboard.putBoolean("NaN_err", false);
        targetHeading = _targetHeading;
        posTracker = _posTracker;
        drive = _drive;
        requires(drive);
        pid = new PIDController(0.015, 0.0, 0.001, this, this) {
            /* Hack -- use RMSE for onTarget */
            @Override
            protected void calculate() {
                _updateRMSE_hack();
                super.calculate();
            }
        };        
        pid.setInputRange(-180.0D, 180.0D);
        pid.setContinuous();
        pid.setOutputRange(-1.0D, 1.0D);
        pid.setSetpoint(targetHeading);
        SmartDashboard.putData("TTA_PID", pid);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putBoolean("TTA Running", true);

        pid.enable();
        SmartDashboard.putData("TTA_PID", pid);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { 
        pid = (PIDController) SmartDashboard.getData("TTA_PID");
        if(!pid.isEnabled()) {
            pid.enable();
        }
        System.out.println("Turn to heading power: " + pidout);
        SmartDashboard.putNumber("TTH heading", posTracker.getHeading());
        drive.tankDrive(pidout, -pidout);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        System.out.println("sqerr : " + sqerr);
        System.out.println("RMSE_hack : " + _getRMSE_hack());
        return _getRMSE_hack() < TOLERANCE;
    }

    // Called once after isFinished returns true
    protected void end() {
        pid.reset();
        drive.setShift(false);
        pid.disable();
        SmartDashboard.putBoolean("TTA Running", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    @Override
    public void pidWrite(double output) {
        System.out.println("PIDOUT : " + output);
        pidout = output + 0.05 * Math.signum(output);
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
        if(Double.isNaN(err)) SmartDashboard.putBoolean("NaN_err", true);
        errs.add(err);
        sqerr += err * err;
        if(errs.size() > MOV_AVG_COUNT) {
            double staleerr = errs.remove();
            if(Double.isNaN(staleerr)) SmartDashboard.putBoolean("NaN_staleerr", true);
            sqerr -= staleerr * staleerr;
        }
    }
}