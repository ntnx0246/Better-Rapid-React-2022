// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
// import edu.wpi.first.wpilibj.Timer;

public class CargoManipulation extends CommandBase {
  private final Intake intake;
  private Shooter shooter;
  public boolean isIntaking;
  public boolean isAuto = false;
  private Timer timer;
  public int shooterVelocity;

  public CargoManipulation(Intake intake, Shooter shooter, boolean isAuto) {
    addRequirements(intake, shooter);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = true;
    this.isAuto = isAuto;
    timer = new Timer();
  }
  public CargoManipulation(Intake intake, Shooter shooter, boolean isAuto, int shooterVelocity) {
    addRequirements(intake, shooter);
    this.intake = intake;
    this.shooter = shooter;
    this.isIntaking = false;
    this.isAuto = isAuto;
    timer = new Timer();
    this.shooterVelocity = shooterVelocity;
  }

  @Override
  public void initialize() {
    if (isAuto) {
      timer.reset();
      timer.start();
    }

    if (isIntaking) {
      intake.intakeTopMotor(Constants.Intake.TOP_SPEED);
      intake.intakeBottomMotor(Constants.Intake.BOTTOM_SPEED);
      intake.intakeOutsideMotor(Constants.Intake.OUTSIDE_SPEED);
    } else if(shooterVelocity == -1) {
      shooter.setVelocity(shooter.getShooterVelocity());
    } else {
      shooter.setVelocity(shooterVelocity);
    }
  }

  // Called every time the scheduler runs while the command is scheduled
  double error = 0;

  @Override
  public void execute() {
    if (shooterVelocity == -1) {
      error = Math.abs(shooter.getShooterVelocity() - shooter.getLeftVelocity());
      if (isIntaking == false && error <= Constants.Shooter.RPM_TOLERANCE) {
        intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
        intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
      }
    } else {
      error = Math.abs(shooterVelocity - shooter.getLeftVelocity());
      if (isIntaking == false && error <= Constants.Shooter.RPM_TOLERANCE) {
        intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
        intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.stop();
    shooter.stop();
  }

  @Override
  public boolean isFinished() {
    if (isAuto && timer.get() > 5) {
      timer.stop();
      return true;
    }
    return false;
  }
}
