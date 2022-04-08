package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class Shoot extends CommandBase {
    private Intake intake;
    private Shooter shooter;
    private DriveTrain driveTrain;
    private NavX navX;
    private Vision vision;
    private boolean usingVision;
    private boolean isAuto;
    private int shooterVelocity;
    private int rollerVelocity;
    private IntSupplier suppliedVelocity;
    private IntSupplier suppliedRollerVelocity;
    private int pulseCounter;
    private int rpmCounter;
    private boolean turned;
    private int visionFrontRPM;
    private int visionBackRPM;
    private double visionAngle;

    // TELEOP VISION
    public Shoot(Intake intake, Shooter shooter, IntSupplier suppliedVelocity, IntSupplier suppliedRollerVelocity,
            Vision vision, DriveTrain driveTrain, NavX navX) {
        addRequirements(intake, shooter, vision, driveTrain);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.vision = vision;
        this.usingVision = true;
        this.navX = navX;
        this.driveTrain = driveTrain;
        this.suppliedVelocity = suppliedVelocity;
        this.suppliedRollerVelocity = suppliedRollerVelocity;
    }

    // TELEOP
    public Shoot(Intake intake, Shooter shooter, IntSupplier suppliedVelocity, IntSupplier suppliedRollerVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.usingVision = false;
        this.suppliedVelocity = suppliedVelocity;
        this.suppliedRollerVelocity = suppliedRollerVelocity;
    }

    // AUTO
    public Shoot(Intake intake, Shooter shooter, int shooterVelocity, int rollerVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = true;
        this.usingVision = false;
        this.shooterVelocity = shooterVelocity;
        this.rollerVelocity = rollerVelocity;
    }

    @Override
    public void initialize() {
        if (isAuto) {
            shooter.setVelocity(shooterVelocity);
            shooter.setRollerVelocity(rollerVelocity);
        } else if (!usingVision) {
            shooter.setVelocity(suppliedVelocity.getAsInt());
            shooter.setRollerVelocity(suppliedRollerVelocity.getAsInt());
        } else {
            navX.reset();
        }
        pulseCounter = 0;
        rpmCounter = 0;
        turned = false;
        if (usingVision){
            visionFrontRPM = (int) vision.getFrontRPM();
            visionBackRPM = (int) vision.getBackRPM();
            visionAngle = vision.getAngle();
        }
        
    }

    @Override
    public void execute() {
        if (usingVision) {
            
            double errorAngle = 0;
            System.out.println("NAVX ANGLE: " + navX.getAngle());
            if (visionAngle>0){
                errorAngle = visionAngle - navX.getAngle();
            } else {
                errorAngle = -(visionAngle - navX.getAngle());
            }
            // System.out.println("ERROR: " + errorAngle);
            if(Constants.DEBUG){
                System.out.println("HI THERE SHOOTER IS ON DEBUG");
            }
            if (Math.abs(errorAngle) < 1 || turned == true) {
                turned = true;
                System.out.println("SHOOTINGGGGGGGG");
                // shooter.setVelocity(visionFrontRPM);
                // shooter.setRollerVelocity(visionBackRPM);
                // shootWhenReady(visionFrontRPM, visionBackRPM);

                driveTrain.tankDrive(0, 0);
            } else {
                double power = (errorAngle / visionAngle)*0.3;
                // System.out.println("POWER SETTING DRIVETRAIN: " +power);
                driveTrain.tankDrive(power, -power);
            }
            // double power = (errorAngle / visionAngle) * 0.3;
            // // System.out.println("POWER SETTING DRIVETRAIN: " + power);
            // driveTrain.tankDrive(power, -power);
        } else {
            shootWhenReady(isAuto ? shooterVelocity : suppliedVelocity.getAsInt(),
                    isAuto ? rollerVelocity : suppliedRollerVelocity.getAsInt());
        }
    }

    private void shootWhenReady(double velocity, double backVelocity) {
        double error = velocity - shooter.getLeftVelocity();
        double errorBack = backVelocity - shooter.getBackLeftVelocity();
        SmartDashboard.putNumber("Front Shooter RPM IMPORTANTE", shooter.getLeftVelocity());
        SmartDashboard.putNumber("Back Shooter RPM IMPORTANTE", shooter.getBackLeftVelocity());
        if (error <= Constants.Shooter.RPM_TOLERANCE && errorBack <= Constants.Shooter.RPM_TOLERANCE) {
            rpmCounter++;
        }
        if (rpmCounter > 15) {
            if (pulseCounter < 10) {
                intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
                intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
                pulseCounter++;
            } else if (rpmCounter < 50) {
                intake.intakeTopMotor(0);
                intake.intakeBottomMotor(0);
                pulseCounter++;
            } else {
                intake.intakeTopMotor(0);
                intake.intakeBottomMotor(0);
                pulseCounter = 0;
                rpmCounter = 0;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        shooter.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
