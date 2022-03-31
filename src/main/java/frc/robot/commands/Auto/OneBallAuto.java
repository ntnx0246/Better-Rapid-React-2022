// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.Constants;
import frc.robot.commands.Shoot;
import frc.robot.commands.DriveTrain.DriveStraight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class OneBallAuto extends SequentialCommandGroup {

  public OneBallAuto(Intake intake, Shooter shooter, DriveTrain driveTrain) {

    addCommands(
      new Shoot(intake, shooter, 5, Constants.Auto.OneBall.SHOOT_VELOCITY, 0),
      new DriveStraight(driveTrain, () -> Constants.Auto.OneBall.CROSS_LINE)
    );
  }
}
