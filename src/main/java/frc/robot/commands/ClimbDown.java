// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimbDown extends CommandBase {
  /** Creates a new ClimbDown. */

  private final Climber climber;
  private int goal;
  private double climberEncoder;


  public ClimbDown(Climber climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climber);
    this.climber = climber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.climb(Constants.Climber.DOWN_SPEED);
    System.out.println("finished climb down initalize");

    climberEncoder = Math.abs(climber.getLeftEncoderCount());
    if (climberEncoder > 50000) {

      goal = 21000; // 25000
      climber.selectProfile(0);
    } else {
      goal = 1000; //1000
      // climber.selectProfile(1);
      climber.climb(-0.4);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.


  @Override
  public void execute() {
    // if (climber.getRightEncoderCount() < goal) {
    // climber.stop();
    // }
    // else {
    // climber.printEncoders();
    // }
    if(goal != 1000){
      climberEncoder = Math.abs(climber.getLeftEncoderCount());
      if (climberEncoder < 100000) { // 25000
        // if (goal == 1000) {
        // climber.selectProfile(1);
        // } else {
        // climber.selectProfile(0);
        // }
        climber.setPositionLeft(goal * 0.9762);
        climber.setPositionRight(goal);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.printEncoders();
    climber.stop();

    // try {
    // Thread.sleep(5000);
    // } catch (InterruptedException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // climber.printEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
