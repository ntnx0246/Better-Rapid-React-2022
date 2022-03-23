package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class PivotRelative extends CommandBase {
  Climber climber;
  double angle;
  double target;
  
  public PivotRelative(Climber climber, double angle) {
    addRequirements(climber);
    this.climber = climber;    
    this.angle = angle;
  }

  @Override
  public void initialize() {
    target = climber.getRightPivotEncoder()+(angle*64/360);
    climber.setPositionPivots(climber.getRightPivotEncoder()+(angle*64/360));
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  @Override
  public boolean isFinished() {
    if (Math.abs(climber.getRightPivotEncoder()-target)<0.05){
      return true;
    }
    return false;
  }
}
