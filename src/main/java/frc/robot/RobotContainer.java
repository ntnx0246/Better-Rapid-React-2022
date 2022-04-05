package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Climber.AutoClimb;
import frc.robot.commands.Climber.ClimbDown;
import frc.robot.commands.Climber.ClimbUp;
// import frc.robot.commands.Climber.PivotRelative;
import frc.robot.commands.DriveTrain.ArcadeDrive;
import frc.robot.commands.Calibration;
import frc.robot.commands.IntakeBalls;
import frc.robot.commands.Shoot;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Pivots;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;
import frc.robot.utils.LogitechGamingPad;
import frc.robot.utils.ShuffleBoard;

public class RobotContainer {
  private final LogitechGamingPad drivePad = new LogitechGamingPad(0);
  private final LogitechGamingPad opPad = new LogitechGamingPad(1);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber();
  private final Intake intake = new Intake();
  private final NavX navX = new NavX();
  private final Pivots pivots = new Pivots();
  private final Vision vision = new Vision();

  private final JoystickButton driveA = new JoystickButton(drivePad, 1);
  private final JoystickButton driveB = new JoystickButton(drivePad, 2);
  private final JoystickButton driveX = new JoystickButton(drivePad, 3);
  private final JoystickButton driveY = new JoystickButton(drivePad, 4);
  private final JoystickButton leftBumper = new JoystickButton(drivePad, 5);
  private final JoystickButton rightBumper = new JoystickButton(drivePad, 6);
  // private final JoystickButton driveBackButton = new JoystickButton(drivePad,7);
  private final JoystickButton driveStartButton = new JoystickButton(drivePad, 8);

  private final JoystickButton opA = new JoystickButton(opPad, 1);
  private final JoystickButton opB = new JoystickButton(opPad, 2);
  private final JoystickButton opX = new JoystickButton(opPad, 3);
  private final JoystickButton opY = new JoystickButton(opPad, 4);
  // private final JoystickButton opLeftBumper = new JoystickButton(opPad, 5);
  private final JoystickButton opRightBumper = new JoystickButton(opPad, 6);
  // private final JoystickButton opBackButton = new JoystickButton(opPad, 7);
  // private final JoystickButton opStartButton = new JoystickButton(opPad, 8);

  private final ShuffleBoard shuffleBoard = new ShuffleBoard(
      intake, shooter, driveTrain, navX);
  
  public RobotContainer() {
    driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, drivePad));
  }

  public void configureButtonBindings() {
    // primary driver
    driveTrain.setSlowMode(false);
    rightBumper.whileHeld(new IntakeBalls(intake));
    
    leftBumper.whileHeld(new Shoot(intake, shooter, () -> shuffleBoard.getShooterVelocity(), () -> shuffleBoard.getRollerVelocity()));
    driveStartButton.whenPressed(new Calibration(climber, pivots, intake, driveTrain));
    // driveStartButton.whenPressed(new InstantCommand(()->configureButtonBindings()));

    driveX.whenPressed(new AutoClimb(climber, pivots));
    // driveX.whileHeld(new AutoClimb(climber, pivots));
    driveY.whileHeld(new ClimbUp(climber, 1));
    driveY.whenPressed(new InstantCommand(driveTrain::climbingTrue));
    driveA.whileHeld(new ClimbDown(climber));
    driveB.whenPressed(new InstantCommand(driveTrain::toggleSlowMode));

    // secondary driver
    opY.whileHeld(new ClimbUp(climber, 1)); // maybe add slow
    opA.whileHeld(new ClimbDown(climber));
    // opX.whileHeld(new PivotRelative(pivots, 10, 0));
    opX.whileHeld(new InstantCommand(() -> pivots.climbPivots(0.2)));
    // opB.whileHeld(new PivotRelative(pivots, -10, 0));
    opB.whileHeld(new InstantCommand(() -> pivots.climbPivots(-0.2)));
    opRightBumper.whileHeld(new IntakeBalls(intake));
  }

  public void configureClimberButtonBindings(){
    driveY.whileHeld(new ClimbUp(climber, 1));
    driveY.whenPressed(new InstantCommand(driveTrain::climbingTrue));
    driveA.whileHeld(new ClimbDown(climber));
  }

  public Command getAutonomousCommand() {
    return shuffleBoard.getAutonomousCommand();
  }

  public Command calibrate() {
    return new Calibration(climber, pivots, intake, driveTrain);
  }
}
