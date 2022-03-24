package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivots;

public class PivotRelative extends CommandBase {
  Pivots pivots;
  double angle;
  double target;
  
  public PivotRelative(Pivots pivots,double angle) {
    addRequirements(pivots); 
    this.angle = angle;
    this.pivots = pivots;
  }

  @Override
  public void initialize() {
    target = pivots.getRightPivotEncoder()+(angle*64/360);
    pivots.setPositionPivots(pivots.getRightPivotEncoder()+(angle*64/360));
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {
    pivots.stop();
  }

  @Override
  public boolean isFinished() {
    if (Math.abs(pivots.getRightPivotEncoder()-target)<0.05){
      return true;
    }
    return false;
  }
}
