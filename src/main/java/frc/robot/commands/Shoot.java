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
    private IntSupplier suppliedVelocity;

    private double wait;

    // TELEOP
    public Shoot(Intake intake, Shooter shooter, IntSupplier suppliedVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = false;
        this.suppliedVelocity = suppliedVelocity;
    }

    // AUTO
    public Shoot(Intake intake, Shooter shooter, double wait, int shooterVelocity) {
        addRequirements(intake, shooter);
        this.intake = intake;
        this.shooter = shooter;
        this.isAuto = true;
        this.wait = wait;
        timer = new Timer();
        this.shooterVelocity = shooterVelocity;
    }

    @Override
    public void initialize() {
        if (isAuto) {
            timer.reset();
            timer.start();
            shooter.setVelocity(shooterVelocity);
        } else {
            shooter.setVelocity(suppliedVelocity.getAsInt());
        }
    }

    @Override
    public void execute() {
        double error = isAuto ? Math.abs(shooterVelocity - shooter.getLeftVelocity())
                : Math.abs(suppliedVelocity.getAsInt() - shooter.getLeftVelocity());
        if (error <= Constants.Shooter.RPM_TOLERANCE) {
            intake.intakeTopMotor(Constants.Shooter.PUSH_SPEED * -1);
            intake.intakeBottomMotor(Constants.Shooter.PUSH_SPEED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
        shooter.stop();
    }

    @Override
    public boolean isFinished() {
        if (isAuto && timer.get() > wait) {
            timer.stop();
            return true;
        }
        return false;
    }
}
