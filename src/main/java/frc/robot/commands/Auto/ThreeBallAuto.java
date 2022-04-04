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
                new Shoot(intake, shooter, 5, Constants.Auto.TwoBall.SHOOT_VELOCITY),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.MOVE_TOWARDS_SHOOT),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.GET_SECONDBALL).alongWith(
                        new IntakeBalls(intake)),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.GET_SECONDBALL).alongWith(
                        new IntakeBalls(intake)),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.CROSS_LINE),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TURN_ANGLE),
                new DriveStraight(driveTrain, () -> Constants.Auto.TwoBall.SPACE_TO_TURN),
                new Shoot(intake, shooter, 5, Constants.Auto.TwoBall.SHOOT_VELOCITY));
    }
}