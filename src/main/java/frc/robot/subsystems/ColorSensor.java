// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
// import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {
  private I2C.Port i2cPort = I2C.Port.kOnboard;
  private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  private final ColorMatch colorMatcher = new ColorMatch();

  // Our Wheel
  private final Color kBlueTarget = new Color(0.160, 0.405, 0.435); // .143,.427,.429
  // private final Color kRedTarget = new Color(0.627, 0.310, 0.063); //.561,
  // .232, .114
  private final Color kRedTarget = new Color(0.482, 0.364, 0.154);

  private String colorString;
  private double proximity;

  private double colorRatio;
  private int numBalls = 0;
  private int ball1 = -1; // -1 is none 0 is red and 1 is blue
  private int ball2 = -1; // -1 is none 0 is red and 1 is blue

  public ColorSensor() {
    colorMatcher.addColorMatch(kBlueTarget);
    colorMatcher.addColorMatch(kRedTarget);
    colorString = "Unknown";
    colorThread.start();
    colorRatio = 0;
    numBalls = 0;
    ball1 = -1;
    ball2 = -1;
  }

  public void backgroundUpdate() {
    // This method will be called once per scheduler run

    // Color detectedColor = colorSensor.getColor();
    colorMatcher.setConfidenceThreshold(.95);
    // ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    proximity = colorSensor.getProximity() * 10 / 2047;

    colorRatio = colorSensor.getRed() / colorSensor.getBlue();
    if (proximity < 2) {
      if (numBalls == 1) {
        ball1 = (int) colorRatio;
      } else if (numBalls == 2) {
        ball2 = (int) colorRatio;
      }
    } else {
      ball1 = -1;
      ball2 = -1;
    }

    // if (match.color == kBlueTarget) {
    // colorString = "B";
    // } else if (match.color == kRedTarget) {
    // colorString = "R";
    // } else {
    // colorString = "Unknown";
    // }
    // System.out.println(colorString);
    // System.out.println(proximity);
    Timer.delay(0.05);
  }

  public String getColor() {
    return colorString;
  }

  public double getProximity() {
    return proximity;
  }

  public double colorRatio() {
    return colorRatio;
  }

  public int getNumBalls() {
    return numBalls;
  }

  public int getBall1() {
    return ball1;
  }

  public int getBall2() {
    return ball2;
  }

  public void incrementNumBalls() {
    this.numBalls = this.numBalls + 1 > 2 ? 2 : ++this.numBalls;
  }

  public void decrementNumBalls() {
    this.numBalls = this.numBalls - 1 < 0 ? 0 : --this.numBalls;
  }

  Thread colorThread = new Thread(new Runnable() {
    public void run() {
      while (!colorThread.isInterrupted()) {
        backgroundUpdate();
      }
    }
  });
}