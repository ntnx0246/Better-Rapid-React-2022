// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveStraight extends CommandBase {
  /** Creates a new DriveStraight. */
  private final DriveTrain driveTrain;
  private DoubleSupplier suppliedGoal; // put goal in as a constant
  int count = 0;

  public DriveStraight(DriveTrain driveTrain, DoubleSupplier suppliedGoal) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.suppliedGoal = suppliedGoal;
  }

  @Override
  public void initialize() {
    driveTrain.resetEncoders();
    driveTrain.setLeftPID(Constants.DriveTrain.PID.s, Constants.DriveTrain.PID.p, Constants.DriveTrain.PID.i,
        Constants.DriveTrain.PID.d);
    driveTrain.setRightPID(Constants.DriveTrain.PID.s, Constants.DriveTrain.PID.p, Constants.DriveTrain.PID.i,
        Constants.DriveTrain.PID.d);
    driveTrain.setPosition(driveTrain.getNativeUnitsFromInches(suppliedGoal.getAsDouble()));
    Timer.delay(.1);
  }

  @Override
  public void execute() {
    double leftError = Math.abs(
        driveTrain.getNativeUnitsFromInches(suppliedGoal.getAsDouble()) - driveTrain.getLeftEncoderCount());
    double rightError = Math.abs(
        driveTrain.getNativeUnitsFromInches(suppliedGoal.getAsDouble()) + driveTrain.getRightEncoderCount());

    if ((leftError <= Constants.DriveTrain.ERROR_THRESHOLD) && (rightError <= Constants.DriveTrain.ERROR_THRESHOLD)) {
      count++;
    } else {
      count = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
    driveTrain.printEncoders();
    driveTrain.printInches();
  }

  @Override
  public boolean isFinished() {
    return count >= 10;

  }
}