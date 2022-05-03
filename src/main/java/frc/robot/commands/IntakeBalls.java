package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.utils.ShuffleBoard;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeBalls extends CommandBase {
  private final Intake intake;
  private final ColorSensor colorSensor;
  private final Shooter shooter;
  private boolean usingColorSensor;
  private final int teamAlliance = ShuffleBoard.getTeamAlliance(); // -1 no team found 0 red 1 blue

  public IntakeBalls(Intake intake, ColorSensor colorSensor, Shooter shooter) {
    addRequirements(intake);
    this.intake = intake;
    this.colorSensor = colorSensor;
    usingColorSensor = true;
    this.shooter = shooter;
  }

  public IntakeBalls(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
    this.colorSensor = null;
    usingColorSensor = false;
    shooter = null;
  }

  @Override
  public void initialize() {
    intake.intakeTopMotor(Constants.Intake.TOP_SPEED);
    intake.intakeBottomMotor(Constants.Intake.BOTTOM_SPEED);
    intake.intakeOutsideMotor(Constants.Intake.OUTSIDE_SPEED);
    intake.setRaisingMotorPosition(-Constants.Intake.INTAKE_OUT_ENCODER);

  }

  @Override
  public void execute() {

  }

  @Override
  public void end(boolean interrupted) {
    if (usingColorSensor) {
      int numBalls = colorSensor.getNumBalls();
      int ball1 = colorSensor.getBall1();
      int ball2 = colorSensor.getBall1();
      if (numBalls == 1 && ball1 != teamAlliance) {
        intake.ejectThroughIntake(1);
      } else if (numBalls == 2) {
        // case: yours theirs
        if (ball1 == teamAlliance && ball2 != teamAlliance) {
          intake.ejectThroughIntake(1);
        } else if (ball1 != teamAlliance && ball2 == teamAlliance) {
          // case theirs yours
          intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
          intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
          shooter.setVelocity(Constants.Shooter.DUMP.frontRPM);
          shooter.setRollerVelocity(Constants.Shooter.DUMP.backRPM);
        } else {
          intake.ejectThroughIntake(2);
        }
      }
    }
    intake.setRaisingMotorPosition(0);
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
