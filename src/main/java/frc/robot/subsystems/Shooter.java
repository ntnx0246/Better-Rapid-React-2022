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


  private CANSparkMax backLeft;
  private CANSparkMax backRight;
  private SparkMaxPIDController backLeftPIDController;
  private SparkMaxPIDController backRightPIDController;
  private RelativeEncoder backLeftEncoder;
  private RelativeEncoder backRightEncoder;

  public Shooter() {
    left = new CANSparkMax(Constants.ID.SHOOTER_LEFT, MotorType.kBrushless);
    right = new CANSparkMax(Constants.ID.SHOOTER_RIGHT, MotorType.kBrushless);
    backLeft = new CANSparkMax(Constants.ID.SHOOTER_BACKLEFT, MotorType.kBrushless);
    backRight = new CANSparkMax(Constants.ID.SHOOTER_BACKRIGHT, MotorType.kBrushless);

    leftPIDController = left.getPIDController();
    rightPIDController = right.getPIDController();
    backLeftPIDController = backLeft.getPIDController();
    backRightPIDController = backRight.getPIDController();

    right.setInverted(true);
    backRight.setInverted(true);

    leftEncoder = left.getEncoder();
    rightEncoder = right.getEncoder();
    backLeftEncoder = backLeft.getEncoder();
    backRightEncoder = backRight.getEncoder();

    leftPIDController.setP(Constants.Shooter.P);
    leftPIDController.setI(Constants.Shooter.I);
    leftPIDController.setD(Constants.Shooter.D);
    leftPIDController.setFF(Constants.Shooter.F);

    rightPIDController.setP(Constants.Shooter.P);
    rightPIDController.setI(Constants.Shooter.I);
    rightPIDController.setD(Constants.Shooter.D);
    rightPIDController.setFF(Constants.Shooter.F);

    backLeftPIDController.setP(Constants.Shooter.P_BACK);
    backLeftPIDController.setI(Constants.Shooter.I_BACK);
    backLeftPIDController.setD(Constants.Shooter.D_BACK);
    backLeftPIDController.setFF(Constants.Shooter.F_BACK);

    backRightPIDController.setP(Constants.Shooter.P_BACK);
    backRightPIDController.setI(Constants.Shooter.I_BACK);
    backRightPIDController.setD(Constants.Shooter.D_BACK);
    backRightPIDController.setFF(Constants.Shooter.F_BACK);
  }

  public void setSpeed(double speed) {
    left.set(speed);
    right.set(speed);
  }

  public void setRollerSpeed(double speed){
    backLeft.set(speed);
    backRight.set(speed);
  }

  public void setVelocity(int velocity) {
    leftPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    rightPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
  }

  public void setRollerVelocity(int velocity) {
    backLeftPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
    backRightPIDController.setReference(velocity, CANSparkMax.ControlType.kVelocity);
  }

  public double getLeftVelocity() {
    return leftEncoder.getVelocity();
  }

  public double getRightVelocity() {
    return rightEncoder.getVelocity();
  }

  public double getBackLeftVelocity(){
    return backLeftEncoder.getVelocity();
  }

  public double getBackRightVelocity(){
    return backRightEncoder.getVelocity();
  }

  public void stop() {
    left.stopMotor();
    right.stopMotor();
  }

  public void stopRoller(){
    backLeft.stopMotor();
    backRight.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
