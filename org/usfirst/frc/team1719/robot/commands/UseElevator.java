package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class UseElevator extends Command {

	private Elevator elevator;
	private final double DEADZONE = 0.1;
	
	/**
	 * constructor that takes in an Elevator
	 * @param _elevator
	 */
	public UseElevator(Elevator _elevator) {
		elevator = _elevator;
		requires(elevator);
	}
	
	protected void initialize() {
    }

    protected void execute() {
    		double controllerY = -Robot.oi.operatorGetY();
    		
    		if(Math.abs(controllerY)<DEADZONE) {
    			controllerY = 0;
    		}
    		
    		elevator.moveElevator(controllerY);
    		
    } 
    
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
    }

    protected void interrupted() {
    }

}
