// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.CargoManipulation;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Calibration;
import frc.robot.commands.ChangeDriveMode;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.PistonMove;
//import frc.robot.commands.IntakeCargo;
import frc.robot.commands.OneBallAuto;
import frc.robot.commands.TwoBallAuto;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.LinearServo2;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.LinearServo;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final LogitechGamingPad drivePad = new LogitechGamingPad(0);
  // private final LogitechGamingPad opPad = new LogitechGamingPad(1);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Shooter shooter = new Shooter();
  private final Climber climber = new Climber();
  private final LinearServo servo = new LinearServo();
  private final Intake intake = new Intake();

  private final NavX navX = new NavX();
  // private final LinearServo2 servo2 = new LinearServo2(0);



  // private final Intake intake = new Intake();
  // private final JoystickButton buttonX = new JoystickButton(drivePad, 3);
  // private final JoystickButton rightBumper = new JoystickButton(drivePad, 10);
  // private final JoystickButton startButton = new JoystickButton(drivePad, 8);
  // private final JoystickButton opY = new JoystickButton(opPad, 4);
  // private final JoystickButton opA = new JoystickButton(opPad, 1);

  // private final LogitechGamingPad drivePad = new LogitechGamingPad(0);
  // private final JoystickButton leftBumper = new JoystickButton(drivePad, 9);
  // private final JoystickButton rightBumper = new JoystickButton(drivePad, 10);

  private final JoystickButton driveA = new JoystickButton(drivePad, 1);
  private final JoystickButton driveB = new JoystickButton(drivePad, 2);
  private final JoystickButton driveX = new JoystickButton(drivePad, 3);
  private final JoystickButton driveY = new JoystickButton(drivePad, 4);
  private final JoystickButton leftBumper = new JoystickButton(drivePad, 5);
  private final JoystickButton rightBumper = new JoystickButton(drivePad, 6);
  private final JoystickButton driveBackButton = new JoystickButton(drivePad, 7);
  private final JoystickButton driveStartButton = new JoystickButton(drivePad, 8);

  public SendableChooser<String> chooser;
  private final OneBallAuto oneBallAuto_command = new OneBallAuto(intake, shooter, driveTrain);
  private final TwoBallAuto twoBallAuto_command = new TwoBallAuto(intake, shooter, driveTrain, navX);
  private final String OneBall = "OneBall";
  private final String TwoBall = "TwoBall";

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    driveTrain.setDefaultCommand(new ArcadeDrive(driveTrain, drivePad));
    configureButtonBindings();

    chooser = new SendableChooser<String>();
    chooser.setDefaultOption("TwoBall", TwoBall);
    chooser.addOption("OneBall", OneBall);

    SmartDashboard.putData("AUTO CHOOSER", chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    rightBumper.whileHeld(new CargoManipulation(intake, shooter, true, false));
    leftBumper.whileHeld(new CargoManipulation(intake, shooter, false, false));

    driveY.whileHeld(new ClimbUp(climber));
    driveX.whileHeld(new PistonMove(servo));
    // driveA.whenPressed(new Calibration(climber));
    driveA.whileHeld(new ClimbDown(climber));
    driveB.whenPressed(new ChangeDriveMode(driveTrain));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    if(chooser.getSelected().equals(TwoBall)) {
      return twoBallAuto_command;
    } else if(chooser.getSelected().equals(OneBall)) {
      return oneBallAuto_command;
    } else {
      return new DriveStraight(driveTrain, 50);
    }
  }

  public Command getTestCommand() {
    return new Calibration(climber);
  }
  public Climber getClimber(){
    return climber;
  }
  public Command getTest2Command(){
    return new PistonMove(servo);
  }

}
