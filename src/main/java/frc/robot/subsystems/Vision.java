package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.UsbCamera;
// import edu.wpi.first.cscore.VideoMode;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.utils.ShuffleBoard;

public class Vision extends SubsystemBase {
  private static final int BAUD = 9600;
  private double angle;
  private double yPos;
  private SerialPort camPort;
  // private UsbCamera visionCam;

  public Vision() {

    // CameraServer.startAutomaticCapture();


    // visionCam = new UsbCamera("cam0", 1);
    // visionCam = CameraServer.startAutomaticCapture();

    // visionCam.setVideoMode(VideoMode.PixelFormat.kYUYV, 320, 240, 30);

    try {
      System.out.println("1st Try");
      camPort = new SerialPort(BAUD, SerialPort.Port.kUSB);
    } catch (Exception e) {
      System.out.println("Error - 2nd Try");
      try {
        camPort = new SerialPort(BAUD, SerialPort.Port.kUSB1);
      } catch (Exception j) {
        try {
          System.out.println("Error - 3rd Try");
          camPort = new SerialPort(BAUD, SerialPort.Port.kUSB2);
        } catch (Exception k) {
          System.out.println("Could not connect robot to jevois");
        }
      }
    }
    if (camPort != null) {
      packetListenerThread.start();
    }
    SmartDashboard.putBoolean("Vision Alive", camPort != null);
  }

  // @Override
  public void backgroundUpdate() {
    try {
      String data = camPort.readString();
      String[] realData = data.split(";");
      if (realData.length == 2) {
        // System.out.println("ANGLE IS SUPPOSED TO BE: " + realData[0]);
        // System.out.println("yPOS IS SUPPOSED TO BE: " + realData[1]);
        angle = Double.valueOf(realData[0]);
        yPos = Double.valueOf(realData[1]);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return;
  }

  public double getAngle() {
    return angle;
  }

  public double getFrontRPM() {
    return yPos;
  }

  public double getBackRPM() {
    return yPos;
  }

  Thread packetListenerThread = new Thread(new Runnable() {
    public void run() {
      while (!Thread.interrupted()) {
        backgroundUpdate();
      }
    }
  });
}

