package frc.robot;

// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command autonomousCommand;
  private RobotContainer robotContainer;
  // private NetworkTable sd;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    // sd = NetworkTableInstance.getDefault().getTable("nano");
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
    autonomousCommand = robotContainer.getAutonomousCommand();
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    robotContainer.configureButtonBindings();

  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().clearButtons();
    CommandScheduler.getInstance().enable();
    CommandScheduler.getInstance().schedule(robotContainer.calibrateClimber());
    // CommandScheduler.getInstance().schedule(robotContainer.calibrateServo());
    // robotContainer.getTestCommand().schedule();
    // robotContainer.calibrateClimber().execute();
    // robotContainer.calibrateServo().execute();
  }

  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
