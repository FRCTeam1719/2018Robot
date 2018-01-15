package org.usfirst.frc.team1719.robot.interfaces;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * Interface around the WPILib Encoder object. Allows for mockability & device specific settings
 * It's recommended to run config() after creating an object implementing IEncoder
 * @author Duncan Lowther
 *
 */
public interface IEncoder extends PIDSource {
    /**
     * Resets the total distance traveled to 0.
     */
    public void reset();
    /**
     * @return {@code true} if the encoder is not moving
     */
    public boolean getStopped();
    /**
     * @return the direction the encoder is spinning.
     */
    public boolean getDirection();
    /**
     * @return the distance the encoder has moved since {@link #reset()} was last called.
     */
    public double getDistance();
    /**
     * @return the rate at which the encoder is turning.
     */
    public double getRate();
    /**
     * 
     * @param distancePerPulse
     */
    public void setDistancePerPulse(double distancePerPulse);
    /**
     * Sets whether the encoder is mounted 'backwards'
     * @param reverseDirection set to {@code true} if 'negative' ticks should be counted as positive in code.
     */
    public void setReverseDirection(boolean reverseDirection);
    /**
     * Sets the number of past samples to use to smooth collection noise.
     * @param sampleNum the number of samples.
     */
    public void setSamplesToAverage(int sampleNum);
    /**
     * Sets up device specific settings
     * @param distancePerRev (ft)
     */
    public void config(double distancePerRev);
}