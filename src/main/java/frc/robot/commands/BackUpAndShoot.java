package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.utils.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// TODO inline command
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BackUpAndShoot extends ParallelCommandGroup {
  /** Creates a new BackUpAndShoot. */
  public BackUpAndShoot(DriveTrain driveTrain, Intake intake, Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new DriveStraight(driveTrain, Constants.BACK_UP_TO_SHOOT), new CargoManipulation(intake, shooter, false, false,-1));
  }
}
