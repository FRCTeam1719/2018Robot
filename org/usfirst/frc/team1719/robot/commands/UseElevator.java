package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.Robot;
import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Runs the elevator using the operator console fader
 * 
 * @author bennyrubin, Quintin
 *
 */
public class UseElevator extends Command {
    
    private Elevator elevator;
    private double targetElevatorZ;
    private double actualDistance;
    private double actualVoltage;
    private boolean lastOverride;
    private double controllerZ;
    private double controllerY;
    private double proportional;
    private double DEADZONE = 0.15;
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
        targetElevatorZ = elevator.getDistanceVoltage();
    }
    
    @Override
    protected void execute() {
        controllerZ = Robot.oi.operatorGetZ();
        controllerY = -Robot.oi.operatorGetY();
        actualVoltage = elevator.getDistanceVoltage();
        if (!elevator.elevatorOverride) {
            if (lastOverride != elevator.elevatorOverride) targetElevatorZ = elevator.getDistanceVoltage();
            if (Math.abs(controllerY) > .02) {
                targetElevatorZ -= controllerY / 20;
            }
            if (actualVoltage < targetElevatorZ - DEADZONE) {
                elevator.moveElevator(-.75); // move up
            } else if (actualVoltage > targetElevatorZ + DEADZONE) {
                elevator.moveElevator(.5 + UPWARDS_FORCE); // move down
            } else {
                elevator.moveElevator(-UPWARDS_FORCE);
            }
            targetElevatorZ = Math.max(Math.min(targetElevatorZ, 5), 0);
        } else if (elevator.elevatorOverride == true) { // If we override use manual control
            if (controllerY >= 0.1D) {
                elevator.moveElevator(Math.max(Math.min(((controllerY)) + UPWARDS_FORCE, 1), -1));
            }else {
                elevator.moveElevator(UPWARDS_FORCE); // Try to keep steady.
            }
        }
        SmartDashboard.putNumber("ELEVATOR_TARGET", targetElevatorZ);
        SmartDashboard.putNumber("ELEVATOR_DISTANCE", elevator.getDistanceVoltage());
        lastOverride = elevator.elevatorOverride;
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
