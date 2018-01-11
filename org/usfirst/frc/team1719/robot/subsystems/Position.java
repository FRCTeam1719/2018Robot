package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UsePosition;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * Position and NAV-X
 * 
 * @author gusg21
 *
 */
public class Position extends Subsystem {

	AHRS navX;
	Encoder left;
	Encoder right;
	
    private final double collisionThreshold = 0.5f;
    private boolean isTrustworthy;
    private double lastAccelX;
    private double lastAccelY;
    private double lDist;
    private double rDist;
    private volatile double x;
    private volatile double y;
    private volatile double heading;

	public Position(AHRS _navX, Encoder _left, Encoder _right) {
		navX = _navX;
		left = _left;
		right = _right;
	}

	private boolean checkForCollision() {
		double currentAccelX = navX.getWorldLinearAccelX();
		double currentJerkX = currentAccelX - lastAccelX;
		lastAccelX = currentAccelX;
		double currentAccelY = navX.getWorldLinearAccelY();
		double currentJerkY = currentAccelY - lastAccelY;
		lastAccelY = currentAccelY;
		if (Math.abs(currentJerkX) > collisionThreshold || Math.abs(currentJerkY) > collisionThreshold) {
			return true;
		} else {
			return false;
		}

	}

	public void update() {
		isTrustworthy = checkForCollision();
		heading = navX.getYaw();
		double dl = left.getDistance() - lDist;
		double dr = right.getDistance() - rDist;
		lDist += dl;
		rDist += dr;
		x += Math.sin(Math.toRadians(heading)) * (dl + dr) / 2.0D;
		y += Math.cos(Math.toRadians(heading)) * (dl + dr) / 2.0D;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new UsePosition(this));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public boolean getTrustworthy() {
		return isTrustworthy;
	}
}
