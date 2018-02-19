package org.usfirst.frc.team1719.robot.auton;

import org.usfirst.frc.team1719.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class LeftAutonomous extends AbstractAutonomous2018 {
    
    private boolean ownSwitch;
    private boolean scale;
    private boolean oppSwitch;
    
    private Drive drive;
    
    public LeftAutonomous(Drive _drive) {
        drive = _drive;
        
        /*if ownSwitch
        addSequential(new Command() {
            
            @Override
            protected void initialize() {
                drive.arcadeDrive(speed, rotation);
            }
            
            @Override
            protected void execute() {
                
            }
            
            @Override
            protected boolean isFinished() {
                return false;
            }
            
        });*/
    }
    
    @Override
    public void setFieldState(boolean _ownSwitch, boolean _scale, boolean _oppSwitch) {
        ownSwitch = _ownSwitch;
        scale = _scale;
        oppSwitch = _oppSwitch;
    }
    
}
