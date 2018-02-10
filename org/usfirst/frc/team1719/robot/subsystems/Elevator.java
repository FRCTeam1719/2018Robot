package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseElevator;
import org.usfirst.frc.team1719.robot.interfaces.IEncoder;
import org.usfirst.frc.team1719.robot.sensors.RangeFinder45LMS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Elevator subsystem for controlling the elevator
 * 
 * @author bennyrubin
 * @author quintin
 *
 */
public class Elevator extends Subsystem {
    private IEncoder positionEncoder;
    private SpeedController elevatorController;
    private RangeFinder45LMS rangeFinder;
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;
    
    PIDController elevatorPIDController;
    
    private double kP = 0;
    private double kD = 0;
    private double kF = 0;
    
    private double elevatorSpeed;
    
    volatile double elevatorOutput = 0;
    
    volatile double actualDistance = 0;
    
    volatile double err = 0;
    
    /**
     * Takes in an Encoder for the position, a motor to control it, and two switches
     * for when it reaches the top and bottom of the elevator
     * 
     * @param position
     *            - Encoder for current position of the elevator
     * @param top
     *            - top limit switch
     * @param bottom
     *            - bottom limit switch
     * @param speedController
     *            - elevator speed controller
     */
    public Elevator(SpeedController _elevatorController, RangeFinder45LMS _rangeFinder, DigitalInput _upperLimit, DigitalInput _lowerLimit) {
        elevatorController = _elevatorController;
        rangeFinder = _rangeFinder;
        upperLimit = _upperLimit;
        lowerLimit = _lowerLimit;
               
        
        getRangeFinder().setPIDSourceType(PIDSourceType.kRate);
        
        elevatorPIDController = new PIDController(kP, 0, kF, getRangeFinder(), elevatorController);
        getRangeFinder().setPIDSourceType(PIDSourceType.kRate);
        elevatorPIDController.setInputRange(0D, 70D);
        elevatorPIDController.setOutputRange(-1, 1);
        elevatorPIDController.setContinuous(false);
        elevatorPIDController.setToleranceBuffer(20);
        elevatorPIDController.setPercentTolerance(5);
        SmartDashboard.putData("ELEVATOR_PID", elevatorPIDController);
        SmartDashboard.putNumber("Elevator Height", getRangeFinder().distance());
        
        elevatorPIDController.enable();
        
    }
    
    /**
     * Update the PID setpoint from a double.
     * 
     * @param targetElevatorZ
     */
    public void PIDUpdate(double targetElevatorZ) {
        
        actualDistance = getDistanceVoltage();
        elevatorPIDController.setSetpoint(targetElevatorZ);
        err = targetElevatorZ - actualDistance;
        
    }
    
    /**
     * Set the PID controller to a passed PID Controller
     * 
     * @param _elevatorPIDController
     */
    public void setPIDController(PIDController _elevatorPIDController) {
        elevatorPIDController = _elevatorPIDController;
    }
    
    /**
     * Returns the elevatorPIDController
     * 
     * @return
     */
    public PIDController getPIDController() {
        return elevatorPIDController;
    }
    
    private class elevatorPIDOut implements PIDOutput {
        
        /**
         * Write the values of the PID controller to the output variable.
         * 
         * @param output
         */
        public void pidWrite(double output) {
            elevatorOutput = output;
        }
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseElevator(this));
    }
    
    /**
     * returns the distance of the rangefinder
     * 
     * @return
     */
    public double getDistance() {
        return rangeFinder.distance();
    }
    
    /**
     * returns the voltage of the rangefinder
     * 
     * @return
     */
    public double getDistanceVoltage() {
        return rangeFinder.getVoltage();
    }
    
    /**
     * returns the rangefinder
     * 
     * @return
     */
    public RangeFinder45LMS getRangeFinder() {
        return rangeFinder;
    }
    
    /**
     * moves the elevator a disired speed.
     * 
     * @param speed
     */
    public void moveElevator(double speed) {
        
        elevatorController.set(speed);
        /* System.out.println(speed / 2); */
        
    }
    
    public DigitalInput getUpperLimit() {
        return upperLimit;
    }
    
    public DigitalInput getLowerLimit() {
        return lowerLimit;
        
    }
    
    /**
     * stops the elevator from moving
     */
    public void stop() {
        elevatorController.set(0);
    }
    
}
