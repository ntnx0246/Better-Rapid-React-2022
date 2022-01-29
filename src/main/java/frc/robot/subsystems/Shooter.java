// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax left;
  private CANSparkMax right;

  public Shooter() {
    left = new CANSparkMax(Constants.SHOOTER_LEFT_ID, MotorType.kBrushless);
    right = new CANSparkMax(Constants.SHOOTER_RIGHT_ID, MotorType.kBrushless);
    right.setInverted(true);
  }

  public void setSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
