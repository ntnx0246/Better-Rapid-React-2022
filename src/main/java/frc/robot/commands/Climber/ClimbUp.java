// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
public class ClimbUp extends CommandBase {
  private final Climber climber;

  public ClimbUp(Climber climber) {
    addRequirements(climber);
    this.climber = climber;
  }

  @Override
  public void initialize() {
    climber.climb(Constants.Climber.UP_SPEED);
    climber.selectProfile(1);
    climber.setPositionLeft(Constants.Climber.UP_ENCODER_LEFT);
    climber.setPositionRight(Constants.Climber.UP_ENCODER_RIGHT);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  @Override
  public boolean isFinished() {
    if (Math.abs(climber.getRightEncoderCount()-Constants.Climber.UP_ENCODER_RIGHT)<1000
     && Math.abs(climber.getLeftEncoderCount()-Constants.Climber.UP_ENCODER_LEFT)<1000){
      return true;
    }
    return false;
  }
}
