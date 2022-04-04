package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class Shooter extends SubsystemBase {
  private CANSparkMax left;
  private CANSparkMax right;
  private SparkMaxPIDController leftPIDController;
  private SparkMaxPIDController rightPIDController;
  private RelativeEncoder leftEncoder;
  private RelativeEncoder rightEncoder;

  public Shooter() {
    left = new CANSparkMax(Constants.ID.SHOOTER_LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ID.SHOOTER_RIGHT, MotorType.kBrushless);

    leftPIDController = left.getPIDController();
    rightPIDController = right.getPIDController();

    right.setInverted(true);

    leftEncoder = left.getEncoder();
    rightEncoder = right.getEncoder();

    leftPIDController.setP(Constants.Shooter.P);
    leftPIDController.setI(Constants.Shooter.I);
    leftPIDController.setD(Constants.Shooter.D);
    leftPIDController.setFF(Constants.Shooter.F);

    rightPIDController.setP(Constants.Shooter.P);
    rightPIDController.setI(Constants.Shooter.I);
    rightPIDController.setD(Constants.Shooter.D);
    rightPIDController.setFF(Constants.Shooter.F);
  }

  public void setSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void setVelocity(int velocity) {
    leftPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    rightPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
  }

  public double getLeftVelocity() {
    return leftEncoder.getVelocity();
  }

  public double getRightVelocity() {
    return rightEncoder.getVelocity();
  }

  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
