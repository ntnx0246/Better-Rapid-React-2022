// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LinearServo;

public class PistonMove extends CommandBase {
    /** Creates a new ClimbTeleop. */
    private final LinearServo servo;
    private int counter;

    public PistonMove(LinearServo servo) {
        this.servo = servo;
        counter = 0;
    }

    @Override
    public void initialize() {
        servo.setPosition(0.4);
        counter = 0;
    }

    @Override
    public void execute() {
        if (counter > 100) {
            servo.setPosition(1);
        }
        counter++;

    }

    @Override
    public void end(boolean interrupted) {
        servo.setPosition(1);
    }

    @Override
    public boolean isFinished() {
        if (counter > 200) {
            return true;
        }
        return false;
    }
}
