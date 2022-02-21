// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static final int INTAKE_TOP_SPARK = 8;
  public static final int INTAKE_BOTTOM_SPARK = 9;
  public static final double INTAKE_SPEED = 0.4;
  public static final double INTAKE_TOP_SPEED = 0.6;
  public static final double INTAKE_BOTTOM_SPEED = 0.25;

  public static final int SHOOTER_LEFT_ID = 6;
  public static final int SHOOTER_RIGHT_ID = 7;
  public static final double SHOOTER_SPEED = -0.6;
  public static final double SHOOTER_VELOCITY = -3500;
  public static final double SHOOTER_RPM_TOLERANCE = 25;

  public static final double SHOOTER_P = 0.0001;
  public static final double SHOOTER_I = 0;
  public static final double SHOOTER_D = 0;
  public static final double SHOOTER_F = 0.000174;

  public static final double MOTOR_TO_WHEEL_REVOLUTION = 10.71;
  public static final int SENSOR_UNITS_PER_ROTATION = 2048;
  public static final int DRIVE_WHEEL_DIAMETER_INCHES = 6;

  public static final int CLIMBER_LEFT_ID = 4;
  public static final int CLIMBER_RIGHT_ID = 5;

  public static final int FRONT_LEFT_ID = 3;
  public static final int FRONT_RIGHT_ID = 1;
  public static final int BACK_LEFT_ID = 2;
  public static final int BACK_RIGHT_ID = 0;

  public static final double CLIMBER_UP_SPEED = 0.3;
  public static final double CLIMBER_DOWN_SPEED = -0.3;

  public static final double SLOW_MODE = 0.1;
  public static final double REGULAR_MODE = 0.4;

  //drivetrain PID
  public static final int SLOT_ID = 0;
  public static final double kP = 0.15;
  public static final double kI = 0;
  public static final double kD = 0;

  public static final int CRUISE_VELOCITY = 7000; //max is around 21500
  public static final int ACCELERATION = 3500;
  public static final int ERROR_THRESHOLD = 500;

  public static final double ERROR_ANGLE_TOLERANCE = 5;
  public static final double AUTO_CLIMB_TOLERANCE = 50;

  public static final double CALIBRATION_SPEED = -0.1;

  public static final double CLIMBER_P = 0;
  public static final double CLIMBER_I = 0;
  public static final double CLIMBER_D = 0;
  public static final double CLIMBER_F = 0;
  public static final int CLIMBER_SLOT_ID = 0;

  // might need to add a driveStraightP vs turnAngleP
  // might also need leftPID vs rightPID bc of motor controllers

}
