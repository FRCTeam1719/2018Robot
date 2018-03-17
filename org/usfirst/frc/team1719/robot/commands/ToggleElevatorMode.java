package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleElevatorMode extends InstantCommand {
    
    private Elevator elevator;
    public ToggleElevatorMode(Elevator _elevator) {
        super();
        elevator = _elevator;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        }

    // Called once when the command executes
    protected void initialize() {
        elevator.elevatorOverride = !elevator.elevatorOverride;
    }
    

}
