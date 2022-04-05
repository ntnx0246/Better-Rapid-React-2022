// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
// import edu.wpi.first.wpilibj.Timer;

public class Shoot extends CommandBase {
    private Intake intake;
    private Shooter shooter;
    private boolean isAuto;
    private Timer timer;
    private int shooterVelocity;
    private int rollerVelocity;
    private IntSupplier suppliedVelocity;
    private IntSupplier suppliedRollerVelocity;
    private int pulseCounter; 
    private int rpmCounter;

    // TELEOP
    public Shoot(Intake intake, Shooter shooter, IntSupplier suppliedVelocity, IntSupplier suppliedRollerVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.suppliedVelocity = suppliedVelocity;
        this.suppliedRollerVelocity = suppliedRollerVelocity;
    }

    // AUTO
    public Shoot(Intake intake, Shooter shooter, int shooterVelocity, int rollerVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = true;
        timer = new Timer();
        this.shooterVelocity = shooterVelocity;
        this.rollerVelocity = rollerVelocity;
    }

    @Override
    public void initialize() {
        if (isAuto) {
            timer.reset();
            timer.start();
            shooter.setVelocity(shooterVelocity);
            shooter.setRollerVelocity(rollerVelocity);
        } else {
            shooter.setVelocity(suppliedVelocity.getAsInt());
            shooter.setRollerVelocity(suppliedRollerVelocity.getAsInt());
        }
        pulseCounter = 0;
        rpmCounter = 0;
    }

    @Override
    public void execute() {
        double error = isAuto ? Math.abs(shooterVelocity - shooter.getLeftVelocity())
                : Math.abs(suppliedVelocity.getAsInt() - shooter.getLeftVelocity());
        double errorBack = isAuto ? Math.abs(rollerVelocity - shooter.getRollerLeftVelocity())
                : Math.abs(suppliedRollerVelocity.getAsInt() - shooter.getRollerLeftVelocity());
        if (error <= Constants.Shooter.RPM_TOLERANCE && errorBack <= Constants.Shooter.RPM_TOLERANCE ) {
            rpmCounter++;
        }
        if(rpmCounter > 10){
            if (pulseCounter < 10){
                intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
                intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
                pulseCounter ++;
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
