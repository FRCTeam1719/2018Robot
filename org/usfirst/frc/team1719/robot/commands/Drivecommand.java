package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Mydrive;

import edu.wpi.first.wpilibj.command.Command;

public class Drivecommand extends Command{
	
	Mydrive mydrive;
	private final double JOYSTICK_TOLERANCE = 0.1;
	
	public Drivecommand(Mydrive m) {
		mydrive = m;
		requires(mydrive);
	}
	
	
	protected void execute() {
		double rightJoy = Robot.oi.getRightY(); // 1
		double leftJoy = Robot.oi.getLeftY(); // 0
		
		double difference = Math.abs(rightJoy - leftJoy); //1
		
		
		if(difference <  JOYSTICK_TOLERANCE) {
			leftJoy = rightJoy;
//			mydrive.setRight(leftJoy);
//			mydrive.setRight(rightJoy);
			
		}else if(Math.abs(rightJoy) < 0.1) {
			rightJoy = 0;
			
		}
		else if(Math.abs(leftJoy)<0.1) {
			leftJoy = 0;
		}
		
		mydrive.setRight(rightJoy); 
		mydrive.setLeft(leftJoy);
			
		
		
		
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

