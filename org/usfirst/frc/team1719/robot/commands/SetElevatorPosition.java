package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Sets elevator to position
 * @author Quintin Forrer
 *
 */
public class SetElevatorPosition extends Command {
    
    private Elevator elevator;
    private double targetElevatorZ;
    private double actualVoltage;
    private double DEADZONE = 0.15;
    private double UPWARDS_FORCE = 0.1373;

    public SetElevatorPosition(Elevator _elevator, double _target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        elevator = _elevator;
        targetElevatorZ = _target;
        requires(elevator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (actualVoltage < targetElevatorZ - DEADZONE) {
            elevator.moveElevator(-.6); //move up
        }else if(actualVoltage > targetElevatorZ + DEADZONE){
            elevator.moveElevator(.4 + UPWARDS_FORCE); //move down
        } else {
            elevator.moveElevator(-UPWARDS_FORCE);
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

}
