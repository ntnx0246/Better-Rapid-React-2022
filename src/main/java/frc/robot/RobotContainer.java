// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.PS4ControllerSim;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  LogitechGamingPad drivePad;

  DriveTrain driveTrain;
  Shooter shooter;

  ArcadeDrive arcadeDrive;

  JoystickButton driveA;
  JoystickButton driveB;
  JoystickButton driveLeftBumper; 
  JoystickButton driveRightBumper; 
  
  Test test;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    
    drivePad = new LogitechGamingPad(0);

    driveTrain = new DriveTrain();
    shooter = new Shooter();

    arcadeDrive = new ArcadeDrive(driveTrain, drivePad);
    test = new Test(driveTrain);

    driveTrain.setDefaultCommand(arcadeDrive);
    configureButtonBindings();

    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driveA = new JoystickButton(drivePad, 1);
    driveA.whenPressed(new Test(driveTrain));

    driveB = new JoystickButton(drivePad, 2);
    driveB.whileHeld(new Shoot(shooter));

    driveLeftBumper = new JoystickButton(drivePad, 5);
    driveLeftBumper.whenPressed(new ArcadeDrive(driveTrain, drivePad));

    driveRightBumper = new JoystickButton(drivePad, 6);
    driveRightBumper.whenPressed(new ArcadeDriveSlowMode(driveTrain, drivePad));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
