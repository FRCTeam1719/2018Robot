package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UseElevator extends Command {

	private Elevator elevator;
	PIDController elevatorController;
	private final double DEADZONE = 0.5;
	
	
	
	private double kP = 0;
	private double kD = 0;
	private double kF = 0;
	
	volatile double elevatorOutput = 0;
	
	private class elevatorPIDOut implements PIDOutput{
	    
	    /**
	     * Write the values of the PID controller to the output variable.
	     * @param output
	     */
	        public void pidWrite(double output) {
	            elevatorOutput = output;
	        }
	}
	
	/**
	 * constructor that takes in an Elevator
	 * @param _elevator
	 */
	public UseElevator(Elevator _elevator) {
		elevator = _elevator;
		requires(elevator);
		
		elevator.getRangeFinder().setPIDSourceType(PIDSourceType.kRate);
		
		elevatorController = new PIDController(kP, 0, kF, elevator.getRangeFinder(), new elevatorPIDOut());
		
	}
	@SuppressWarnings("deprecation") //once again not deprecation
    @Override
	protected void initialize() {
	    elevator.getRangeFinder().setPIDSourceType(PIDSourceType.kRate);
	    elevatorController.setOutputRange(-1, 1);
	    
	    elevatorController.setContinuous(false);
	    
	    elevatorController.setToleranceBuffer(20);
	    
	    elevatorController.setPercentTolerance(5);
	    
	    SmartDashboard.putData("ELEVATOR_PID", elevatorController);
	    SmartDashboard.putNumber("Elevator Height", elevator.getRangeFinder().distance());
	    
	    elevatorController.enable();
	    
    }
	@Override
    protected void execute() {
	   
    		double controllerY = -Robot.oi.operatorGetZ();
    		//System.out.println("In: " + controllerY);
    		
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
