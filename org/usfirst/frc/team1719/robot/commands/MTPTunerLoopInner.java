package org.usfirst.frc.team1719.robot.commands;


import org.usfirst.frc.team1719.robot.OI;
import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MTPTunerLoopInner extends Command implements PIDSource, PIDOutput {


    private static final double SPD = 0.5D;
    
    private Position posTracker;
    private Drive drive;
    private Robot robot;
    private OI oi;
    private PIDController rotateController;
    
    private double targetAngle;
    private double rotSpd;
    
    public MTPTunerLoopInner(Position _posTracker, Drive _drive, Robot _robot) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        posTracker = _posTracker;
        robot = _robot;
        drive = _drive;
        oi = robot.oi;
        requires( (Subsystem) drive);
        SmartDashboard.putNumber("MTPLT1 target angle", targetAngle = 0.0D);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        rotateController = new PIDController(0, 0, 0, this, this);
        rotateController.setPID(SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04),
                SmartDashboard.getNumber("MoveToPos K[1][I]", 0),
                SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1));
        rotateController.setSetpoint(0);
        rotateController.setInputRange(-180.0D,  180.0D);
        rotateController.setContinuous();
        rotateController.setOutputRange(-1.0D, 1.0D);
        rotateController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(true){//oi.getResetPIDConstants()) {
            double p = SmartDashboard.getNumber("MoveToPos K[1][P]", 0.04);
            double i = SmartDashboard.getNumber("MoveToPos K[1][I]", 0);
            double d = SmartDashboard.getNumber("MoveToPos K[1][D]", 0.1);
            rotateController.setPID(p, i, d);
            System.out.println("Updating PID constants: " + p + "/" + i + "/" + d);
        }
        targetAngle = SmartDashboard.getNumber("MTPLT1 target angle", targetAngle);
        drive.arcadeDrive(SPD, -rotSpd);
        SmartDashboard.putNumber("MTP Desired angle", targetAngle);
        SmartDashboard.putNumber("MTP current angle", posTracker.getHeading());
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


    @Override
    public void pidWrite(double output) {
        rotSpd = output;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {}

    @Override
    public PIDSourceType getPIDSourceType() {return PIDSourceType.kDisplacement;}

    @Override
    public double pidGet() {
        return posTracker.getHeading() - targetAngle;
    }
}