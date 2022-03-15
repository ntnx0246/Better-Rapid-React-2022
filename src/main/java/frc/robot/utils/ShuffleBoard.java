package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.Auto.OneBallAuto;
import frc.robot.commands.Auto.TwoBallAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;

public class ShuffleBoard {
  private Intake intake;
  private Shooter shooter;
  private DriveTrain driveTrain;
  private NavX navX;

  private final OneBallAuto oneBallAuto_command = new OneBallAuto(intake, shooter, driveTrain);
  private final TwoBallAuto twoBallAuto_command = new TwoBallAuto(intake, shooter, driveTrain, navX);
  public final SendableChooser<String> autoChoose = new SendableChooser<String>();
  public final SendableChooser<String> shooterSpeedChoose = new SendableChooser<String>();

  public ShuffleBoard(
      Intake intake,
      Shooter shooter,
      DriveTrain driveTrain,
      NavX navX) {
    // shuffleboard stuff we want:
    // auto
    // shooter rpm
    // camera stuff (this will prolly be in vision file or not maybe thats why our
    // cam keeps dying)

    // AUTO
    autoChoose.setDefaultOption(Constants.ShuffleBoard.OneBall, Constants.ShuffleBoard.OneBall);
    autoChoose.addOption(Constants.ShuffleBoard.TwoBall, Constants.ShuffleBoard.TwoBall);
    SmartDashboard.putData("AUTO MODE", autoChoose);

    // RPM
    shooterSpeedChoose.setDefaultOption(Constants.ShuffleBoard.HIGH_RPM, Constants.ShuffleBoard.HIGH_RPM);
    shooterSpeedChoose.addOption(Constants.ShuffleBoard.LOW_RPM, Constants.ShuffleBoard.LOW_RPM);
    SmartDashboard.putData("SHOOTER SPEED", shooterSpeedChoose);
  }

  public Command getAutonomousCommand() {
    return (autoChoose.getSelected().equals(Constants.ShuffleBoard.OneBall)) ? oneBallAuto_command
        : twoBallAuto_command;
  }

  

}
