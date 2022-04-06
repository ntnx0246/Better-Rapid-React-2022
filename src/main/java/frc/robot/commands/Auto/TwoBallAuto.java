
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeBalls;
import frc.robot.commands.Shoot;
import frc.robot.commands.DriveTrain.DriveStraight;
import frc.robot.commands.DriveTrain.TurnToAngle;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;

public class TwoBallAuto extends SequentialCommandGroup {
    Intake intake;
    Shooter shooter;
    DriveTrain driveTrain;
    NavX navX;

    public TwoBallAuto(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
        addCommands(
                new DriveStraight(driveTrain, () -> 90).withTimeout(4).alongWith(
                        new IntakeBalls(intake)).withTimeout(4),
                new DriveStraight(driveTrain, () -> -90).withTimeout(5),
                new TurnToAngle(driveTrain, navX, 180).withTimeout(5),
                new DriveStraight(driveTrain, () -> 10).withTimeout(5),
                new Shoot(intake, shooter, -2550, 1000).withTimeout(5),
                new DriveStraight(driveTrain, () -> -180).withTimeout(5));
    }
}
