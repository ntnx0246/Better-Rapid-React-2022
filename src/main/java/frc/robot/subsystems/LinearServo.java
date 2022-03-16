// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
public class LinearServo extends SubsystemBase {
  double m_speed;
  double m_length;
  double setPos;
  double curPos;
  private Servo left;
  private Servo right;

  public LinearServo() {
    left = new Servo(Constants.LINEARSERVO_LEFT_CHANNEL);
    right = new Servo(Constants.LINEARSERVO_RIGHT_CHANNEL);
    left.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    right.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
  }

  public void setPosition(double value) {
    value = MathUtil.clamp(value, 0, 1);
    left.set(value);
    right.set(value);
  }

  public double getLeftPosition() {
    return left.get();
  }

  public double getRightPosition() {
    return right.get();
  }
  
  @Override
  public void periodic() {
  }
}
