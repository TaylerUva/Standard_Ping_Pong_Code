package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

@Logged
public class Hopper extends SubsystemBase {
  private final WPI_TalonSRX feeder;

  /** Creates a new Hopper. */
  public Hopper() {
    feeder = new WPI_TalonSRX(RobotMap.mapHopper.HOPPER_MOTOR);
  }

  /**
   * Sets the speed of the hopper motor.
   *
   * @param speed The speed to set, between -1.0 and 1.0.
   */
  public void setFeederSpeed(double speed) {
    feeder.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
