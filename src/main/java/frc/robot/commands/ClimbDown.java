// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimbDown extends CommandBase {
  /** Creates a new ClimbDown. */
  
  private final Climber climber;
  
  public ClimbDown(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber); 
    this.climber = climber;  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.climb(Constants.CLIMBER_DOWN_SPEED);
    SmartDashboard.putNumber("climber Speed", 10);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
