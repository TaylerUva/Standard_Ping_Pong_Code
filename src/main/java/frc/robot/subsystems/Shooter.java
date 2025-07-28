// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

@Logged
public class Shooter extends SubsystemBase {
  private final TalonFX flywheel;

  /** Creates a new Shooter. */
  public Shooter() {
    flywheel = new TalonFX(RobotMap.mapShooter.SHOOTER_MOTOR);
  }

  /**
   * Sets the speed of the shooter motor.
   *
   * @param speed The speed to set, between -1.0 and 1.0.
   */
  public void setFlywheelSpeed(double speed) {
    flywheel.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
