package frc.robot.utils;

import edu.wpi.first.cameraserver.CameraServer;
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

  private final SendableChooser<Constants.Shooter.Shots> shooterLocationChooser = new SendableChooser<Constants.Shooter.Shots>();

  private int shooterRPM = Constants.Shooter.Shots.FENDER.getFrontRPM();
  private int rollerRpm = Constants.Shooter.Shots.FENDER.getBackRPM();

  public ShuffleBoard(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
    // CameraServer.startAutomaticCapture(0);

    oneBallAuto_command = new OneBallAuto(intake, shooter, driveTrain);
    twoBallAuto_command = new TwoBallAuto(intake, shooter, driveTrain, navX);
    // AUTO
    autoChoose.setDefaultOption("One Ball", Constants.ShuffleBoard.Auto.OneBall);
    autoChoose.addOption("Two Ball", Constants.ShuffleBoard.Auto.TwoBall);
    SmartDashboard.putData("AUTO MODE", autoChoose);

    // RPM
    shooterLocationChooser.setDefaultOption("Fender Shooting", Constants.Shooter.Shots.FENDER);
    shooterLocationChooser.addOption("LaunchPad Shooting", Constants.Shooter.Shots.LAUNCHPAD);

    SmartDashboard.putData("Shooter Location", shooterLocationChooser);

    // manual RPM input
    if (Constants.Shooter.DEBUG_MODE) {
      SmartDashboard.putNumber("Shooter RPM", shooterRPM);
      SmartDashboard.putNumber("Back RPM", rollerRpm);
    }
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
    return shooterLocationChooser.getSelected().getFrontRPM();
  }

  public int getRollerVelocity() {
    return shooterLocationChooser.getSelected().getBackRPM();
  }

  public double getMoveBack() {
    return shooterLocationChooser.getSelected().getDistance();
  }

  public int getShoot() {
    return (int) SmartDashboard.getNumber("Shooter RPM", 0);
  }

  public int getRoller() {
    return (int) SmartDashboard.getNumber("Back RPM", 0);
  }
}
