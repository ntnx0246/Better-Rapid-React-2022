package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
// import frc.robot.subsystems.LinearServo2;

public class Calibration extends CommandBase {
  Climber climber;
  boolean leftDone;
  boolean rightDone;
  Timer timer;
  // LinearServo2 servo;

  public Calibration(Climber climber) {
    addRequirements(climber);
    this.climber = climber;
    leftDone = false;
    rightDone = false;
    timer = new Timer();
    // this.servo = servo;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    climber.climb(Constants.Climber.CALIBRATION_SPEED); // make constant
    // servo.setMyPosition(0);
  }

  @Override
  public void execute() {
    if (timer.get() > 0.1) {
      if (climber.getCurrentLeft() >= 27.5) {
        leftDone = true;
        climber.climbLeft(0);
      }
      if (climber.getCurrentRight() >= 27.5) {
        rightDone = true;
        climber.climbRight(0);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Climber Calibration Finished");
    timer.stop();
    climber.stop();
    climber.resetEncoders();
  }

  @Override
  public boolean isFinished() {
    return rightDone && leftDone;
  }

  /*
   * -- after all the self test(motor, rio tests, sensors)
   * bring the climber down at 0.1 speed
   * detect stall when climber is all the way down
   * reset encoders
   */
}
