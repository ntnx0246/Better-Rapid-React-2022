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

    leftPivotController.setP(Constants.Pivot.P_0, 0);
    leftPivotController.setI(Constants.Pivot.I_0, 0);
    leftPivotController.setD(Constants.Pivot.D_0, 0);
    leftPivotController.setFF(Constants.Pivot.F_0, 0);

    rightPivotController.setP(Constants.Pivot.P_0, 0);
    rightPivotController.setI(Constants.Pivot.I_0, 0);
    rightPivotController.setD(Constants.Pivot.D_0, 0);
    rightPivotController.setFF(Constants.Pivot.F_0, 0);

    leftPivotController.setP(Constants.Pivot.P_1, 1);
    leftPivotController.setI(Constants.Pivot.I_1, 1);
    leftPivotController.setD(Constants.Pivot.D_1, 1);
    leftPivotController.setFF(Constants.Pivot.F_1, 1);

    rightPivotController.setP(Constants.Pivot.P_1, 1);
    rightPivotController.setI(Constants.Pivot.I_1, 1);
    rightPivotController.setD(Constants.Pivot.D_1, 1);
    rightPivotController.setFF(Constants.Pivot.F_1, 1);

    leftPivotController.setP(Constants.Pivot.P_2, 2);
    leftPivotController.setI(Constants.Pivot.I_2, 2);
    leftPivotController.setD(Constants.Pivot.D_2, 2);
    leftPivotController.setFF(Constants.Pivot.F_2, 2);

    rightPivotController.setP(Constants.Pivot.P_2, 2);
    rightPivotController.setI(Constants.Pivot.I_2, 2);
    rightPivotController.setD(Constants.Pivot.D_2, 2);
    rightPivotController.setFF(Constants.Pivot.F_2, 2);

  }

  // 0 is fast, 1 is slow, 2 is turbo
  public void setPositionPivots(double position, int slot){
    leftPivotController.setReference(position, CANSparkMax.ControlType.kPosition, slot);
    rightPivotController.setReference(position, CANSparkMax.ControlType.kPosition, slot);
    System.out.println("USING SPECIFICSLOT: "+slot);
    // leftPivotEncoder.setPosition(position);
    // rightPivotEncoder.setPosition(position);
  }

  // public void setPositionPivots1(double position){
  //   leftPivotController.setReference(position, CANSparkMax.ControlType.kPosition, 1);
  //   rightPivotController.setReference(position, CANSparkMax.ControlType.kPosition, 1);
  //   // leftPivotEncoder.setPosition(position);
  //   // rightPivotEncoder.setPosition(position);
  // }

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

  public boolean pivotStalled(){
    return leftPivot.getOutputCurrent() > 15 && rightPivot.getOutputCurrent() > 15;
  }

  public double getCurrentPivotLeft(){
    return leftPivot.getOutputCurrent();
  }

  public double getCurrentPivotRight(){
    return rightPivot.getOutputCurrent();
  }

  public void resetEncoders(){
    leftPivotEncoder.setPosition(0);
    rightPivotEncoder.setPosition(0);
  }

  public void stop(){
    leftPivot.set(0);
    rightPivot.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
