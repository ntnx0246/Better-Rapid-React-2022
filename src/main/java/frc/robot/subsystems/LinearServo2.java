package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class LinearServo2 extends Servo {
  double m_speed;
  double m_angle;

  double setPos;
  double curPos;

  public LinearServo2(int channel) {
    super(channel);
  }

  /**
   * Parameters for L-16 Actuonix Linear Actuators
   * 
   * @param channel PWM channel used to control the servo
   * @param length  max length of the servo [mm]
   * @param speed   max speed of the servo [mm/second]
   */
  public LinearServo2(int channel, int angle, int speed) {
    super(channel);
    setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    m_angle = angle;
    m_speed = speed;
  }

  /**
   * Run this method in nay periodic function to update the position estimation of
   * the servo
   * 
   * @param setpoint the target position of the servo [mm]
   */
  public void setPosition(double setpoint) {
    System.out.println("AHAHAHAAHAHAFDKSLDKSJKLASLJKDFJKDJFK");
    setPos = MathUtil.clamp(setpoint, 0, 1);
    super.set(setPos);
  }

  public void setMyPosition(double setpoint) {
    setPos = MathUtil.clamp(setpoint, 0, 1);
    super.set(setPos);
  }

  double lastTime = 0;

  /**
   * Run this method in any periodic function to update the position estimation of
   * your servo
   */
  public void updateCurPos() {
    double dt = Timer.getFPGATimestamp() - lastTime;
    if (curPos > setPos + m_speed * dt) {
      curPos -= m_speed * dt;
    } else if (curPos < setPos - m_speed * dt) {
      curPos += m_speed * dt;
    } else {
      curPos = setPos;
    }
  }
}