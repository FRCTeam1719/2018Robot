package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UseElevator extends Command {
    
    private Elevator elevator;
    private double targetElevatorZ;
    private double actualDistance;
    private double actualVoltage;
    private double controllerZ;
    private double controllerY;
    
    private double DEADZONE = 0.05;
    private double UPWARDS_FORCE = 0.1373;
    
    /**
     * constructor that takes in an Elevator
     * 
     * @param _elevator
     */
    public UseElevator(Elevator _elevator) {
        elevator = _elevator;
        requires(elevator);
        
    }
    
    @SuppressWarnings("deprecation") // once again not deprecation
    @Override
    protected void initialize() {
        
    }
    
    @Override
    protected void execute() {
        
        controllerZ = -Robot.oi.operatorGetZ();
        //controllerY = -Robot.oi.operatorGetY();
        // System.out.println("Controller" + controllerZ);
        System.out.println("target " + targetElevatorZ);
        
        // System.out.println("In: " + controllerY);
        
        // System.out.println("Elevator Distance: " + elevator.getDistanceVoltage());
        // 1 - 5 to 0 - 1
        
        // actualDistance = elevator.getDistance();
        actualVoltage = elevator.getDistanceVoltage();
        targetElevatorZ = ((controllerZ + 1) * 2.5); // USE LATER FOR POT ELEVATOR
        /*
         * if (Math.abs(controllerY) > .05) { targetElevatorZ += controllerY / 2;
         * 
         * }
         */
        
        /*
         * setElevator(targetElevatorZ)
         * 
         * // if(elevator.getUpperLimit().get()){ // System.out.println("UPPER LIMIT");
         * // if(targetElevatorZ > actualDistance) targetElevatorZ = actualDistance; //
         * //elevatorPIDController.reset(); // }else if(elevator.getLowerLimit().get())
         * { // if(targetElevatorZ < actualDistance) targetElevatorZ = actualDistance;
         * // // System.out.println("LOWER LIMIT"); // //elevatorPIDController.reset();
         * }
         */
        if (actualVoltage < targetElevatorZ + DEADZONE) {
            elevator.moveElevator(.8);
        }else if(actualVoltage > targetElevatorZ - DEADZONE){
            elevator.moveElevator(-.8 + UPWARDS_FORCE);
        }
        // elevator.updatePID(targetElevatorZ);
        /*if (Math.abs(controllerY) < DEADZONE) {
            elevator.moveElevator(-UPWARDS_FORCE);
        } else {
            elevator.moveElevator(controllerY);
        }
        */
        SmartDashboard.putNumber("ELEVATOR_TARGET", targetElevatorZ);
        SmartDashboard.putNumber("ELEVATOR_DISTANCE", elevator.getDistanceVoltage());
        
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {}
    
    @Override
    protected void interrupted() {}
    
}
