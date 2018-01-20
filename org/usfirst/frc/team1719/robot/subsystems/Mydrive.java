package org.usfirst.frc.team1719.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Mydrive extends Subsystem {
	private SpeedController left;
	private SpeedController right;
	
	
	public Mydrive(SpeedController left, SpeedController right ) {
		this.left = left;
		this.right= right;
				
	}
	public void setLeft(double speed) {
		left.set(speed);
		
	}
	public void setRight(double speed) {
		right.set(speed);
	}
	public double getLeftPower() {
		return left.get();
	}
	public double getRightPower() {
		return right.get();
	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	} 

}
