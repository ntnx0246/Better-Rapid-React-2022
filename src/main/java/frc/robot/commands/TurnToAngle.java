// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class TurnToAngle extends CommandBase {
  private final DriveTrain driveTrain;
  private final NavX navX;
  private double goalAngle;
  private double currentAngle;
  /** Creates a new TurnToAngle. */
  public TurnToAngle(DriveTrain driveTrain, NavX navX, double goalAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain, navX);
    this.driveTrain = driveTrain;
    this.navX = navX;
    this.goalAngle = goalAngle;
    this.currentAngle = 0.0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    navX.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentAngle = navX.getAngle();
    double errorAngle = goalAngle - currentAngle;
    double power = errorAngle/goalAngle;
    driveTrain.tankDrive(power, -power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double errorAngle = goalAngle - currentAngle;
    return (errorAngle < Constants.ERROR_ANGLE_TOLERANCE);
  }

}