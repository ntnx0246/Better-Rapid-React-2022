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

public class ThreeBallAuto extends SequentialCommandGroup {
    Intake intake;
    Shooter shooter;
    DriveTrain driveTrain;
    NavX navX;

    public ThreeBallAuto(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
        addCommands(
                new Shoot(intake, shooter, 5, -3200),
                new DriveStraight(driveTrain, () -> 10),
                new TurnToAngle(driveTrain, navX, 180),
                new DriveStraight(driveTrain, () -> 90).alongWith(
                        new IntakeBalls(intake)),
                new TurnToAngle(driveTrain, navX, 180),
                new DriveStraight(driveTrain, () -> 90).alongWith(
                        new IntakeBalls(intake)),
                new TurnToAngle(driveTrain, navX, 180),
                new DriveStraight(driveTrain, () -> -180),
                new TurnToAngle(driveTrain, navX, 180),
                new DriveStraight(driveTrain, () -> -90),
                new Shoot(intake, shooter, 5, -3200));
    }
}