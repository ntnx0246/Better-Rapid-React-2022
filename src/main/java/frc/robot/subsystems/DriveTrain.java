// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
 

public class DriveTrain extends SubsystemBase {

  private TalonFX frontL; 
  private TalonFX frontR; 
  private TalonFX backL; 
  private TalonFX backR;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonFX(0);
    frontR = new TalonFX(1);
    backL = new TalonFX(2);
    backR = new TalonFX(3);

    backL.follow(frontL);
    backR.follow(frontR);

    frontL.setInverted(true);
    backL.setInverted(true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //frontL.getSelectedSensorVelocity();
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }
}
