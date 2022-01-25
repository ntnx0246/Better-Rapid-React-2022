// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

   private CANSparkMax motorController1; 
   private CANSparkMax motorController2; 

  public Intake() {
    motorController1 = new CANSparkMax(Constants.TOP_MOTOR, MotorType.kBrushless);
    motorController2 = new CANSparkMax(Constants.BOTTOM_MOTOR, MotorType.kBrushless);
  }

  public void intakeTopMotor(double speed){
    motorController1.set(speed);
  }

  public void intakeBottomMotor(double speed){
    motorController2.set(speed);
  }

  public void stop(){
    motorController1.set(0);
    motorController2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}