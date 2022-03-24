package frc.robot.commands.Climber;

// import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutoClimb extends SequentialCommandGroup {

  public AutoClimb(Climber climber) {
    addRequirements(climber);
    addCommands(
      new PivotRelative(climber, -230),
      // unused 
      // new InstantCommand(()->climber.setPositionPivots(-41)),
      // new ClimbDown(climber, true)
      // new ParallelDeadlineGroup(, new ClimbDown(climber, false))
      new ClimbDown(climber, true).withTimeout(3),
      new PivotRelative(climber, 20),
      // untested
      // new InstantCommand(()->climber.climb(0.2)).alongWith(new InstantCommand(()->climber.climbPivots(0.1))).withTimeout(2),
      new ParallelDeadlineGroup(new PivotRelative(climber, 90), new InstantCommand(()->climber.climb(0.2))),
      new ClimbUp(climber).withTimeout(3),
      new PivotRelative(climber, 10),
      new ParallelDeadlineGroup(new PivotRelative(climber, -50), new ClimbDown(climber, false))
    );
  }
}
