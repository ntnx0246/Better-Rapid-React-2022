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
  // private Intake intake;
  // private DriveTrain driveTrain;
  // private NavX navX;
  private final SendableChooser<Constants.ShuffleBoard.Auto> autoChoose = new SendableChooser<Constants.ShuffleBoard.Auto>();
  private OneBallAuto oneBallAuto_command;
  private TwoBallAuto twoBallAuto_command;

  private final SendableChooser<Integer> shooterVelocityChoose = new SendableChooser<Integer>();

  private final SendableChooser<Integer> shooterRollerVelocityChoose = new SendableChooser<Integer>();

  private int shooterRPM = Constants.Shooter.FENDER_HIGH_VELOCITY;
  private int rollerRpm = Constants.Shooter.BACKROLLER_HIGH_VELOCITY;

  public ShuffleBoard(
      Intake intake,
      Shooter shooter,
      DriveTrain driveTrain,
      NavX navX) {
    oneBallAuto_command = new OneBallAuto(intake, shooter, driveTrain);
    twoBallAuto_command = new TwoBallAuto(intake, shooter, driveTrain, navX);
    // AUTO
    autoChoose.setDefaultOption("One Ball", Constants.ShuffleBoard.Auto.OneBall);
    autoChoose.addOption("Two Ball", Constants.ShuffleBoard.Auto.TwoBall);
    SmartDashboard.putData("AUTO MODE", autoChoose);

    // RPM
    shooterVelocityChoose.setDefaultOption("High Shooting", Constants.Shooter.FENDER_HIGH_VELOCITY);
    shooterVelocityChoose.addOption("Low Shooting", Constants.Shooter.FENDER_LOW_VELOCITY);
    SmartDashboard.putData("SHOOTER SPEED", shooterVelocityChoose);

    // BACK ROLLER
    shooterRollerVelocityChoose.setDefaultOption("High Rolling", Constants.Shooter.BACKROLLER_HIGH_VELOCITY);
    shooterRollerVelocityChoose.addOption("Low Rolling", Constants.Shooter.BACKROLLER_LOW_VELOCITY);
    shooterRollerVelocityChoose.addOption("OFF", 0);

    SmartDashboard.putData("BACK ROLLER SPEED", shooterRollerVelocityChoose);

    SmartDashboard.putNumber("Shooter RPM", shooterRPM);
    SmartDashboard.putNumber("Back RPM", rollerRpm);

    // TODO auto position 1,2,3
  }

  public Command getAutonomousCommand() {
    switch (autoChoose.getSelected()) {
      case OneBall:
        return oneBallAuto_command;
      case TwoBall:
        return twoBallAuto_command;
      // case ThreeBall:
      // break;
      // case FourBall:
      // break;
      default:
        return oneBallAuto_command;

    }
  }

  public int getShooterVelocity() {
    return shooterVelocityChoose.getSelected().intValue();
  }

  public int getRollerVelocity() {
    return shooterRollerVelocityChoose.getSelected().intValue();
  }

  public double getMoveBack() {
    return shooterVelocityChoose.getSelected().intValue() == Constants.Shooter.FENDER_HIGH_VELOCITY
        ? Constants.Shooter.BACK_UP_TO_SHOOT
        : 0;
  }

  public int getShoot() {
    return (int) SmartDashboard.getNumber("Shooter RPM", 0);

  }

  public int getRoller() {
    return (int) SmartDashboard.getNumber("Back RPM", 0);
  }
}
