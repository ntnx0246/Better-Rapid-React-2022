// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DriveTrain extends SubsystemBase {
  private boolean slowModeOn;
  private boolean climbing;
  private TalonFX frontR;
  private TalonFX frontL;
  private TalonFX backR;
  private TalonFX backL;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontR = new TalonFX(Constants.ID.DRIVETRAIN_FRONT_RIGHT);
    frontL = new TalonFX(Constants.ID.DRIVETRAIN_FRONT_LEFT);
    backR = new TalonFX(Constants.ID.DRIVETRAIN_BACK_RIGHT);
    backL = new TalonFX(Constants.ID.DRIVETRAIN_BACK_LEFT);
    slowModeOn = false;
    climbing = false;

    backL.follow(frontL);
    backR.follow(frontR);
    frontL.setInverted(false);
    backL.setInverted(false);
    frontR.setInverted(true);
    backR.setInverted(true);

    frontL.configClosedloopRamp(Constants.DriveTrain.CLOSED_LOOP_RAMP);
    frontR.configClosedloopRamp(Constants.DriveTrain.CLOSED_LOOP_RAMP);
    backL.configClosedloopRamp(Constants.DriveTrain.CLOSED_LOOP_RAMP);
    backR.configClosedloopRamp(Constants.DriveTrain.CLOSED_LOOP_RAMP);

    frontL.configOpenloopRamp(Constants.DriveTrain.OPEN_LOOP_RAMP);
    frontR.configOpenloopRamp(Constants.DriveTrain.OPEN_LOOP_RAMP);
    backL.configOpenloopRamp(Constants.DriveTrain.OPEN_LOOP_RAMP);
    backR.configOpenloopRamp(Constants.DriveTrain.OPEN_LOOP_RAMP);

    frontL.configPeakOutputForward(1);
    frontL.configPeakOutputReverse(-1);
    frontR.configPeakOutputForward(1);
    frontR.configPeakOutputReverse(-1);

    frontL.configMotionCruiseVelocity(Constants.DriveTrain.CRUISE_VELOCITY);
    frontL.configMotionAcceleration(Constants.DriveTrain.ACCELERATION);
    frontR.configMotionCruiseVelocity(Constants.DriveTrain.CRUISE_VELOCITY);
    frontR.configMotionAcceleration(Constants.DriveTrain.ACCELERATION);
    this.setLeftPID(Constants.DriveTrain.PID.s, Constants.DriveTrain.PID.p, Constants.DriveTrain.PID.i,
        Constants.DriveTrain.PID.d);
    this.setRightPID(Constants.DriveTrain.PID.s, Constants.DriveTrain.PID.p, Constants.DriveTrain.PID.i,
        Constants.DriveTrain.PID.d);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y - x);
    frontR.set(ControlMode.PercentOutput, y + x);
  }

  public boolean getSlowMode() {
    return slowModeOn;
  }

  public boolean climbing(){
    return climbing;
  }

  public void toggleSlowMode() {
    slowModeOn = ! slowModeOn;
  }

  public void falseSlowMode(){
    slowModeOn = false;
  }

  public void trueSlowMode(){
    slowModeOn = true;
  }

  public void climbingFalse(){
    climbing = false;
  }

  public void climbingTrue(){
    climbing = true;
  }

  public boolean getClimbing(){
    return climbing;
  }

  public void setSlowMode(boolean slowMode) {
    slowModeOn = slowMode;
  }

  public void tankDrive(double left, double right) {
    frontL.set(ControlMode.PercentOutput, left);
    frontR.set(ControlMode.PercentOutput, right);
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public double getLeftEncoderCount() {
    // return frontL.getSensorCollection().getIntegratedSensorPosition();
    return frontL.getSelectedSensorPosition();

  }

  public double getRightEncoderCount() {
    // return frontR.getSensorCollection().getIntegratedSensorPosition();
    return frontR.getSelectedSensorPosition();

  }

  public double getNativeUnitsFromInches(double inches) {
    return inches * Constants.DriveTrain.MOTOR_TO_WHEEL_REVOLUTION / (Math.PI * Constants.DriveTrain.DRIVE_WHEEL_DIAMETER_INCHES)
        * Constants.DriveTrain.SENSOR_UNITS_PER_ROTATION;
  }

  public double getInchesFromNativeUnits(double native_units) {
    return native_units / Constants.DriveTrain.MOTOR_TO_WHEEL_REVOLUTION * (Math.PI * Constants.DriveTrain.DRIVE_WHEEL_DIAMETER_INCHES)
        / Constants.DriveTrain.SENSOR_UNITS_PER_ROTATION;
  }

  public double getNativeUnitsFromAngle(double degrees) {
    return degrees * Constants.DriveTrain.TURN_CONSTANT;

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

  public void setAngle(double degrees) {
    System.out.println("LEFT WAS" + this.getLeftEncoderCount());
    System.out.println("ADDDING LEFT TO " + (degrees * Constants.DriveTrain.TURN_CONSTANT));
    System.out.println("SETTING TO " + (this.getLeftEncoderCount() + (degrees * Constants.DriveTrain.TURN_CONSTANT)));
    frontL.set(ControlMode.Position, this.getLeftEncoderCount() + (degrees * Constants.DriveTrain.TURN_CONSTANT));
    frontR.set(ControlMode.Position, this.getRightEncoderCount() - (degrees * Constants.DriveTrain.TURN_CONSTANT));
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
