// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private boolean slowModeOn;
  private TalonFX frontR;
  private TalonFX frontL;
  private TalonFX backR;
  private TalonFX backL;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontR = new TalonFX(Constants.FRONT_RIGHT_ID);
    frontL = new TalonFX(Constants.FRONT_LEFT_ID);
    backR = new TalonFX(Constants.BACK_RIGHT_ID);
    backL = new TalonFX(Constants.BACK_LEFT_ID);
    slowModeOn = false;

    backL.follow(frontL);
    backR.follow(frontR);
    frontL.setInverted(false);
    backL.setInverted(false);
    frontR.setInverted(true);
    backR.setInverted(true);

    frontL.configClosedloopRamp(Constants.CLOSED_LOOP_RAMP);
    frontR.configClosedloopRamp(Constants.CLOSED_LOOP_RAMP);
    backL.configClosedloopRamp(Constants.CLOSED_LOOP_RAMP);
    backR.configClosedloopRamp(Constants.CLOSED_LOOP_RAMP);

    frontL.configOpenloopRamp(Constants.OPEN_LOOP_RAMP);
    frontR.configOpenloopRamp(Constants.OPEN_LOOP_RAMP);
    backL.configOpenloopRamp(Constants.OPEN_LOOP_RAMP);
    backR.configOpenloopRamp(Constants.OPEN_LOOP_RAMP);
    
    frontL.configPeakOutputForward(1);
    frontL.configPeakOutputReverse(-1);
    frontR.configPeakOutputForward(1);
    frontR.configPeakOutputReverse(-1);

    frontL.configMotionCruiseVelocity(Constants.CRUISE_VELOCITY);
    frontL.configMotionAcceleration(Constants.ACCELERATION);
    frontR.configMotionCruiseVelocity(Constants.CRUISE_VELOCITY);
    frontR.configMotionAcceleration(Constants.ACCELERATION);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y - x);
    frontR.set(ControlMode.PercentOutput, y + x);
  }

  public boolean getSlowMode() {
    return slowModeOn;
  }

  public void setSlowMode(boolean slowMode) {
    slowModeOn = slowMode;
  }

  // for testing purposes
  public void tankDrive(double left, double right) {
    frontL.set(ControlMode.PercentOutput, left);
    frontR.set(ControlMode.PercentOutput, right);
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public double getLeftEncoderCount() {
    return frontL.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getRightEncoderCount() {
    return frontR.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getNativeUnitsFromInches(double inches) {
    return inches * Constants.MOTOR_TO_WHEEL_REVOLUTION / (Math.PI * Constants.DRIVE_WHEEL_DIAMETER_INCHES)
        * Constants.SENSOR_UNITS_PER_ROTATION;
  }

  public double getInchesFromNativeUnits(double native_units) {
    return native_units / Constants.MOTOR_TO_WHEEL_REVOLUTION * (Math.PI * Constants.DRIVE_WHEEL_DIAMETER_INCHES)
        / Constants.SENSOR_UNITS_PER_ROTATION;
  }

  public void resetEncoders() {
    frontL.getSensorCollection().setIntegratedSensorPosition(0, 0);
    frontR.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  public void printEncoders() {
    System.out.println("Left: " + getLeftEncoderCount());
    System.out.println("Right: " + getRightEncoderCount());
  }

  public void printInches() {
    System.out.println("Left Inches: " + getInchesFromNativeUnits(getLeftEncoderCount()));
    System.out.println("Right Inches: " + getInchesFromNativeUnits(getRightEncoderCount()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPosition(double pos) {
    frontL.set(ControlMode.MotionMagic, pos);
    frontR.set(ControlMode.MotionMagic, pos);
  }

  public void setLeftPID(int slotID, double p, double i, double d) {
    frontL.config_kP(slotID, p);
    frontL.config_kI(slotID, i);
    frontL.config_kD(slotID, d);
  }

  public void setRightPID(int slotID, double p, double i, double d) {
    frontR.config_kP(slotID, p);
    frontR.config_kI(slotID, i);
    frontR.config_kD(slotID, d);
  }
}
