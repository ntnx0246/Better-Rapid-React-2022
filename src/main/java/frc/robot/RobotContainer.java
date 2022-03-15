package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.BackUpAndShoot;
import frc.robot.commands.Calibration;
import frc.robot.commands.CargoManipulation;
import frc.robot.commands.ChangeDriveMode;
import frc.robot.commands.PistonMove;
import frc.robot.commands.Climber.ClimbDown;
import frc.robot.commands.Climber.ClimbUp;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LinearServo;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;
import frc.robot.utils.LogitechGamingPad;
import frc.robot.utils.ShuffleBoard;

public class RobotContainer {
  private final LogitechGamingPad drivePad = new LogitechGamingPad(0);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber();
  private final LinearServo servo = new LinearServo();
  private final Intake intake = new Intake();
  private final NavX navX = new NavX();

  private final JoystickButton driveA = new JoystickButton(drivePad, 1);
  private final JoystickButton driveB = new JoystickButton(drivePad, 2);
  private final JoystickButton driveX = new JoystickButton(drivePad, 3);
  private final JoystickButton driveY = new JoystickButton(drivePad, 4);
  private final JoystickButton leftBumper = new JoystickButton(drivePad, 5);
  private final JoystickButton rightBumper = new JoystickButton(drivePad, 6);
  // private final JoystickButton driveBackButton = new JoystickButton(drivePad,
  // 7);
  // private final JoystickButton driveStartButton = new JoystickButton(drivePad,
  // 8);

  private final ShuffleBoard shuffleBoard = new ShuffleBoard(
      intake, shooter, driveTrain, navX);

  public RobotContainer() {
    driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, drivePad));
  }

  public void configureButtonBindings() {
    rightBumper.whileHeld(new CargoManipulation(intake, shooter, true, false, -1));
    // TODO look once vision done
    // leftBumper.whileHeld(new CargoManipulation(intake, shooter, false, false));
    leftBumper.whileHeld(new BackUpAndShoot(driveTrain, intake, shooter));
    driveY.whileHeld(new ClimbUp(climber));
    driveX.whileHeld(new PistonMove(servo));
    driveA.whileHeld(new ClimbDown(climber));
    driveB.whenPressed(new ChangeDriveMode(driveTrain));
  }

  public Command getAutonomousCommand() {
    return shuffleBoard.getAutonomousCommand();
  }

  public Command calibrateClimber() {
    return new Calibration(climber);
  }

  public Command calibrateServo() {
    return new PistonMove(servo);
  }

  public void setShooterRPM(){
    shuffleBoard.setShooterRPM();
  }
}
