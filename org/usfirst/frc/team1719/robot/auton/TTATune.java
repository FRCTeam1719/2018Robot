package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.commands.TurnToAngle;
import org.usfirst.frc.team1719.robot.subsystems.Drive;
import org.usfirst.frc.team1719.robot.subsystems.Position;

/**
 *
 */
public class TTATune extends AbstractAutonomous2018 {
    public TTATune(Position position, Drive drive) {
        addSequential(new TurnToAngle(0.0D, position, drive));
    }

    @Override
    public void setFieldState(boolean ownSwitch, boolean scale, boolean oppSwitch) {}
}
