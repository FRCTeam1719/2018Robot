package org.usfirst.frc.team1719.robot.subsystems;

import org.usfirst.frc.team1719.robot.commands.UseElevator;
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
    private SpeedController elevatorController;
    private RangeFinder45LMS rangeFinder;
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;
    
    PIDController elevatorPIDController;
    
    private double kP = 0.01;
    private double kD = 0;
    private double kF = 0;
    
    private double elevatorSpeed;
    
    volatile double elevatorOutput = 0;
    
    volatile double actualDistance = 0;
    
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
    @SuppressWarnings("deprecation")
    public Elevator(SpeedController _elevatorController, RangeFinder45LMS _rangeFinder, DigitalInput _upperLimit, DigitalInput _lowerLimit) {
        elevatorController = _elevatorController;
        rangeFinder = _rangeFinder;
        upperLimit = _upperLimit;
        lowerLimit = _lowerLimit;
               
        
        getRangeFinder().setPIDSourceType(PIDSourceType.kRate);
        
        elevatorPIDController = new PIDController(kP, 0, kF, getRangeFinder(), new ElevatorPIDOut());
        elevatorPIDController.setInputRange(1D, 80D);
        elevatorPIDController.setOutputRange(-1, 1);
        elevatorPIDController.setContinuous(false);
        elevatorPIDController.setToleranceBuffer(20);
        elevatorPIDController.setPercentTolerance(5);
        elevatorPIDController.setSetpoint(getRangeFinder().pidGet());
        SmartDashboard.putNumber("Elevator Height", getRangeFinder().distance());
        
        //elevatorPIDController.enable();
        SmartDashboard.putData("ELEVATOR_PID", elevatorPIDController);
    }
    
    /**
     * Update the PID setpoint from a double.
     * 
     * @param targetElevatorZ
     */
    public void updatePID(double targetElevatorZ) {
        elevatorPIDController = (PIDController) SmartDashboard.getData("ELEVATOR_PID");
        elevatorPIDController.setSetpoint(targetElevatorZ);
    }
    
    public class ElevatorPIDOut implements PIDOutput {
        
        @Override
        public void pidWrite(double output) {
            if(upperLimit.get() && output > 0) {
                output = 0;
            }else if(lowerLimit.get() && output < 0) {
                output = 0;
            }
            elevatorController.set(output);
        }
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new UseElevator(this));
    }
    
    /**
     * @return the distance of the rangefinder
     */
    public double getDistance() {
        return rangeFinder.distance();
    }
    
    /**
     * @return the voltage of the rangefinder
     */
    public double getDistanceVoltage() {
        return rangeFinder.getVoltage();
    }
    
    /**
     * @return the rangefinder
     */
    public RangeFinder45LMS getRangeFinder() {
        return rangeFinder;
    }
    
    /**
     * moves the elevator a desired speed.
     * 
     * @param speed - the power to the motor, on the range [-1, 1]
     */
    public void moveElevator(double output) {
        
        if(upperLimit.get() && output < 0) {
            System.out.println("upper limit");
            output = 0;
        }else if(lowerLimit.get() && output > 0) {
            System.out.println("lower limit");
            output = 0;
        }
        
        elevatorController.set(output);
        
        System.out.println(output);
    }
    
    /**
     * stops the elevator from moving
     */
    public void stop() {
        elevatorController.set(0);
    }
    
}
