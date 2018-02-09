package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.sensors.RangeFinder45LMS;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UseElevator extends Command {
    
    private Elevator elevator;
    private PIDController elevatorPIDController;
    

    
    /**
     * constructor that takes in an Elevator
     * 
     * @param _elevator
     */
    public UseElevator(Elevator _elevator) {
        elevator = _elevator;
        requires(elevator);
        SmartDashboard.putData("ELEVATOR_PID", elevator.getPIDController());

    }
    
    @SuppressWarnings("deprecation") // once again not deprecation
    @Override
    protected void initialize() {
        
    }
    
    @Override
    protected void execute() {
        
        double controllerZ = -Robot.oi.operatorGetZ();
        // System.out.println("In: " + controllerY);
        
        System.out.println("Elevator Distance: " + elevator.getDistance());
        // 1 - 5 to 0 - 1
        
        elevatorPIDController = elevator.getPIDController();
        
        double targetElevatorZ = (controllerZ * 5);
        elevatorPIDController = (PIDController) SmartDashboard.getData("ELEVATOR_PID");
        // setElevator(targetElevatorZ)
        
        if(elevator.getUpperLimit().get()){
            targetElevatorZ = 5;
            elevatorPIDController.reset();
        }else if(elevator.getLowerLimit().get()) {
            targetElevatorZ = 0;
            elevatorPIDController.reset();
        }
        
        elevator.PIDUpdate(targetElevatorZ);
        elevator.setPIDController(elevatorPIDController);

        
        
        SmartDashboard.putNumber("ELEVATOR_TARGET", targetElevatorZ);
        SmartDashboard.putNumber("ELEVATOR_DISTANCE", elevator.getDistance());
        
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
