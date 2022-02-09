// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.LogitechGamingPad;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  DriveTrain driveTrain;
  LogitechGamingPad drivePad;
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(DriveTrain driveTrain, LogitechGamingPad drivePad) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.drivePad = drivePad;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveTrain.getSlowMode()) {
      driveTrain.setSlowMode(false);
      driveTrain.arcadeDrive((drivePad.getRightAnalogXAxis()*Constants.REGULAR_MODE), (drivePad.getLeftAnalogYAxis()*Constants.REGULAR_MODE));
    } else {
      driveTrain.setSlowMode(true);
      driveTrain.arcadeDrive((drivePad.getRightAnalogXAxis()*Constants.SLOW_MODE), (drivePad.getLeftAnalogYAxis()*Constants.SLOW_MODE));
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
