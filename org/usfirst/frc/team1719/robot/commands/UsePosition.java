package org.usfirst.frc.team1719.robot.commands;


import org.usfirst.frc.team1719.robot.subsystems.Position;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Simple Position Command
 * 
 * @author Duncan
 * @author Gus
 *
 */
public class UsePosition extends Command {
    
    private Position parent;
    
    /**
     * Simple constructor
     * 
     * @param _parent
     *            the Position subsystem this command updates.
     */
    public UsePosition(Position _parent) {
        parent = _parent;
        
        requires(parent);
    }
    
    @Override
    protected void initialize() {}
    
    @Override
    protected void execute() {
        parent.update();
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
