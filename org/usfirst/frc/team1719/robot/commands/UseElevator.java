package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class UseElevator extends Command {

	private Elevator elevator;
	private final double DEADZONE = 0.5;
	
	/**
	 * constructor that takes in an Elevator
	 * @param _elevator
	 */
	public UseElevator(Elevator _elevator) {
		elevator = _elevator;
		requires(elevator);
	}
	@Override
	protected void initialize() {
    }
	@Override
    protected void execute() {
	   
    		double controllerY = -Robot.oi.operatorGetY();
    		//System.out.println("In: " + controllerY);
    		if(Math.abs(controllerY)<DEADZONE) {
    			controllerY = 0;
    		}
    		
    		elevator.moveElevator(controllerY);
    		
    } 
    
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void end() {
    }
	@Override
    protected void interrupted() {
    }

}
