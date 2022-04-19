package frc.robot.utils;

public class PID {
    public double p;
    public double i;
    public double d;
    public double f;
    public int s;

    public PID(double p, double i, double d, double f, int s) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = s;
    }
    public PID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = -1;
    }

    public void updatePID(double p, double i, double d, double f, int s) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = s;
    }

    public void updatePID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = -1;
    }
}