package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NavX extends SubsystemBase {
  /** Creates a new NavX. */
  private final AHRS navX;

  public NavX() {
    navX = new AHRS(Port.kMXP, (byte) 50);
  }

  public double getAngle() {
    return navX.getAngle();
  }

  public void reset() {
    navX.reset();
  }

  @Override
  public void periodic() {
  }
}