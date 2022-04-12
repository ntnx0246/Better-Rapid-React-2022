// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class TurnToAngle extends CommandBase {
  private final DriveTrain driveTrain;
  private final NavX navX;
  private double goalAngle;
  private double currentAngle;

  /** Creates a new TurnToAngle. */
  public TurnToAngle(DriveTrain driveTrain, NavX navX, double goalAngle) {
    addRequirements(driveTrain, navX);
    this.driveTrain = driveTrain;
    this.navX = navX;
    this.goalAngle = goalAngle;
    this.currentAngle = 0.0;
  }

  @Override
  public void initialize() {
    navX.reset();
    driveTrain.setAngle(goalAngle);
  }

  @Override
  public void execute() {
    // currentAngle = navX.getAngle();
    // double errorAngle = goalAngle - currentAngle;
    // double power = (errorAngle / goalAngle)*0.75;
    // driveTrain.tankDrive(power, -power);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  @Override
  public boolean isFinished() {
    double errorAngle = goalAngle - navX.getAngle();
    return (errorAngle < Constants.DriveTrain.ANGLE_TOLERANCE);
  }

}