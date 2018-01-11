    package org.usfirst.frc.team1719.robot.sensors;

    import org.usfirst.frc.team1719.robot.interfaces.IEncoder;

    /**
     * Driver class for the E4T Optical Encoder Simple wrapper around the WPILib
     * Encoder that implements IEncoder, for the purposes of mockability, and
     * hardware configs Requires the parameter distance per revolution, so that the
     * internal Encoder object can track distance per tick For specs & more, see the
     * <a href=
     * "http://104.131.160.86/index.php/E4T_Miniature_Optical_Encoder">wiki</a> for
     * more.
     * 
     * @author Duncan Lowther
     *
     */
    public class E4TOpticalEncoder extends edu.wpi.first.wpilibj.Encoder implements IEncoder {

        public final double TICKS_PER_REV = 1440.0;

        /**
         * Calculates distance per tick and passes that to the internal Encoder
         * object. Uses TICKS_PER_REV which is supplied by documentation
         * 
         * @param distancePerRev
         *            (distance in ft)
         */
        public void config(double distancePerRev) {
            this.setDistancePerPulse(distancePerRev / TICKS_PER_REV);
        }

        public E4TOpticalEncoder(int channelA, int channelB, boolean reverseDirection) {
            super(channelA, channelB, reverseDirection, EncodingType.k2X);
        }

    }
