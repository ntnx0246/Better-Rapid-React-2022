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
  public CANSparkMax leftPivot;
  public CANSparkMax rightPivot;
  private RelativeEncoder leftPivotEncoder;
  private RelativeEncoder rightPivotEncoder;
  private SparkMaxPIDController leftPivotController;
  private SparkMaxPIDController rightPivotController;

  /** Creates a new Climber. */
  public Climber() {
    leftMotor = new TalonFX(Constants.ID.CLIMBER_LEFT);
    rightMotor = new TalonFX(Constants.ID.CLIMBER_RIGHT);
    leftPivot = new CANSparkMax(Constants.ID.PIVOT_LEFT, MotorType.kBrushless);
    rightPivot = new CANSparkMax(Constants.ID.PIVOT_RIGHT, MotorType.kBrushless);
    rightPivot.setInverted(true);

    leftPivotEncoder = leftPivot.getEncoder();
    rightPivotEncoder = rightPivot.getEncoder();

    leftPivotController = leftPivot.getPIDController();
    rightPivotController = rightPivot.getPIDController();

    leftPivotController.setP(Constants.Climber.P_3);
    leftPivotController.setI(Constants.Climber.I_3);
    leftPivotController.setD(Constants.Climber.D_3);
    leftPivotController.setFF(Constants.Climber.F_3);

    rightPivotController.setP(Constants.Climber.P_3);
    rightPivotController.setI(Constants.Climber.I_3);
    rightPivotController.setD(Constants.Climber.D_3);
    rightPivotController.setFF(Constants.Climber.F_3);

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

    leftMotor.config_kP(Constants.Climber.SLOT_ID_2, Constants.Climber.P_2);
    leftMotor.config_kI(Constants.Climber.SLOT_ID_2, Constants.Climber.I_2);
    leftMotor.config_kD(Constants.Climber.SLOT_ID_2, Constants.Climber.D_2);
    leftMotor.config_kF(Constants.Climber.SLOT_ID_2, Constants.Climber.F_2);
    rightMotor.config_kP(Constants.Climber.SLOT_ID_2, Constants.Climber.P_2);
    rightMotor.config_kI(Constants.Climber.SLOT_ID_2, Constants.Climber.I_2);
    rightMotor.config_kD(Constants.Climber.SLOT_ID_2, Constants.Climber.D_2);
    rightMotor.config_kF(Constants.Climber.SLOT_ID_2, Constants.Climber.F_2);
  }

  public void setPositionPivots(double position){
    leftPivotController.setReference(position, CANSparkMax.ControlType.kPosition);
    rightPivotController.setReference(position, CANSparkMax.ControlType.kPosition);
    // leftPivotEncoder.setPosition(position);
    // rightPivotEncoder.setPosition(position);
  }

  public double getLeftPivotEncoder(){
    return leftPivotEncoder.getPosition();
  }

  public double getRightPivotEncoder(){
    return rightPivotEncoder.getPosition();
  }

  public void climbPivotLeft(double speed){
    leftPivot.set(speed);
  }

  public void climbPivotRight(double speed){
    rightPivot.set(speed);
  }

  public void climbPivots(double speed){
    leftPivot.set(speed);
    rightPivot.set(speed);
    System.out.println("testing pivot motor"+leftPivot.get());

  }

  public double getCurrentPivotLeft(){
    return leftPivot.getOutputCurrent();
  }

  public double getCurrentPivotRight(){
    return rightPivot.getOutputCurrent();
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
    leftPivot.set(0);
    rightPivot.set(0);
  }

  // check for stall of the motors
  public double getVelocityLeft() {
    return leftMotor.getSelectedSensorVelocity();
  }

  public double getVelocityRight() {
    return rightMotor.getSelectedSensorVelocity();
  }

  // limit the encoder for climber when going up, don't let the numbers get to
  // high
  // right has to get to 210000 (currently - L: -202964 R: 204334)

  @Override
  public void periodic() {
    // printEncoders();
    System.out.println(this.getLeftPivotEncoder());
  }
}