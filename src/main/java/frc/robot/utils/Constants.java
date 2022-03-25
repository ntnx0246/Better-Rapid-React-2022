package frc.robot.utils;

public final class Constants {
  public final static class ID {
    public static final int INTAKE_OUTSIDE_SPARK = 1;
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
  }

  public final static class Intake {
    public static final double TOP_SPEED = 0.6;
    public static final double BOTTOM_SPEED = 0.25;
    public static final double OUTSIDE_SPEED = -0.5;
  }

  public final static class Shooter {
    public static final double PUSH_SPEED = 0.4;
    public static final int FENDER_HIGH_VELOCITY = -3200;
    public static final int FENDER_LOW_VELOCITY = -2000;
    public static final double RPM_TOLERANCE = 50;
    public static final double P = 0.0001;
    public static final double I = 0;
    public static final double D = 0;
    public static final double F = 0.000174;
    public static final double BACK_UP_TO_SHOOT = -12;
  }

  public final static class DriveTrain {
    public static final double SLOW_MODE = 0.5;
    public static final double REGULAR_MODE = 1.0;
    public static final double REGULAR_MODE_TURN = 0.3;
    public static final double MOTOR_TO_WHEEL_REVOLUTION = 10.71;
    public static final int SENSOR_UNITS_PER_ROTATION = 2048;
    public static final int DRIVE_WHEEL_DIAMETER_INCHES = 6;
    public static final int SLOT_ID = 0;
    public static final double P = 0.15;
    public static final double I = 0;
    public static final double D = 0;
    public static final int CRUISE_VELOCITY = 7000; // max is around 21500
    public static final int ACCELERATION = 3500;
    public static final int ERROR_THRESHOLD = 500;
    public static final double OPEN_LOOP_RAMP = .5;
    public static final double CLOSED_LOOP_RAMP = .5;
    public static final double ANGLE_TOLERANCE = 5;
  }

  public final static class Climber {
    public static final double UP_SPEED = 0.3; // 0.3
    public static final double DOWN_SPEED = -0.2; // -0.3
    public static final double CALIBRATION_SPEED = -0.1; // -0.1
    public static final double UP_ENCODER_RIGHT = 236000;
    public static final double UP_ENCODER_LEFT = 229000;

    public static final double CLIMB_TOLERANCE = 50;
    
    // going down part 1
    public static final double P_0 = 0.008;
    public static final double I_0 = 0.00001;
    public static final double D_0 = 0.5;
    public static final double F_0 = 0;
    public static final int SLOT_ID_0 = 0;
    
    // going up
    public static final double P_1 = 0.008;
    public static final double I_1 = 0;
    public static final double D_1 = 0;
    public static final double F_1 = 0;
    public static final int SLOT_ID_1 = 1;

    public static final double P_2 = 0.8;
    public static final double I_2 = 0;
    public static final double D_2 = 0;
    public static final double F_2 = 0;
  }

  public final static class Auto {
    public final class OneBall {
      public static final double CROSS_LINE = -180;
      public static final int SHOOT_VELOCITY = -3000;

    }

    public final static class TwoBall {
      public static final double GET_SECONDBALL = 90;
      public static final double SPACE_TO_TURN = -90;
      public static final double MOVE_TOWARDS_SHOOT = 30;
      public static final double CROSS_LINE = -180;
      public static final double TURN_ANGLE = 180;
      public static final int SHOOT_VELOCITY = -3000;
    }
  }

  public final static class ShuffleBoard {
    public enum Auto {
      OneBall, TwoBall, ThreeBall, FourBall
    }
  }
}
