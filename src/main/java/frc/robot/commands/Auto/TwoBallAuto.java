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

  public TwoBallAuto(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX, double angle) {
    addCommands(
        // new DriveStraight(driveTrain, () -> 90).withTimeout(4).alongWith(
        //     new IntakeBalls(intake)).withTimeout(4),
        // new DriveStraight(driveTrain, () -> -90).withTimeout(5),
        // new TurnToAngle(driveTrain, navX, 180).withTimeout(5),
        // new DriveStraight(driveTrain, () -> 10).withTimeout(5),
        // ,
        // new DriveStraight(driveTrain, () -> -180).withTimeout(5)
        
        // new DriveStraight(driveTrain, () -> 45).withTimeout(3).alongWith(
        //     new IntakeBalls(intake).withTimeout(3)),
        // new TurnToAngle(driveTrain, navX, 192).withTimeout(2),
        // // pos 3 blue 192  
        // new DriveStraight(driveTrain, () -> 30).withTimeout(2),
        // new Shoot(intake, shooter, -2700, 1200).withTimeout(5),
        // // // new Shoot(intake, shooter, -2700, 1000).withTimeout(5),
        // new DriveStraight(driveTrain, () -> -100).withTimeout(3)

        new Shoot(intake, shooter, -3000, 1200).withTimeout(4),
        new TurnToAngle(driveTrain, navX, angle).withTimeout(2),
        new DriveStraight(driveTrain, () -> 45).withTimeout(2).alongWith(
            new IntakeBalls(intake).withTimeout(2)),
        new DriveStraight(driveTrain, () -> -45).withTimeout(2),
        new TurnToAngle(driveTrain, navX, -angle).withTimeout(2),
        new Shoot(intake, shooter, -3000, 1200).withTimeout(4),
        new DriveStraight(driveTrain, () -> -50).withTimeout(3)
    );
  }
}
