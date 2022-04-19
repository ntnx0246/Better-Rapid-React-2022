package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Constants;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private final Intake intake;
  private final ColorSensor colorSensor;
  private boolean usingColorSensor;

  public IntakeBalls(Intake intake, ColorSensor colorSensor) {
    addRequirements(intake);
    this.intake = intake;
    this.colorSensor = colorSensor;
    usingColorSensor = true;
  }

  public IntakeBalls(Intake intake) {
    addRequirements(intake);
    this.intake = intake;
    this.colorSensor = null;
    usingColorSensor = false;
  }

  @Override
  public void initialize() {
    intake.intakeTopMotor(Constants.Intake.TOP_SPEED);
    intake.intakeBottomMotor(Constants.Intake.BOTTOM_SPEED);
    intake.intakeOutsideMotor(Constants.Intake.OUTSIDE_SPEED);
    intake.setRaisingMotorPosition(-Constants.Intake.INTAKE_OUT_ENCODER);
  }

  @Override
  public void execute() {
    if(usingColorSensor){
      if (colorSensor.getProximity()<2){
        SmartDashboard.putBoolean("INTAKED FULLY", true);
      } else {
        SmartDashboard.putBoolean("INTAKED FULLY", false);
      }
      SmartDashboard.putString("Color Ball Intaked", colorSensor.getColor());
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    intake.setRaisingMotorPosition(0);
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
