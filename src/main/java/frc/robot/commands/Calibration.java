// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
// import frc.robot.subsystems.LinearServo2;

public class Calibration extends CommandBase {
  /** Creates a new Calibration. */
  Climber climber;
  boolean leftDone;
  boolean rightDone;
  Timer timer;
  // LinearServo2 servo;

  public Calibration(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
    leftDone = false;
    rightDone = false;
    timer = new Timer();
    // this.servo = servo;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    System.out.println("*********climber climb with speed******");
    climber.climb(Constants.Climber.CALIBRATION_SPEED); // make constant
    // servo.setMyPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("executing stuff");
    if (timer.get() > 0.1) {
      if (climber.getCurrentLeft() >= 27.5) {
        leftDone = true;
        System.out.println("left is done *****");
        climber.climbLeft(0);
      }
      if (climber.getCurrentRight() >= 27.5) {
        rightDone = true;
        System.out.println("right is done *****");
        climber.climbRight(0);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("climber calibration stop");
    timer.stop();
    climber.stop();
    climber.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("Calibration isFinished: " + (rightDone && leftDone));
    // return rightDone && leftDone;
    return false;
  }

  /*
   * -- after all the self test(motor, rio tests, sensors)
   * bring the climber down at 0.1 speed
   * detect stall when climber is all the way down
   * reset encoders
   */
}
