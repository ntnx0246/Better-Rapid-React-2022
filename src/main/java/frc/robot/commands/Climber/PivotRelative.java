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
    if(forward){

        // climber.setPositionPivots(climber.getLeftPivotEncoder()+(angle+5)*64*2048/360);
        climber.climbPivots(0.5);
        System.out.println(climber.getLeftEncoderCount());
    } else {
        climber.setPositionPivots(climber.getLeftPivotEncoder()+(angle-5)*64*2048/360);
    }
  }

  @Override
  public void execute() {
    
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
