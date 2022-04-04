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

  public IntakeBalls(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
  }

  @Override
  public void initialize() {
    intake.intakeTopMotor(Constants.Intake.TOP_SPEED);
    intake.intakeBottomMotor(Constants.Intake.BOTTOM_SPEED);
    intake.intakeOutsideMotor(Constants.Intake.OUTSIDE_SPEED);
    intake.setRaisingMotorPosition(Constants.Intake.INTAKE_OUT_ENCODER);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    intake.setRaisingMotorPosition(0);
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
