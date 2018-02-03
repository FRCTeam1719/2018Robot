package org.usfirst.frc.team1719.robot.sensors;

public class RangeFinder45LMS extends edu.wpi.first.wpilibj.AnalogInput {

    public RangeFinder45LMS(int channel) {
        super(channel);
        // TODO Auto-generated constructor stub
    }
    /**
     * get the distance from the LMS in cm.
     * @return 
     */
    public double distance() {
        return (10+((this.getVoltage()-1)*18)); //Minimum value of the range finder is 10.
    }
    
}
