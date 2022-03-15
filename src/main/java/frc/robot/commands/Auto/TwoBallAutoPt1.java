// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.utils.Constants;
import frc.robot.commands.CargoManipulation;
import frc.robot.commands.DriveStraight;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallAutoPt1 extends ParallelCommandGroup {
  /** Creates a new TwoBallAutoPt1. */
  public TwoBallAutoPt1(DriveTrain driveTrain, Shooter shooter, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new DriveStraight(driveTrain, Constants.Auto.TwoBall.TWOBALLAUTO_GET_SECONDBALL_GOAL),
        new CargoManipulation(intake, shooter, true, true,Constants.Auto.TwoBall.TWOBALLAUTO_VELOCITY));
  }
}
