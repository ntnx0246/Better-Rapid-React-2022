package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivots;

public class Calibration extends CommandBase {
  Climber climber;
  Pivots pivots;
  Intake intake;
  boolean leftDone;
  boolean rightDone;
  boolean leftPivotDone;
  boolean rightPivotDone;
  boolean intakeDone;
  Timer timer;
  DriveTrain driveTrain;

  public Calibration(Climber climber, Pivots pivots, Intake intake, DriveTrain driveTrain) {
    addRequirements(climber);
    this.climber = climber;
    this.pivots = pivots;
    this.intake = intake;
    this.driveTrain = driveTrain;
    leftDone = false;
    rightDone = false;
    leftPivotDone = false;
    rightPivotDone = false;
    timer = new Timer();
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    climber.climb(Constants.Climber.CALIBRATION_SPEED);
    pivots.climbPivots(-Constants.Climber.CALIBRATION_SPEED);
    intake.setRaisingMotorSpeed(Constants.Intake.CALIBRATION_SPEED);
    driveTrain.setClimbMode(false);
  }

  @Override
  public void execute() {
    if (timer.get() > 0.1) {
      if (climber.getCurrentLeft() >= 20) {
        leftDone = true;
        climber.climbLeft(0);
      }
      if (climber.getCurrentRight() >= 20) {
        rightDone = true;
        climber.climbRight(0);
      }
      if (pivots.getCurrentPivotLeft() >= 20) {
        leftPivotDone = true;
        pivots.climbPivotLeft(0);
      }
      if (pivots.getCurrentPivotRight() >= 20) {
        rightPivotDone = true;
        pivots.climbPivotRight(0);
      }
      if (intake.getRaisingCurrent() >= 20) {
        intakeDone = true;
        intake.setRaisingMotorSpeed(0);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Climber Calibration Finished");
    timer.stop();
    climber.stop();
    climber.resetEncoders();
    pivots.resetEncoders();
    intake.resetEncoders();
  }

  @Override
  public boolean isFinished() {
    System.out.println("INTAKE: "+intakeDone);
    System.out.println("RIGHT CLIMBER: "+rightDone);
    System.out.println("LEFT CLIMBER: "+leftDone);
    return rightDone && leftDone && leftPivotDone && rightPivotDone && intakeDone;
  }

}
