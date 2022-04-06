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

    leftPIDController.setP(Constants.Shooter.PID.p);
    leftPIDController.setI(Constants.Shooter.PID.i);
    leftPIDController.setD(Constants.Shooter.PID.d);
    leftPIDController.setFF(Constants.Shooter.PID.f);

    rightPIDController.setP(Constants.Shooter.PID.p);
    rightPIDController.setI(Constants.Shooter.PID.i);
    rightPIDController.setD(Constants.Shooter.PID.d);
    rightPIDController.setFF(Constants.Shooter.PID.f);
   

    backLeftPIDController.setP(Constants.Shooter.PID_BACK.p);
    backLeftPIDController.setI(Constants.Shooter.PID_BACK.i);
    backLeftPIDController.setD(Constants.Shooter.PID_BACK.d);
    backLeftPIDController.setFF(Constants.Shooter.PID_BACK.f);

    backRightPIDController.setP(Constants.Shooter.PID_BACK.p);
    backRightPIDController.setI(Constants.Shooter.PID_BACK.i);
    backRightPIDController.setD(Constants.Shooter.PID_BACK.d);
    backRightPIDController.setFF(Constants.Shooter.PID_BACK.f);
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

  public double getRollerLeftVelocity() {
    return backLeftEncoder.getVelocity();
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
    backLeft.stopMotor();
    backRight.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
