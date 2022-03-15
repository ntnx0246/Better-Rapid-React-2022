// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.LinearServo;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private static LinearServo servo;
  private RobotContainer m_robotContainer;
  private int servo_counter = 0;
  private Climber climber;

  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();
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

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.configureButtonBindings();

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // System.out.println(climber.getIntialized());
    // if(climber.getIntialized()){
    // servo.setPosition(0);
    // }
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().enable();

    CommandScheduler.getInstance().schedule(m_robotContainer.calibrateClimber());
    CommandScheduler.getInstance().schedule(m_robotContainer.calibrateServo());
    // m_robotContainer.getTestCommand().schedule();
    // m_robotContainer.calibrateClimber().execute();
    // m_robotContainer.calibrateServo().execute();
  }

  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();

  }
}
