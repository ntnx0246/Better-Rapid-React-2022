package frc.robot.utils;

import java.util.EnumMap;
import java.util.stream.IntStream;

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
  private final SendableChooser<Constants.ShuffleBoard.Auto> autoChoose = new SendableChooser<Constants.ShuffleBoard.Auto>();
  private OneBallAuto oneBallAuto_command;
  private TwoBallAuto twoBallAuto_command;

  private final SendableChooser<Shots> shooterLocationChooser = new SendableChooser<Shots>();

  private int shooterRPM = Constants.Shooter.FENDER.getFrontRPM();
  private int rollerRpm = Constants.Shooter.FENDER.getBackRPM();

  private final EnumMap<Constants.ShuffleBoard.Subsystems, Boolean> debugMode = new EnumMap<>(
      Constants.ShuffleBoard.Subsystems.class);

  private void initEnumMap() {
    // Climber, dt, Intake, NavX, pivots, shooter, vision
    for (Constants.ShuffleBoard.Subsystems sub : Constants.ShuffleBoard.Subsystems.values()) {
      debugMode.put(sub, false);
    }
  }

  public ShuffleBoard(Intake intake, Shooter shooter, DriveTrain driveTrain, NavX navX) {
    initEnumMap();

    oneBallAuto_command = new OneBallAuto(intake, shooter, driveTrain);
    twoBallAuto_command = new TwoBallAuto(intake, shooter, driveTrain, navX);

    // AUTO
    autoChoose.setDefaultOption("One Ball", Constants.ShuffleBoard.Auto.OneBall);
    autoChoose.addOption("Two Ball", Constants.ShuffleBoard.Auto.TwoBall);
    SmartDashboard.putData("AUTO MODE", autoChoose);

    // TODO auto position 1,2,3

    // RPM
    shooterLocationChooser.setDefaultOption("Fender Shooting", Constants.Shooter.FENDER);
    shooterLocationChooser.addOption("LaunchPad Shooting", Constants.Shooter.LAUNCHPAD);

    SmartDashboard.putData("Shooter Location", shooterLocationChooser);

    // TODO manual RPM input
    // if (debugMode.get(Constants.ShuffleBoard.Subsystems.Shooter)) {
    //   SmartDashboard.putNumber("Shooter Input RPM", shooterRPM);
    //   SmartDashboard.putNumber("Back Input RPM", rollerRpm);
    // }

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
    return (int) SmartDashboard.getNumber("Shooter Input RPM", 0);
  }

  public int getRoller() {
    return (int) SmartDashboard.getNumber("Back Input RPM", 0);
  }

  public boolean getDebugMode() {
    return SmartDashboard.getBoolean("Debug Mode", false);
  }
}
