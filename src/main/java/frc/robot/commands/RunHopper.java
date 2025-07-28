package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Hopper;
import frc.robot.Constants.HopperConstants;

public class RunHopper extends Command {
  private final Hopper globalHopper;

  public RunHopper(Hopper conhopper) {
    globalHopper = conhopper;
    addRequirements(conhopper);
  }

  @Override
  public void initialize() {
    globalHopper.setFeederSpeed(HopperConstants.FEEDER_SPEED_ON);
  }

  @Override
  public void end(boolean interrupted) {
    globalHopper.setFeederSpeed(HopperConstants.FEEDER_SPEED_OFF);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
