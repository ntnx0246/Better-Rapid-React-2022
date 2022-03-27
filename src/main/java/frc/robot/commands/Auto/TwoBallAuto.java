
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.Constants;
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
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.GET_SECONDBALL).withTimeout(4).alongWith(
                        new IntakeBalls(intake, 5)).withTimeout(4),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.SPACE_TO_TURN).withTimeout(5),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE).withTimeout(5),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.MOVE_TOWARDS_SHOOT).withTimeout(5),
                new Shoot(intake, shooter, 5, Constants.Auto.TwoBall.SHOOT_VELOCITY).withTimeout(5),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.CROSS_LINE).withTimeout(5));
    }
}
