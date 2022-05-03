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

  /** Creates a new TurnToAngle. */
  public TurnToAngle(DriveTrain driveTrain, NavX navX, double goalAngle) {
    addRequirements(driveTrain, navX);
    this.driveTrain = driveTrain;
    this.navX = navX;
    this.goalAngle = goalAngle;
  }

  @Override
  public void initialize() {
    navX.reset();
    driveTrain.setAngle(goalAngle);
  }

  @Override
  public void execute() {
    double errorAngle = goalAngle - navX.getAngle();
    double turnPower;
    if (Math.abs(goalAngle)<=10 && Math.abs(goalAngle) >= 0){
        turnPower = Math.pow(errorAngle, 0.580667)*0.0148639+0.0752756;
    } else{
        turnPower = Math.pow(errorAngle,0.706689)*0.0152966+0.0550678;
    }
    Math.min(turnPower, 0.3);
    if ((goalAngle - navX.getAngle()) < 0){
        driveTrain.tankDrive(-turnPower,turnPower);
    } else {
        driveTrain.tankDrive(turnPower, -turnPower);
    }
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