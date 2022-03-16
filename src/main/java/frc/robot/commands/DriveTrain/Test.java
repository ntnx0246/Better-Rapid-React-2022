// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Test extends CommandBase {
  /** Creates a new Test. */
  // Timer timer;
  DriveTrain driveTrain;
  double goal;
  double error;

  public Test(DriveTrain driveTrain) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    goal = 95000;
    // timer = new Timer();
  }

  @Override
  public void initialize() {
    // timer.reset();
    driveTrain.resetEncoders();
    // timer.start();

  }

  @Override
  public void execute() {
    error = goal - driveTrain.getLeftEncoderCount();
    driveTrain.arcadeDrive(0, (-error / goal) * .4);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  @Override
  public boolean isFinished() {
    // return timer.get() > 1;
    return false;
    // return driveTrain.getLeftEncoderCount() > goal;
  }
}
