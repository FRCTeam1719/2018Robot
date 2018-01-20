package org.usfirst.frc.team1719.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The parent class for all autonomous modes in the 2018 competition season. Needed in order to
 * communicate the field state properly.
 * 
 * @author Duncan
 */
public abstract class AbstractAutonomous2018 extends CommandGroup {
    /**
     * Called by {@code Robot.autonomousInit()} in order to communicate the field's state 
     * (the chirality of the switches and scale). A {@code true} value represents the friendly
     * side of the goal in question being on our right, while a {@code false} value represents
     * it being on our left.
     * @param ownSwitch the chirality of our alliance's switch
     * @param scale the chirality of the scale
     * @param oppSwitch the chirality of the opposing alliance's switch
     */
    public abstract void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch);
}
