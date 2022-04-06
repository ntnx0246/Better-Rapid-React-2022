package frc.robot.utils;

public class PID {
    public final double p;
    public final double i;
    public final double d;
    public final double f;
    public final int s;

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
}