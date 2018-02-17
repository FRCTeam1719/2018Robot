package org.usfirst.frc.team1719.robot.sensors;

/**
 * Laser rangefinder provided by Mike of the Many Monikers.
 */
public class RangeFinder45LMS extends edu.wpi.first.wpilibj.AnalogInput {

    /**
     * Set up the analog input
     * 
     * @param channel - the RIO's AIN channel to use
     */
    public RangeFinder45LMS(int channel) {
        super(channel);
    }
    
    /**
     * @return the distance from the LMS in cm. 
     */
    public double distance() {
        return (10+((this.getVoltage()-1)*18)); //Minimum value of the range finder is 10.
    }
}
