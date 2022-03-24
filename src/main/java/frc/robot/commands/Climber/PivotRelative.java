package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class PivotRelative extends CommandBase {
  Climber climber;
  double angle;
  boolean forward;
  
  public PivotRelative(Climber climber, boolean forward) {
    addRequirements(climber);
    this.climber = climber;    
    this.forward = forward;
    this.angle = 0;
  }

  @Override
  public void initialize() {
    System.out.println("want to be: "+climber.getRightPivotEncoder()+(5.0*64/360));
    System.out.println("what its at: "+climber.getRightPivotEncoder());
    if(forward){
        climber.setPositionPivots(climber.getRightPivotEncoder()+(10.0*64/360));
        // climber.climbPivots(0.5);
    } else {
        climber.setPositionPivots(climber.getRightPivotEncoder()+(-10.0*64/360));
    }
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
    return false;
  }
}
