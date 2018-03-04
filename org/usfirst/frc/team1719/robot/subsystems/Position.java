package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UsePosition;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 * Position and NAV-X
 * 
 * @author Duncan
 * @author gusg21
 *
 */
public class Position extends Subsystem {

	private final AHRS navX;
	private final Encoder left;
	private final Encoder right;
	
    private final double collisionThreshold = 0.5f;
    private boolean isTrustworthy;
    private double lastAccelX;
    private double lastAccelY;
    private double lDist;
    private double rDist;
    private volatile double x;
    private volatile double y;
    private volatile double heading;

    /**
     * Subsystem for current Cartesian coordinates via Nav-X
     * 
     * @param _navX AHRS Nav-X instance
     * @param _left Left Encoder
     * @param _right Right Encoder
     */
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

	/**
	 * Update the position, converting polar coordinates
	 */
	public void update() {
		isTrustworthy = checkForCollision();
		heading = navX.getYaw();
		double dl = left.getDistance() - lDist;
		double dr = right.getDistance() - rDist;
		lDist += dl;
		rDist += dr;
		x += Math.sin(Math.toRadians(heading)) * (dl + dr) / 2.0D;
		y += Math.cos(Math.toRadians(heading)) * (dl + dr) / 2.0D;
		/*System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("Yaw: " + navX.getYaw());
		System.out.println("Pitch: " + navX.getPitch());
		System.out.println("Roll: " + navX.getRoll());*/
		
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new UsePosition(this));
	}
	
	/**
	 * Get X value
	 * 
	 * @return X value
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Get Y value
	 * 
	 * @return Y value
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Get current yaw heading
	 * @return current yaw heading
	 */
	public double getHeading() {
		return heading;
	}
	
	/**
	 * Get whether our current data is reliable
	 * 
	 * @return if we still have our tolerance of around +/- 5 in
	 */
	public boolean getTrustworthy() {
		return isTrustworthy;
	}
}
