// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class Climber extends SubsystemBase {

  public TalonFX leftMotor;
  public TalonFX rightMotor;

  /** Creates a new Climber. */
  public Climber() {
    leftMotor = new TalonFX(Constants.ID.CLIMBER_LEFT);
    rightMotor = new TalonFX(Constants.ID.CLIMBER_RIGHT);

    rightMotor.setInverted(true);

    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);

    leftMotor.configPeakOutputForward(1);
    leftMotor.configPeakOutputReverse(-1);
    rightMotor.configPeakOutputForward(1);
    rightMotor.configPeakOutputReverse(-1);

    leftMotor.config_kP(Constants.Climber.SLOT_ID_0, Constants.Climber.P_0);
    leftMotor.config_kI(Constants.Climber.SLOT_ID_0, Constants.Climber.I_0);
    leftMotor.config_kD(Constants.Climber.SLOT_ID_0, Constants.Climber.D_0);
    leftMotor.config_kF(Constants.Climber.SLOT_ID_0, Constants.Climber.F_0);
    rightMotor.config_kP(Constants.Climber.SLOT_ID_0, Constants.Climber.P_0);
    rightMotor.config_kI(Constants.Climber.SLOT_ID_0, Constants.Climber.I_0);
    rightMotor.config_kD(Constants.Climber.SLOT_ID_0, Constants.Climber.D_0);
    rightMotor.config_kF(Constants.Climber.SLOT_ID_0, Constants.Climber.F_0);

    leftMotor.config_kP(Constants.Climber.SLOT_ID_1, Constants.Climber.P_1);
    leftMotor.config_kI(Constants.Climber.SLOT_ID_1, Constants.Climber.I_1);
    leftMotor.config_kD(Constants.Climber.SLOT_ID_1, Constants.Climber.D_1);
    leftMotor.config_kF(Constants.Climber.SLOT_ID_1, Constants.Climber.F_1);
    rightMotor.config_kP(Constants.Climber.SLOT_ID_1, Constants.Climber.P_1);
    rightMotor.config_kI(Constants.Climber.SLOT_ID_1, Constants.Climber.I_1);
    rightMotor.config_kD(Constants.Climber.SLOT_ID_1, Constants.Climber.D_1);
    rightMotor.config_kF(Constants.Climber.SLOT_ID_1, Constants.Climber.F_1);
  }

  public void selectProfile(int id) {
    leftMotor.selectProfileSlot(id, 0);
    rightMotor.selectProfileSlot(id, 0);
  }

  public void climb(double speed) {
    leftMotor.set(ControlMode.PercentOutput, speed);
    rightMotor.set(ControlMode.PercentOutput, speed);
  }

  public void climbLeft(double speed) {
    leftMotor.set(ControlMode.PercentOutput, speed);
  }

  public void climbRight(double speed) {
    rightMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setPosition(double encoder) {
    leftMotor.set(ControlMode.Position, encoder);
    rightMotor.set(ControlMode.Position, encoder);
  }

  public void setPositionLeft(double encoder) {
    leftMotor.set(ControlMode.Position, encoder);
  }

  public void setPositionRight(double encoder) {
    rightMotor.set(ControlMode.Position, encoder);
  }

  public void resetEncoders() {
    leftMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
    rightMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  public double getLeftEncoderCount() {
    return leftMotor.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getRightEncoderCount() {
    return rightMotor.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getCurrentLeft() {
    return Math.abs(leftMotor.getStatorCurrent());
  }

  public double getCurrentRight() {
    return Math.abs(rightMotor.getStatorCurrent());
  }

  public void printEncoders() {
    System.out.println(leftMotor.getDeviceID() + ": " + getLeftEncoderCount());
    System.out.println(rightMotor.getDeviceID() + ": " + getRightEncoderCount());
  }

  public void stop() {
    leftMotor.set(ControlMode.PercentOutput, 0.0);
    rightMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public double getVelocityLeft() {
    return leftMotor.getSelectedSensorVelocity();
  }

  public double getVelocityRight() {
    return rightMotor.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
  }
}