// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpiutil.math.MathUtil;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class LinearServo extends SubsystemBase {
  /** Creates a new Servo. */
  double m_speed;
  double m_length;
  double setPos;
  double curPos;
  private Servo left;
  private Servo right;

  public LinearServo() {
    // super(channel);
    left = new Servo(Constants.LINEARSERVO_LEFT_CHANNEL);
    right = new Servo(Constants.LINEARSERVO_RIGHT_CHANNEL);
    left.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    right.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    // m_length = length;
    // m_speed = speed;
  }

  // public void setSpeed(double speed) {
  // // = MathUtil.clamp(setpoint, 0, m_length);
  // left.setSpeed(Constants.LINEARSERVO_SPEED);
  // right.setSpeed(Constants.LINEARSERVO_SPEED);
  // //right.setPosition(setPos);
  // }

  public void setPosition(double value) {
    value = MathUtil.clamp(value, 0, 1);
    left.set(value);
    right.set(value);
  }

  public double getLeftPosition() {
    System.out.println(left.get());
    return left.get();
  }

  public double getRightPosition() {
    System.out.println(right.get());
    return right.get();
  }

  // double lastTime = 0;

  // public void updateCurPos() {
  // double dt = Timer.getFPGATimestamp() - lastTime;
  // if (curPos > setPos + m_speed * dt) {
  // curPos -= m_speed * dt;
  // } else if (curPos < setPos - m_speed * dt) {
  // curPos += m_speed * dt;
  // } else {
  // curPos = setPos;
  // }
  // }

  // public double getPosition() {
  // return curPos;
  // }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
