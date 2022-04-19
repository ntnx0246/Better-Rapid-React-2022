package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.utils.ShuffleBoard;
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
    private ShuffleBoard shuffleBoard;
    private boolean usingVision;
    private boolean isAuto;
    private int shooterVelocity;
    private int rollerVelocity;
    private IntSupplier suppliedVelocity;
    private IntSupplier suppliedRollerVelocity;
    private int pulseCounter;
    private int rpmCounter;
    private int visionFrontRPM;
    private int visionBackRPM;
    private double visionAngle;
    private int turnCounter;
    private double initalizeEncoderLeft;
    private boolean driveTrainDebug = false;
    private boolean shooterDebug = false;

    // TELEOP VISION
    public Shoot(Intake intake, Shooter shooter, Vision vision, DriveTrain driveTrain, NavX navX) {
        addRequirements(intake, shooter, vision, driveTrain);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.vision = vision;
        this.usingVision = true;
        this.navX = navX;
        this.driveTrain = driveTrain;
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

    // TELEOP DEBUG MODE
    public Shoot(Intake intake, Shooter shooter, IntSupplier suppliedVelocity, IntSupplier suppliedRollerVelocity, ShuffleBoard shuffleBoard) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.usingVision = false;
        this.shooterDebug = false;
        this.suppliedVelocity = suppliedVelocity;
        this.suppliedRollerVelocity = suppliedRollerVelocity;
        this.shuffleBoard = shuffleBoard;
    }

    // TELEOP VISION DEBUG
    public Shoot(Intake intake, Shooter shooter, Vision vision, DriveTrain driveTrain, NavX navX, ShuffleBoard shuffleBoard) {
        addRequirements(intake, shooter, vision, driveTrain);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.vision = vision;
        this.usingVision = true;
        this.driveTrainDebug = true;
        this.navX = navX;
        this.driveTrain = driveTrain;
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
        if (shooterDebug){
            shooter.setPIDFront(shuffleBoard.getShooterPID());
        }
        if (usingVision) {
            visionFrontRPM = (int) vision.getFrontRPM();
            visionBackRPM = (int) vision.getBackRPM();
            visionAngle = vision.getAngle();
            
            navX.reset();
            if (driveTrainDebug){
                driveTrain.setPID(shuffleBoard.getDriveTrainPID());
            }
            // shooter.setVelocity(visionFrontRPM);
            // shooter.setRollerVelocity(visionBackRPM);
            // driveTrain.setAngle(visionAngle);
            initalizeEncoderLeft = driveTrain.getLeftEncoderCount();
            turnCounter = 0;

        } else if (isAuto) {
            shooter.setVelocity(shooterVelocity);
            shooter.setRollerVelocity(rollerVelocity);
        } else if (!usingVision) {
            shooter.setVelocity(suppliedVelocity.getAsInt());
            shooter.setRollerVelocity(suppliedRollerVelocity.getAsInt());
        } else {
            System.out.println("SOMETHING WENT REALLY WRONG UR STUPIDLY SCREWED");
        }
        pulseCounter = 0;
        rpmCounter = 0;
    }

    @Override
    public void execute() {
        if (usingVision) {
            // nav X stuff
            double errorAngle = visionAngle - navX.getAngle();
            System.out.println("NAVX ANGLE: " + navX.getAngle());
            double min = 0.01;
            double max = 0.1;
            int thresholdAngle = 1;
            if (visionAngle < 0){
                // driveTrain.tankDrive(-errorAngle/visionAngle*force-constant,
                // errorAngle/visionAngle*force+constant);
                if (errorAngle < thresholdAngle) {
                    driveTrain.tankDrive(-((max - min) / thresholdAngle) * errorAngle - min,
                            ((max - min) / thresholdAngle) * errorAngle + min);
                } else {
                    driveTrain.tankDrive(-max, max);
                }
            } else {
                // driveTrain.tankDrive(errorAngle/visionAngle*force+constant,
                // -errorAngle/visionAngle*force-constant);
                if (errorAngle < thresholdAngle) {
                    driveTrain.tankDrive(((max - min) / thresholdAngle) * errorAngle + min,
                            -((max - min) / thresholdAngle) * errorAngle - min);
                } else {
                    driveTrain.tankDrive(max, -max);
                }
            }

            // pid stuff
            // System.out.println("ENCODER PERCENT CORRECT: " + (driveTrain.getLeftEncoderCount()
                    // / (initalizeEncoderLeft + (visionAngle * Constants.DriveTrain.TURN_CONSTANT))));

            // System.out.println("ENCODER VALUES OFF: " + ((initalizeEncoderLeft + (visionAngle * Constants.DriveTrain.TURN_CONSTANT))-driveTrain.getLeftEncoderCount()));

            // check multiple times
            if (turnCounter > 5 && Math.abs(errorAngle) < .5) {
                System.out.println("SHOOTING");
                // shootWhenReady(visionFrontRPM, visionBackRPM);
                driveTrain.tankDrive(0, 0);
            } else if (Math.abs(errorAngle) < .5) {
                System.out.println("TURN COUNTER INCREASED TO:"+turnCounter);
                System.out.println("ERROR ANGLE:"+errorAngle);
                System.out.println("NAVX ANGLE:"+navX.getAngle());
                System.out.println("VISION ANGLE:"+visionAngle);
                turnCounter ++;

                driveTrain.tankDrive(0, 0);

                // visionAngle = vision.getAngle();
                // navX.reset();
                // initalizeEncoderLeft = driveTrain.getLeftEncoderCount();
                // driveTrain.setAngle(visionAngle);
            }
        } else {
            SmartDashboard.putNumber("Front Shooter LEFT RPM IMPORTANTE", shooter.getLeftVelocity());
            SmartDashboard.putNumber("Front Shooter RIGHT RPM IMPORTANTE", shooter.getRightVelocity());
            SmartDashboard.putNumber("Back Shooter RPM IMPORTANTE", shooter.getBackLeftVelocity());
            shootWhenReady(isAuto ? shooterVelocity : suppliedVelocity.getAsInt(),
                    isAuto ? rollerVelocity : suppliedRollerVelocity.getAsInt());
        }
    }

    private void shootWhenReady(double velocity, double backVelocity) {
        double error = Math.abs(velocity - shooter.getLeftVelocity());
        double errorBack = Math.abs(backVelocity - shooter.getBackLeftVelocity());
        SmartDashboard.putNumber("Front Shooter LEFT RPM IMPORTANTE", shooter.getLeftVelocity());
        SmartDashboard.putNumber("Front Shooter RIGHT RPM IMPORTANTE", shooter.getRightVelocity());
        SmartDashboard.putNumber("Back Shooter RPM IMPORTANTE", shooter.getBackLeftVelocity());
        if (error <= Constants.Shooter.RPM_TOLERANCE && errorBack <= Constants.Shooter.RPM_TOLERANCE) {
            rpmCounter++;
            System.out.println("RPM VALUE IS "+rpmCounter);
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
