package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Pivots;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutoClimb extends SequentialCommandGroup {

  public AutoClimb(Climber climber, Pivots pivots) {
    addRequirements(climber);
    addCommands(
      // new PivotRelative(pivots, -230).withTimeout(2),
      // new ClimbDown(climber, 1000, 1000).withTimeout(2)
      //   .andThen(new PivotRelative(pivots, 17).withTimeout(3)),
      // new ClimbUp(climber, 40000, 40000).withTimeout(2)
      //   .andThen(new PivotRelative(pivots, -10).withTimeout(3)),
      // new PivotRelative(pivots, 90).withTimeout(3),
      // new ClimbUp(climber).withTimeout(3),
      // new PivotRelative(pivots, -10).withTimeout(3),
      // new ClimbDown(climber, 220000, 220000).withTimeout(2),
      // new PivotRelative(pivots, -100).withTimeout(3),
      // new ClimbDown(climber, 150000, 150000).withTimeout(2),
      // new InstantCommand(()-> pivots.setPositionPivots(0)).withTimeout(2)

      new PivotRelative(pivots, -230).withTimeout(2),
      new ClimbDown(climber, 1000, 1000).withTimeout(2).andThen(new PivotRelative(pivots, 20).withTimeout(3)),
      new ClimbUp(climber, 40000, 40000).withTimeout(2).andThen(new PivotRelative(pivots, -10).withTimeout(3)),
      new WaitCommand(2),
      new PivotRelative(pivots, 90, true).withTimeout(3),
      new ClimbUp(climber).withTimeout(4),
      new PivotRelative(pivots, -15).withTimeout(4),
      new ClimbDown(climber, 220000, 220000).withTimeout(3),
      new PivotRelative(pivots, -100).withTimeout(3),
      new ClimbDown(climber, 150000, 150000).withTimeout(2),
      new ClimbDown(climber, 175000, 175000).withTimeout(3),
      new InstantCommand(()-> pivots.setPositionPivots(0)).withTimeout(2)
    );
  }
}
