package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivots;

public class PivotRelative extends CommandBase {
  Pivots pivots;
  double angle;
  double target;
  boolean slow;
  
  public PivotRelative(Pivots pivots,double angle) {
    addRequirements(pivots); 
    this.angle = angle;
    this.pivots = pivots;
    this.slow = false;
  }

  public PivotRelative(Pivots pivots,double angle, boolean slow) {
    addRequirements(pivots); 
    this.angle = angle;
    this.pivots = pivots;
    this.slow = true;
  }

  @Override
  public void initialize() {
    if(slow){
      target = pivots.getRightPivotEncoder()+(angle*64/360);
      pivots.setPositionPivots1(pivots.getRightPivotEncoder()+(angle*64/360));
    } else {
      target = pivots.getRightPivotEncoder()+(angle*64/360);
      pivots.setPositionPivots(pivots.getRightPivotEncoder()+(angle*64/360));
    }
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
