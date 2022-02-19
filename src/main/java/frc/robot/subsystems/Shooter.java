// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax left;
  private CANSparkMax right;
  private SparkMaxPIDController leftPIDController;
  private SparkMaxPIDController rightPIDController;
  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  public Shooter() {
    left = new CANSparkMax(Constants.SHOOTER_LEFT_ID, MotorType.kBrushless);
    right = new CANSparkMax(Constants.SHOOTER_RIGHT_ID, MotorType.kBrushless);
   
    leftPIDController = left.getPIDController();
    rightPIDController = right.getPIDController();
   
    right.setInverted(true);

    leftEncoder = left.getEncoder();
    rightEncoder = right.getEncoder();

    leftPIDController.setP(Constants.SHOOTER_P);
    leftPIDController.setI(Constants.SHOOTER_I);
    leftPIDController.setD(Constants.SHOOTER_D);
    leftPIDController.setFF(Constants.SHOOTER_F);

    rightPIDController.setP(Constants.SHOOTER_P);
    rightPIDController.setI(Constants.SHOOTER_I);
    rightPIDController.setD(Constants.SHOOTER_D);
    rightPIDController.setFF(Constants.SHOOTER_F);
  }

  public void setSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }
  
   //set velocity
   public void setVelocity(double velocity){
    leftPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    rightPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
  }

  //get velocity
  public double getLeftVelocity(){
    return leftEncoder.getVelocity();
  }

  public double getRightVelocity(){
    return rightEncoder.getVelocity();
  }

  //one starts shooter motor, second one that pushes game piece into the shooter, turns of a few seconds after both are shooted.
  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("Left: " + getLeftVelocity());
    System.out.println("Right: " + getRightVelocity());
  }
}
