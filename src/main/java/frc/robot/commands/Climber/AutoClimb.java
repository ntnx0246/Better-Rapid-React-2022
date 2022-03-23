package frc.robot.commands.Climber;

// import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class AutoClimb extends SequentialCommandGroup {
  private Climber climber;

  public AutoClimb(Climber climber) {
    addRequirements(climber);
    this.climber = climber;
  }

  @Override
  public void initialize() {
    addCommands(
      new ParallelDeadlineGroup(new InstantCommand(()->climber.setPositionPivots(41)), new ClimbDown(climber, false)),
      new ClimbDown(climber, true).withTimeout(3),
      new PivotRelative(climber, -20),
      new InstantCommand(()->climber.climb(0.2)).alongWith(new InstantCommand(()->climber.climbPivots(0.1))).withTimeout(2),
      new ParallelCommandGroup(new ClimbUp(climber), new PivotRelative(climber, -90)),
      new PivotRelative(climber, 10),
      new ParallelDeadlineGroup(new PivotRelative(climber, 100), new ClimbDown(climber, false))
    );
  }

}
