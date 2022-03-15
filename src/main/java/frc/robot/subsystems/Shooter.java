// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax left;
  private CANSparkMax right;
  private SparkMaxPIDController leftPIDController;
  private SparkMaxPIDController rightPIDController;
  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  public Shooter() {
    left = new CANSparkMax(Constants.ID.SHOOTER_LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ID.SHOOTER_RIGHT, MotorType.kBrushless);

    leftPIDController = left.getPIDController();
    rightPIDController = right.getPIDController();

    right.setInverted(true);

    leftEncoder = left.getEncoder();
    rightEncoder = right.getEncoder();

    leftPIDController.setP(Constants.Shooter.P);
    leftPIDController.setI(Constants.Shooter.I);
    leftPIDController.setD(Constants.Shooter.D);
    leftPIDController.setFF(Constants.Shooter.F);

    rightPIDController.setP(Constants.Shooter.P);
    rightPIDController.setI(Constants.Shooter.I);
    rightPIDController.setD(Constants.Shooter.D);
    rightPIDController.setFF(Constants.Shooter.F);
  }

  public void setSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }

  // set velocity
  public void setVelocity(double velocity) {
    leftPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    rightPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
  }

  // get velocity
  public double getLeftVelocity() {
    return leftEncoder.getVelocity();
  }

  public double getRightVelocity() {
    return rightEncoder.getVelocity();
  }

  // one starts shooter motor, second one that pushes game piece into the shooter,
  // turns of a few seconds after both are shooted.
  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // System.out.println("Left: " + getLeftVelocity());
    // System.out.println("Right: " + getRightVelocity());
  }
}
