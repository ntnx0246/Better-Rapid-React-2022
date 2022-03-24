package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Climber;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.subsystems.LinearServo2;
import frc.robot.subsystems.Pivots;

public class Calibration extends CommandBase {
  Climber climber;
  Pivots pivots;
  boolean leftDone;
  boolean rightDone;
  boolean leftPivotDone;
  boolean rightPivotDone;
  Timer timer;
  // LinearServo2 servo;

  public Calibration(Climber climber, Pivots pivots) {
    addRequirements(climber);
    this.climber = climber;
    this.pivots = pivots;
    leftDone = false;
    rightDone = false;
    leftPivotDone = false;
    rightPivotDone = false;
    timer = new Timer();
    // this.servo = servo;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    climber.climb(Constants.Climber.CALIBRATION_SPEED);
    pivots.climbPivots(-Constants.Climber.CALIBRATION_SPEED);
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
      if (pivots.getCurrentPivotLeft() >= 20) {
        leftPivotDone = true;
        pivots.climbPivotLeft(0);
      }
      if (pivots.getCurrentPivotRight() >= 20) {
        rightPivotDone = true;
        pivots.climbPivotRight(0);
      }
    }
    // SmartDashboard.putNumber("Bus Voltage", climber.rightPivot.getBusVoltage());
    // SmartDashboard.putNumber("Output Current", climber.rightPivot.getOutputCurrent());
    // SmartDashboard.putNumber("Stick Faults", climber.rightPivot.getStickyFaults());
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("Climber Calibration Finished");
    timer.stop();
    climber.stop();
    climber.resetEncoders();
    pivots.resetEncoders();
  }

  @Override
  public boolean isFinished() {
    return rightDone && leftDone && leftPivotDone && rightPivotDone;
  }

  /*
   * -- after all the self test(motor, rio tests, sensors)
   * bring the climber down at 0.1 speed
   * detect stall when climber is all the way down
   * reset encoders
   */
}
