// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ChangeDriveMode extends InstantCommand {
  private final DriveTrain driveTrain;

  public ChangeDriveMode(DriveTrain driveTrain) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    if (driveTrain.getSlowMode()) {
      driveTrain.setSlowMode(false);
    } else {
      driveTrain.setSlowMode(true);
    }
  }
}
