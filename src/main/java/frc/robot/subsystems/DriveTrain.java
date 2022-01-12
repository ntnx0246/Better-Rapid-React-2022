// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private TalonFX frontR;
  private TalonFX frontL;
  private TalonFX backR;
  private TalonFX backL;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontR = new TalonFX(Constants.frontRightID);
    frontL = new TalonFX(Constants.frontLeftID);
    backR = new TalonFX(Constants.backRightID);
    backL = new TalonFX(Constants.backLeftID);
    
    backR.follow(frontR);
    backL.follow(frontL);

    frontL.setInverted(true);
    backL.setInverted(true);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y-x);
    frontR.set(ControlMode.PercentOutput, y+x);
  }

  public void stop(){
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
