// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensor extends SubsystemBase {

    private I2C.Port i2cPort = I2C.Port.kOnboard;
    private ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  
    private final ColorMatch colorMatcher = new ColorMatch();
  
    //Our Wheel
    private final Color kBlueTarget = new Color(0.160, 0.405, 0.435); //.143,.427,.429
    //private final Color kGreenTarget = new Color(0.180, 0.620, 0.200); //.197,.561,.240
    // private final Color kRedTarget = new Color(0.627, 0.310, 0.063); //.561, .232, .114
    //private final Color kYellowTarget = new Color(0.386, 0.516, 0.088);//.361, .524, .113

    private final Color kRedTarget = new Color(0.482, 0.364, 0.154);
      
    private String colorString;
    private double proximity;
  
    public ColorSensor() {
      colorMatcher.addColorMatch(kBlueTarget);
      //colorMatcher.addColorMatch(kGreenTarget);
      colorMatcher.addColorMatch(kRedTarget);
      //colorMatcher.addColorMatch(kYellowTarget); 
      colorString = "";
      colorThread.start();
    }
  
    public void backgroundUpdate() {
      // This method will be called once per scheduler run
  
      Color detectedColor = colorSensor.getColor();
      colorMatcher.setConfidenceThreshold(.95);
      ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
      proximity = colorSensor.getProximity()*10/2047;
      System.out.println("Red: " + detectedColor.red);
      System.out.println("Green: " + detectedColor.green);
      System.out.println("Blue: " + detectedColor.blue);
      
      if (match.color == kBlueTarget) {
        colorString = "B";
      } else if (match.color == kRedTarget) {
        colorString = "R";
      } else {
        colorString = "Unknown";
      }
      System.out.println(colorString);
      System.out.println(proximity);
      Timer.delay(0.05);
    }

    public String getColor(){
        return colorString;
    }

    public double getProximity(){
        return proximity;
    }

    Thread colorThread = new Thread(new Runnable() {
        public void run() {
          while (!colorThread.isInterrupted()) {
            backgroundUpdate();
          }
        }
    });
}