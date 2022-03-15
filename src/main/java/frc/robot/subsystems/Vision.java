// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  private final String TABLE_NAME = "Vision";
  private final String ANGLE_ENTRY = "Angle";
  private final String DISTANCE_ENTRY = "Distance";

  private NetworkTable visionTable;

  /** Creates a new Vision. ONLY FOR TARGET STUFFS */
  public Vision() {
    visionTable = NetworkTableInstance.getDefault().getTable(TABLE_NAME);
    visionTable.getEntry("Test Entry").setDouble(69420);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getAngle() {
    NetworkTableEntry angleEntry = this.visionTable.getEntry(ANGLE_ENTRY);
    double angle = angleEntry.getDouble(-1);
    // TODO kill print
    System.out.println("Angle: " + angle);
    return angle;
  }

  public double getDistance() {
    NetworkTableEntry distanceEntry = this.visionTable.getEntry(DISTANCE_ENTRY);
    double distance = distanceEntry.getDouble(-1);
    // TODO kill print
    System.out.println("Distance: " + distance);
    return distance;
  }
}
