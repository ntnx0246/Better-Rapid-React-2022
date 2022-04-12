package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.Auto.OneBallAuto;
import frc.robot.commands.Auto.FourBallAuto;
import frc.robot.commands.Auto.TwoBallAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;

public class ShuffleBoard {
  private final SendableChooser<Constants.ShuffleBoard.Auto> autoChoose = new SendableChooser<Constants.ShuffleBoard.Auto>();

  private final SendableChooser<Shots> shooterLocationChooser = new SendableChooser<Shots>();

  private int shooterRPM = Constants.Shooter.FENDER.getFrontRPM();
  private int rollerRpm = Constants.Shooter.FENDER.getBackRPM();

  private Intake intake;
  private Shooter shooter;
  private DriveTrain driveTrain;
  private NavX navX;

  public ShuffleBoard(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
    this.intake = intake;
    this.shooter = shooter;
    this.driveTrain = driveTrain;
    this.navX = navX;

    // AUTO
    autoChoose.setDefaultOption("One Ball", Constants.ShuffleBoard.Auto.OneBall);
    autoChoose.addOption("Two Ball 1", Constants.ShuffleBoard.Auto.TwoBall_1);
    autoChoose.addOption("Two Ball 2", Constants.ShuffleBoard.Auto.TwoBall_2);
    autoChoose.addOption("Two Ball 3", Constants.ShuffleBoard.Auto.TwoBall_3);
    autoChoose.addOption("Four Ball 2", Constants.ShuffleBoard.Auto.FourBall);

    SmartDashboard.putData("AUTO MODE", autoChoose);

    // RPM
    shooterLocationChooser.setDefaultOption("Fender Shooting", Constants.Shooter.FENDER);
    shooterLocationChooser.addOption("Low Fender Shooting", Constants.Shooter.FENDER_LOW);
    shooterLocationChooser.addOption("Launchpad Shooting", Constants.Shooter.LAUNCHPAD);

    SmartDashboard.putData("Shooter Location", shooterLocationChooser);

    if (Constants.Shooter.DEBUG_MODE) {
      SmartDashboard.putNumber("Shooter Input RPM", shooterRPM);
      SmartDashboard.putNumber("Back Input RPM", rollerRpm);
    }

  }

  public Command getAutonomousCommand() {
    switch (autoChoose.getSelected()) {
      case OneBall:
        return new OneBallAuto(intake, shooter, driveTrain);
      case TwoBall_1:
        return new TwoBallAuto(intake, shooter, 160);
      case TwoBall_2:
        return new TwoBallAuto(intake, shooter, -170);
      case TwoBall_3:
        return new TwoBallAuto(intake, shooter, 180);
      case FourBall:
        return new FourBallAuto(intake, shooter, driveTrain, navX);
      default:
        return new OneBallAuto(intake, shooter, driveTrain);

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
    return (int) SmartDashboard.getNumber("Shooter Input RPM", 0);
  }

  public int getRoller() {
    return (int) SmartDashboard.getNumber("Back Input RPM", 0);
  }

  public boolean getDebugMode() {
    return SmartDashboard.getBoolean("Debug Mode", false);
  }
}
