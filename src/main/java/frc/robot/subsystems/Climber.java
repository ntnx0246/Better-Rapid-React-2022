// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  public TalonFX leftMotor; 
  public TalonFX rightMotor; 

  /** Creates a new Climber. */
  public Climber() {
    leftMotor = new TalonFX(Constants.CLIMBER_LEFT_ID);
    rightMotor = new TalonFX(Constants.CLIMBER_RIGHT_ID);

    leftMotor.setInverted(true);

    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void climb(double speed){
    leftMotor.set(ControlMode.PercentOutput, speed);
    rightMotor.set(ControlMode.PercentOutput, speed);
  }

  public void climbLeft(double speed){
    leftMotor.set(ControlMode.PercentOutput, speed);
  }

  public void climbRight(double speed){
    rightMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setPosition(double encoder){
    leftMotor.set(ControlMode.Position, encoder);
    rightMotor.set(ControlMode.Position, encoder);
  }

  public void resetEncoders(){
    leftMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
    rightMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  public double getLeftEncoderCount() {
    return leftMotor.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getRightEncoderCount() {
    return rightMotor.getSensorCollection().getIntegratedSensorPosition();
  }

  public void printEncoders() {
    System.out.println("Left: " + getLeftEncoderCount());
    System.out.println("Right: " + getRightEncoderCount());
  }
  
  public void stop(){
    leftMotor.set(ControlMode.PercentOutput, 0.0);
    rightMotor.set(ControlMode.PercentOutput, 0.0);
  }

  // check for stall of the motors
  public double getVelocityLeft(){
    return leftMotor.getSelectedSensorVelocity();
  }

  public double getVelocityRight(){
    return rightMotor.getSelectedSensorVelocity();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}