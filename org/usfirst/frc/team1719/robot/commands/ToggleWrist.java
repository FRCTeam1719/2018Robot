package org.usfirst.frc.team1719.robot.commands;

import org.usfirst.frc.team1719.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.command.Command;

/**
 * command for toggling the wrist
 * @author bennyrubin
 *
 */
public class ToggleWrist extends Command{
    Wrist wrist;
    boolean state;
    /**
     * takes a wrist and sets it to the wrist
     * @param _wrist
     */
    public ToggleWrist(Wrist _wrist) {
        wrist = _wrist;
        requires(wrist);
    }

    protected void initialize() {
    }
    
    /**
     * toggles the wrist
     */
    protected void execute() {
       wrist.toggle();
    }
    
    
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
