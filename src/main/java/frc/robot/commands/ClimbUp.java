// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.LinearServo;

public class ClimbUp extends CommandBase {
  /** Creates a new ClimbTeleop. */

  private final Climber climber;
  private final LinearServo servo;

  public ClimbUp(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
    servo = null;
  }

  public ClimbUp(Climber climber, LinearServo servo) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.servo = servo;
    // servo.setPosition(1);
    this.climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.climb(Constants.CLIMBER_UP_SPEED);
    climber.setPositionLeft(Constants.CLIMBER_UP_ENCODER_LEFT);
    climber.setPositionRight(Constants.CLIMBER_UP_ENCODER_RIGHT);
    System.out.println("stuff is wrong");
    // if (servo != null) {
    //   servo.setPosition(0);
    // }
    climber.setInitalized(true);
    // climber.setPositionLeft(1000);
    // climber.setPositionRight(1000);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (servo != null) {
    //   servo.setPosition(0);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
    // if (servo != null) {
    //   servo.setPosition(1);
    // }
    // servo.setPosition(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
