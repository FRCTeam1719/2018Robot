
package org.usfirst.frc.team1719.robot;

import org.usfirst.frc.team1719.robot.auton.AbstractAutonomous2018;
import org.usfirst.frc.team1719.robot.auton.CenterAutonomous;
import org.usfirst.frc.team1719.robot.auton.LeftAutonomous;
import org.usfirst.frc.team1719.robot.auton.RightAutonomous;
import org.usfirst.frc.team1719.robot.auton.TTATune;
import org.usfirst.frc.team1719.robot.commands.TimedDriveForward;
import org.usfirst.frc.team1719.robot.subsystems.Claw;
import org.usfirst.frc.team1719.robot.subsystems.ClawHolder;
import org.usfirst.frc.team1719.robot.subsystems.Climber;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;
import org.usfirst.frc.team1719.robot.subsystems.Position;
import org.usfirst.frc.team1719.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * An object to contain all joysticks used.
     */
	public static OI oi;
	private Compressor compressor;
	private AbstractAutonomous2018 autonomousCommand;
	private AbstractAutonomous2018[] autoList = new AbstractAutonomous2018[4];
	
	private String compressorInfo = "Loading, please wait...";
	
    Drive drive;
	Position position;
	Elevator elevator;
	Claw claw;
	ClawHolder clawHolder;
	Climber climber;
	Wrist wrist;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		compressor.start();
		
		/* Initialize Subsystems */
		drive = new Drive(RobotMap.leftDrive, RobotMap.rightDrive, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc, RobotMap.shifterSolenoid);
		position = new Position(RobotMap.navx, RobotMap.leftDriveEnc, RobotMap.rightDriveEnc);
		//elevator = new Elevator(RobotMap.elevator, null, null, null);
		//claw = new Claw(RobotMap.clawSolenoid,null);


		elevator = new Elevator(RobotMap.elevator, RobotMap.rangeFinder, RobotMap.upperLimit, RobotMap.lowerLimit);	
		claw = new Claw(RobotMap.clawSolenoid, RobotMap.wristSolenoid);
		//clawHolder = new ClawHolder(RobotMap.clawHolder);
		climber = new Climber(RobotMap.climberMotor);
		wrist = new Wrist(RobotMap.wristSolenoid);

		/* Autonomous chooser */
//		chooser.addDefault("Goto 0,0", new MTPTest(this, drive, position));
//		chooser.addObject("Tune Inner", new MPTTuneInner(this, drive, position));
//		chooser.addObject("Tune Outer", new MPTTuneOuter(this, drive, position, 0D, 10D));
	
		
		autoList[2] = new LeftAutonomous(drive, position, elevator, claw);
		autoList[0] = new CenterAutonomous(drive, position, elevator, claw);
		autoList[1] = new RightAutonomous(drive, position, elevator, claw);
		autoList[3] = new AbstractAutonomous2018() {
            
            @Override
            public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {
                addSequential(new TimedDriveForward(drive, 0.5, 10.0));
            }
        };

		oi.init(this);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	    //clawHolder.release();
	    wrist.putDown();
	    
		autonomousCommand = autoList[(RobotMap.autoSel[0].get() ? 0 : 2) + (RobotMap.autoSel[1].get() ? 0 : 1)];

		/* Schedule the autonomous command */
		if (autonomousCommand != null) {
		    /* Note from Aaron: 
		     * Consider how this will act when not connected to the FMS, or if the message is somehow garbeled.
		     * Look at isFMSAttached() and maybe consider getting the data from the dashboard otherwise?
		     * Also, add some error handling to that string parsing. */
            String data = DriverStation.getInstance().getGameSpecificMessage();
            if(data.length() > 2) {
                autonomousCommand.setFieldState(data.charAt(0) == 'R', data.charAt(1) == 'R', data.charAt(2) == 'R');
            }
            autonomousCommand.start();
        }
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	    //clawHolder.release();
	    wrist.putDown();
	    
		/* End autonomous */
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
        SmartDashboard.putNumber("MTP current angle", position.getHeading());
        if (compressor.enabled()) {
            compressorInfo = "PNEUMATICS CHARGING";
        } else {
            compressorInfo = "GOOD TO GO";
        }  

        SmartDashboard.putString("compressor", compressorInfo);
	}

	/**
	 * This function is called periodically during test mode. Calls a deprecated method,
	 * but we never use it anyway
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
