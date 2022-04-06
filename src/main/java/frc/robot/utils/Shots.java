package frc.robot.utils;

public final class Shots {
    public final int frontRPM;
    public final int backRPM;
    public final int distanceBack;

    public Shots(int frontRPM, int backRPM, int distanceBack) {
        this.frontRPM = frontRPM;
        this.backRPM = backRPM;
        this.distanceBack = distanceBack;
    }

    public int getFrontRPM(){
        return frontRPM;
    }

    public int getBackRPM(){
        return backRPM;
    }

    public int getDistance(){
        return distanceBack;
    }

}