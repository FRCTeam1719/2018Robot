package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Mydrive;

import edu.wpi.first.wpilibj.command.Command;

public class Drivecommand extends Command{
	
	Mydrive mydrive;
	
	public Drivecommand(Mydrive m) {
		mydrive = m;
	}
	
	
	protected void execute() {
		mydrive.setRight(Robot.oi.getRightX());
		mydrive.setLeft(Robot.oi.getLeftX());
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

