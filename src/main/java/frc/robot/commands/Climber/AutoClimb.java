package frc.robot.commands.Climber;

// import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Pivots;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutoClimb extends SequentialCommandGroup {

  public AutoClimb(Climber climber, Pivots pivots) {
    addRequirements(climber);
    addCommands(
      new PivotRelative(pivots, -230)
      // unused 
      // new InstantCommand(()->climber.setPositionPivots(-41)),
      // new ClimbDown(climber, true)
      // new ParallelDeadlineGroup(, new ClimbDown(climber, false))
      // new ClimbDown(climber, 0, 0).withTimeout(3),
      // new PivotRelative(pivots, 20),
      // // untested
      // // new InstantCommand(()->climber.climb(0.2)).alongWith(new InstantCommand(()->climber.climbPivots(0.1))).withTimeout(2),
      // new ParallelDeadlineGroup(new PivotRelative(pivots, 90), new InstantCommand(()->climber.climb(0.2))),
      // new ClimbUp(climber).withTimeout(3),
      // new PivotRelative(pivots, 10),
      // new ClimbDown(climber, 210000, 210000).withTimeout(2),
      // new ParallelDeadlineGroup(new PivotRelative(pivots, -50), new ClimbDown(climber, false))
    );
  }
}
