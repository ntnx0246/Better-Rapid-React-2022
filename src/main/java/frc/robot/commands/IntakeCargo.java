// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeCargo extends CommandBase {
  /** Creates a new IntakeCargo. */
  private final Intake intake; 
  public boolean isIntaking;
  public Timer timer;


  public IntakeCargo(Intake intake,boolean isIntaking) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.intake = intake;
    this.isIntaking = isIntaking;
    this.timer = new Timer(); 
    // timer.delay(1.0);
  }

  // Called when the command is initially scheduled.
  @Override
  
  public void initialize() {
    timer.reset();

    if (isIntaking == true) {
      intake.intakeTopMotor(Constants.INTAKE_TOP_SPEED);
      intake.intakeBottomMotor(Constants.INTAKE_BOTTOM_SPEED);
    } else {
      timer.start();
    }

  }

  // Called every time the scheduler runs while the command is scheduled
  @Override
  public void execute() {
    if (isIntaking == false && timer.get() > 1) {
      intake.intakeTopMotor(Constants.INTAKE_SPEED*-1);
      intake.intakeBottomMotor(Constants.INTAKE_SPEED);
      System.out.println("intake execute if");
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}