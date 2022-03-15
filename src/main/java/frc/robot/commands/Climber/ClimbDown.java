// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;

public class ClimbDown extends CommandBase {
  /** Creates a new ClimbDown. */

  private final Climber climber;
  private int goal;
  private double climberEncoder;

  public ClimbDown(Climber climber) {
    addRequirements(climber);
    this.climber = climber;
  }

  @Override
  public void initialize() {
    climber.climb(Constants.Climber.DOWN_SPEED);
    climberEncoder = Math.abs(climber.getLeftEncoderCount());
    if (climberEncoder > 50000) {

      goal = 21000; // 25000
      climber.selectProfile(0);
    } else {
      goal = 1000; // 1000
      climber.climb(-0.4);
    }
  }

  @Override
  public void execute() {
    if (goal != 1000) {
      climberEncoder = Math.abs(climber.getLeftEncoderCount());
      if (climberEncoder < 100000) { // 25000
        climber.setPositionLeft(goal * 0.9762);
        climber.setPositionRight(goal);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    // climber.printEncoders();
    climber.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
