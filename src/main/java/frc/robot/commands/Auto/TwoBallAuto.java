
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.utils.Constants;
import frc.robot.commands.CargoManipulation;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAuto extends SequentialCommandGroup {
    Intake intake;
    Shooter shooter;
    DriveTrain driveTrain;
    NavX navX;

    /** Creates a new TwoBallAuto. */
    public TwoBallAuto(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(
                new DriveStraight(driveTrain, Constants.Auto.TwoBall.TWOBALLAUTO_GET_SECONDBALL_GOAL).alongWith(
                    new CargoManipulation(intake, shooter, true, true,Constants.Auto.TwoBall.TWOBALLAUTO_VELOCITY)
                ),
                new TurnToAngle(driveTrain, navX, Constants.Auto.TwoBall.TWOBALLAUTO_ANGLE),
                new DriveStraight(driveTrain, Constants.Auto.TwoBall.TWOBALLAUTOFORWARD_GOAL),
                new CargoManipulation(intake, shooter, false, true, Constants.Auto.TwoBall.TWOBALLAUTO_VELOCITY),
                new DriveStraight(driveTrain, Constants.Auto.TwoBall.TWOBALLAUTOBACKWARD_GOAL));

    }
}
