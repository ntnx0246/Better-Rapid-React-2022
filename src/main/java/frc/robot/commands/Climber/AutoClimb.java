package frc.robot.commands.Climber;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Pivots;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class AutoClimb extends SequentialCommandGroup {

  public AutoClimb(Climber climber, Pivots pivots) {
    addRequirements(climber);
    addCommands(
      new InstantCommand(()-> pivots.setPositionPivots(-41, 0)).withTimeout(2),
      new ClimbDown(climber, 1000, 1000).withTimeout(2).andThen(new PivotRelative(pivots, 20, 0).withTimeout(1)),
      new ClimbUp(climber, 55000, 55000, 1).withTimeout(0.5).andThen(new PivotRelative(pivots, -10, 0).withTimeout(0.5)),
      new PivotRelative(pivots, 90, 1).withTimeout(2).alongWith(new ClimbUp(climber, 2).withTimeout(3)),
      new PivotRelative(pivots, -25, 1).withTimeout(1),
      new ClimbDown(climber, 190000, 190000).withTimeout(1),
      new PivotRelative(pivots, -95, 0).withTimeout(1),
      new ClimbDown(climber, 100000, 100000).withTimeout(1.5),
      new InstantCommand(()-> pivots.setPositionPivots(-41, 0)).withTimeout(1)

      // new InstantCommand(()-> pivots.setPositionPivots(-41, 0)).withTimeout(2),
      // new ClimbDown(climber, 1000, 1000).withTimeout(2).andThen(new PivotRelative(pivots, 20, 0).withTimeout(1)),
      // new ClimbUp(climber, 45000, 45000).withTimeout(1).andThen(new PivotRelative(pivots, -10, 0).withTimeout(1)),
      // new WaitCommand(1),
      // new PivotRelative(pivots, 90, 1).withTimeout(2).alongWith(new ClimbUp(climber).withTimeout(2.5)),
      // new PivotRelative(pivots, 180, 2).withTimeout(3),
      // new ClimbDown(climber, 100000, 100000).withTimeout(2),
      // new InstantCommand(()-> pivots.setPositionPivots(-41, 0)).withTimeout(2)
    );
  }
}
