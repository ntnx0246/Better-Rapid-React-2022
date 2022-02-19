// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /** Creates a new Shoot. */
  private final Shooter shooter;

  public Shoot(Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    this.shooter = shooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("shoot");
    shooter.setSpeed(Constants.SHOOTER_SPEED);
    //shooter.setVelocity(Constants.SHOOTER_VELOCITY);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    if (checks if error < tolerance){
      count ++
    }

    somewhere- check if count>10

    intake.IntakeCargo(intake, false)
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
