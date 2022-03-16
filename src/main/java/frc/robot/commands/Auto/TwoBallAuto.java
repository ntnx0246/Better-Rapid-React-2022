
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.Constants;
import frc.robot.commands.CargoManipulation;
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
            new DriveStraight(driveTrain, Constants.Auto.TwoBall.GET_SECONDBALL).alongWith(
                new CargoManipulation(intake, shooter, true)
            ),
            new DriveStraight(driveTrain, Constants.Auto.TwoBall.SPACE_TO_TURN),
            new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE),
            new DriveStraight(driveTrain, Constants.Auto.TwoBall.MOVE_TOWARDS_SHOOT),
            new CargoManipulation(intake, shooter, true, Constants.Auto.TwoBall.SHOOT_VELOCITY),
            new DriveStraight(driveTrain, Constants.Auto.TwoBall.CROSS_LINE)
        );
    }
}
