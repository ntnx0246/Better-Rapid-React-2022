package frc.robot.utils;

public final class Constants {
  public final static class ID {
    public static final int INTAKE_RAISING_SPARK = 1;
    public static final int INTAKE_TOP_SPARK = 2;
    public static final int INTAKE_BOTTOM_SPARK = 3;
    public static final int SHOOTER_RIGHT = 4;
    public static final int SHOOTER_LEFT = 5;
    public static final int DRIVETRAIN_FRONT_RIGHT = 6;
    public static final int DRIVETRAIN_BACK_RIGHT = 7;
    public static final int DRIVETRAIN_BACK_LEFT = 8;
    public static final int DRIVETRAIN_FRONT_LEFT = 9;
    public static final int CLIMBER_LEFT = 10;
    public static final int CLIMBER_RIGHT = 11;
    public static final int PIVOT_LEFT = 12;
    public static final int PIVOT_RIGHT = 13;
    public static final int SHOOTER_BACKLEFT = 14;
    public static final int SHOOTER_BACKRIGHT = 15;
    public static final int INTAKE_OUTSIDE_SPARK = 16;
  }

  public final static class Intake {
    public static final double TOP_SPEED = 0.6;
    public static final double BOTTOM_SPEED = 0.25;
    public static final double OUTSIDE_SPEED = -0.8;
    public static final double INTAKE_OUT_ENCODER = 94;
    public static final double CALIBRATION_SPEED = 0.1;
    public static final PID PID = new PID(0.6, 0, 0, 0);
  }

  public final static class Shooter {
    public static final double PUSH_SPEED = 1;
    public static final boolean DEBUG_MODE = false;
    public static final Shots FENDER = new Shots(-2550, 1000, -24);
    public static final Shots LAUNCHPAD = new Shots(-5000, 5000, 0);
    public static final double RPM_TOLERANCE = 30;

    public static final PID PID = new PID(0.0002, 0.000000005, 0, 0.000174);
    public static final PID PID_BACK = new PID(0.0002, 0.00000005, 0, 0.000174);
  }

  public final static class DriveTrain {
    public static final double SLOW_MODE_TURN = 0.3;
    public static final double SLOW_MODE = 0.3;
    public static final double SUPER_SLOW_MODE = 0.1;
    public static final double REGULAR_MODE = 1.0;
    public static final double REGULAR_MODE_TURN = 0.3;
    public static final double MOTOR_TO_WHEEL_REVOLUTION = 10.71;
    public static final int SENSOR_UNITS_PER_ROTATION = 2048;
    public static final int DRIVE_WHEEL_DIAMETER_INCHES = 6;
    public static final PID PID = new PID(0.15, 0, 0, 0, 0);
    public static final int CRUISE_VELOCITY = 7000; // max is around 21500
    public static final int ACCELERATION = 3500;
    public static final int ERROR_THRESHOLD = 500;
    public static final double OPEN_LOOP_RAMP = .5;
    public static final double CLOSED_LOOP_RAMP = .5;
    public static final double ANGLE_TOLERANCE = 5;
  }

  public final static class Climber {
    public static final double UP_SPEED = 0.5; // 0.3
    public static final double DOWN_SPEED = -0.3; // -0.3
    public static final double CALIBRATION_SPEED = -0.1; // -0.1
    public static final double UP_ENCODER_RIGHT = 238000;
    public static final double UP_ENCODER_LEFT = 238000;
    public static final double CLIMB_TOLERANCE = 50;

    public static final PID DOWN = new PID(0.008, 0.000005, 0.5, 0, 0);
    public static final PID UP = new PID(0.008, 0, 0, 0, 1);
    public static final PID TURBO = new PID(0.2, 0, 0, 0, 2);
  }

  public final static class Pivot {
    public static final PID FAST_PID = new PID(0.8, 0, 0, 0, 0);
    public static final PID SLOW_PID = new PID(0.05, 0, 0, 0, 1);
    public static final PID TURBO_PID = new PID(1, 1, 0, 1, 2);

  }

  public final static class Auto {
    public final class OneBall {
      public static final double CROSS_LINE = -180;
      public static final int SHOOT_VELOCITY = -3000;
    }

    public final static class TwoBall {
      public static final double GET_SECONDBALL = 90;
      public static final double SPACE_TO_TURN = -90;
      public static final double MOVE_TOWARDS_SHOOT = 10;
      public static final double CROSS_LINE = -180;
      public static final double TURN_ANGLE = 180;
      public static final int SHOOT_VELOCITY = -3200;
    }
  }

  public final static class ShuffleBoard {
    public enum Auto {
      OneBall, TwoBall, ThreeBall, FourBall
    }

    public enum Subsytems {
      Climber, DriveTrain, Intake, NavX, Pivots, Shooter, Vision
    }
  }

}
