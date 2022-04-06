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
import frc.robot.utils.PID;

public class Pivots extends SubsystemBase {
  /** Creates a new Pivots. */
  public CANSparkMax leftPivot;
  public CANSparkMax rightPivot;
  private RelativeEncoder leftPivotEncoder;
  private RelativeEncoder rightPivotEncoder;
  private SparkMaxPIDController leftPivotController;
  private SparkMaxPIDController rightPivotController;

  public Pivots() {
    leftPivot = new CANSparkMax(Constants.ID.PIVOT_LEFT, MotorType.kBrushless);
    rightPivot = new CANSparkMax(Constants.ID.PIVOT_RIGHT, MotorType.kBrushless);
    rightPivot.setInverted(true);

    leftPivotEncoder = leftPivot.getEncoder();
    rightPivotEncoder = rightPivot.getEncoder();

    leftPivotController = leftPivot.getPIDController();
    rightPivotController = rightPivot.getPIDController();

    initPIDController(leftPivotController, rightPivotController, Constants.Pivot.FAST_PID);
    initPIDController(leftPivotController, rightPivotController, Constants.Pivot.SLOW_PID);
    initPIDController(leftPivotController, rightPivotController, Constants.Pivot.TURBO_PID);
  } 

  private void initPIDController(SparkMaxPIDController controller1, SparkMaxPIDController controller2,PID PID) {
    controller1.setP(PID.p, PID.s);
    controller1.setI(PID.i, PID.s);
    controller1.setD(PID.d, PID.s);
    controller1.setFF(PID.f, PID.s);

    controller2.setP(PID.p, PID.s);
    controller2.setI(PID.i, PID.s);
    controller2.setD(PID.d, PID.s);
    controller2.setFF(PID.f, PID.s);

  }

  // 0 is fast, 1 is slow, 2 is turbo
  public void setPositionPivots(double position, int slot) {
    leftPivotController.setReference(position, CANSparkMax.ControlType.kPosition, slot);
    rightPivotController.setReference(position, CANSparkMax.ControlType.kPosition, slot);
  }

  public double getLeftPivotEncoder() {
    return leftPivotEncoder.getPosition();
  }

  public double getRightPivotEncoder() {
    return rightPivotEncoder.getPosition();
  }

  public void climbPivotLeft(double speed) {
    leftPivot.set(speed);
  }

  public void climbPivotRight(double speed) {
    rightPivot.set(speed);
  }

  public void climbPivots(double speed) {
    leftPivot.set(speed);
    rightPivot.set(speed);
    System.out.println("testing pivot motor" + leftPivot.get());

  }

  public boolean pivotStalled() {
    return leftPivot.getOutputCurrent() > 15 && rightPivot.getOutputCurrent() > 15;
  }

  public double getCurrentPivotLeft() {
    return leftPivot.getOutputCurrent();
  }

  public double getCurrentPivotRight() {
    return rightPivot.getOutputCurrent();
  }

  public void resetEncoders() {
    leftPivotEncoder.setPosition(0);
    rightPivotEncoder.setPosition(0);
  }

  public void stop() {
    leftPivot.set(0);
    rightPivot.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
