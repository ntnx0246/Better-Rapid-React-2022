// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;

public class AutoClimb extends CommandBase {
  /** Creates a new AutoClimb. */
  private Climber climber;
  private double goal;
  private int count;

  public AutoClimb(Climber climber, double goal) {
    addRequirements(climber);
    this.climber = climber;
    this.goal = goal;
    count = 0;
  }

  @Override
  public void initialize() {
    climber.resetEncoders();
    climber.setPosition(goal);
    // check encoder for each side
    // get current
    // when current stall the velocity goes down and current goes up
  }

  @Override
  public void execute() {
    double error = Math.abs(goal - climber.getLeftEncoderCount());
    if (error <= Constants.Climber.CLIMB_TOLERANCE) {
      count++;
    } else {
      count = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  @Override
  public boolean isFinished() {
    // return false;
    return count >= 10;
  }
}
