// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.LogitechGamingPad;

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
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveTrain.getSlowMode()) {
      driveTrain.arcadeDrive((drivePad.getRightAnalogXAxis() * -Constants.DriveTrain.SLOW_MODE),
          (drivePad.getLeftAnalogYAxis() * -Constants.DriveTrain.SLOW_MODE));
      SmartDashboard.putBoolean("Slow Mode: ", true);
    } else {
      driveTrain.arcadeDrive((drivePad.getRightAnalogXAxis() * -Constants.DriveTrain.REGULAR_MODE_TURN),
          (drivePad.getLeftAnalogYAxis() * -Constants.DriveTrain.REGULAR_MODE));
      SmartDashboard.putBoolean("Slow Mode: ", false);
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
