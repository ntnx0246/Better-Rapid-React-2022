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
    private boolean shooting;

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
        this.shooterDebug = true;
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
        this.shooterDebug = false;
        System.out.println("DRIVETRIAN HAS BEEN SET");
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
        // if (shooterDebug){
        //     shooter.setPIDFront(shuffleBoard.getShooterPID());
        // }
        if (usingVision) {
            shooting = false;
            // visionFrontRPM = (int) vision.getFrontRPM();
            // visionBackRPM = (int) vision.getBackRPM();
            visionAngle = vision.getAngle();
            
            navX.reset();
            // if (driveTrainDebug){
            //     driveTrain.setPID(shuffleBoard.getDriveTrainPID());
            // }
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
            double errorAngle = Math.abs(visionAngle - navX.getAngle());
            if (shooting){
                shootWhenReady(visionFrontRPM, visionBackRPM);
            } else if (turnCounter > 5 && Math.abs(errorAngle) < 1) {
                System.out.println("SHOOTING");
                visionFrontRPM = (int) vision.getFrontRPM();
                visionBackRPM = (int) vision.getBackRPM();
                System.out.println("THE VISIN FRONT RPM WILL BE: "+visionFrontRPM);
                shooter.setVelocity(visionFrontRPM);
                shooter.setRollerVelocity(visionBackRPM);
                shooting = true;
                driveTrain.tankDrive(0, 0);
            } else if (Math.abs(errorAngle) < 1) {
                turnCounter ++;

                driveTrain.tankDrive(0, 0);

                // visionAngle = vision.getAngle();
                // navX.reset();

                // initalizeEncoderLeft = driveTrain.getLeftEncoderCount();
                // driveTrain.setAngle(visionAngle);
            } else {
                System.out.println("NAVX ANGLE: " + navX.getAngle());
                double point1 = 0.01;
                double point2 = 0.1;
                double point3 = 0.15;
                double point4 = 0.3;
                int angle1 = 3;
                int angle2 = 15;
                int angle3 = 50;
                if ((visionAngle - navX.getAngle()) < 0){
                    // driveTrain.tankDrive(-errorAngle/visionAngle*force-constant,
                    // errorAngle/visionAngle*force+constant);
                    if (errorAngle < angle1) {
                        driveTrain.tankDrive(-((point2 - point1) / angle1) * errorAngle - point1,
                                ((point2 - point1) / angle1) * errorAngle + point1);
                    } else if (errorAngle < angle2){
                        driveTrain.tankDrive(-((point3 - point2) / angle2) * errorAngle - point2,
                                ((point3 - point2) / angle2) * errorAngle + point2);
                    } else if (errorAngle < angle3){
                        driveTrain.tankDrive(-((point4 - point3) / angle3) * errorAngle - point3,
                                ((point4 - point3) / angle3) * errorAngle + point3);
                    } else {
                        driveTrain.tankDrive(-point4, point4);
                    }
                } else {
                    if (errorAngle < angle1) {
                        driveTrain.tankDrive(((point2 - point1) / angle1) * errorAngle + point1,
                                -((point2 - point1) / angle1) * errorAngle - point1);
                    } else if (errorAngle < angle2){
                        driveTrain.tankDrive(((point3 - point2) / angle2) * errorAngle + point2,
                                -((point3 - point2) / angle2) * errorAngle - point2);
                    } else if (errorAngle < angle3){
                        driveTrain.tankDrive(((point4 - point3) / angle3) * errorAngle + point3,
                                -((point4 - point3) / angle3) * errorAngle - point3);
                    } else {
                        driveTrain.tankDrive(point4, -point4);
                    }
                }
            }
        } else {
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
            // System.out.println("RPM VALUE IS "+rpmCounter);
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
