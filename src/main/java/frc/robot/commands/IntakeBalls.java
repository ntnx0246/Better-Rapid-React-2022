// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private final Intake intake;
  public boolean isIntaking;
  private Timer timer;
  private double wait;
  private boolean isAuto;

  public IntakeBalls(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
    this.isAuto = false;
  }

  public IntakeBalls(Intake intake, double wait) {
    addRequirements(intake);
    this.intake = intake;
    this.wait = wait;
    this.isAuto = true;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    if (isAuto) {
      timer.reset();
      timer.start();
    }

    intake.intakeTopMotor(Constants.Intake.TOP_SPEED);
    intake.intakeBottomMotor(Constants.Intake.BOTTOM_SPEED);
    intake.intakeOutsideMotor(Constants.Intake.OUTSIDE_SPEED);

  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    if (isAuto && timer.get() > wait) {
      timer.stop();
      return true;
    }
    return false;
  }
}
