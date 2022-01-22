// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

   CANSparkMax motorController1; 
   CANSparkMax motorController2; 

  public Intake() {
    motorController1 = new CANSparkMax(0, MotorType.kBrushless);
    motorController2 = new CANSparkMax(1, MotorType.kBrushless);
  }

  public void intakeBalls(double speed){
    motorController1.set(speed);
    motorController2.set(-speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}