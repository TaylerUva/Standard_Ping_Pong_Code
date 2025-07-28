package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants.ShooterConstants;

public class StartFlywheel extends Command {
  private final Shooter globalShooter;

  public StartFlywheel(Shooter conShooter) {
    this.globalShooter = conShooter;
    addRequirements(conShooter);
  }

  @Override
  public void initialize() {
    globalShooter.setFlywheelSpeed(ShooterConstants.FLYWHEEL_SPEED_HIGH);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
