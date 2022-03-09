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
    addRequirements(climber, servo);
    this.servo = servo;
    servo.setPosition(0);
    this.climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.climb(Constants.CLIMBER_UP_SPEED);
    climber.setPositionLeft(Constants.CLIMBER_UP_ENCODER_LEFT);
    climber.setPositionRight(Constants.CLIMBER_UP_ENCODER_RIGHT);
    if(servo != null){
      servo.setPosition(0.5);
    }
    // climber.setPositionLeft(1000);
    // climber.setPositionRight(1000);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
    servo.setPosition(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
