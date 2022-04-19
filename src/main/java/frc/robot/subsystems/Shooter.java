package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.PID;

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

    this.setPIDFront(Constants.Shooter.PID);
    this.setPIDBack(Constants.Shooter.PID);
  }

  public void setPIDFront(PID pid){
    leftPIDController.setP(pid.p);
    leftPIDController.setI(pid.i);
    leftPIDController.setD(pid.d);
    leftPIDController.setFF(pid.f);

    rightPIDController.setP(pid.p);
    rightPIDController.setI(pid.i);
    rightPIDController.setD(pid.d);
    rightPIDController.setFF(pid.f);
  }

  public void setPIDBack(PID pid){
    backRightPIDController.setP(pid.p);
    backRightPIDController.setI(pid.i);
    backRightPIDController.setD(pid.d);
    backRightPIDController.setFF(pid.f);

    backLeftPIDController.setP(pid.p);
    backLeftPIDController.setI(pid.i);
    backLeftPIDController.setD(pid.d);
    backLeftPIDController.setFF(pid.f);
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
