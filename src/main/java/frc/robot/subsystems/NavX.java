// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NavX extends SubsystemBase {
  /** Creates a new NavX. */
  private final AHRS navX;
  public NavX() {
    navX = new AHRS(Port.kMXP, (byte)50);
  }

  public double getAngle() {
    return navX.getAngle();
  }
  
  public void reset() {
    navX.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}