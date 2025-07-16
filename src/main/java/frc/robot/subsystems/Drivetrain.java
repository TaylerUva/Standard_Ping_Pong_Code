// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.mapDrivetrain;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;

@Logged
public class Drivetrain extends SubsystemBase {
  private final AnalogGyro m_gyro = new AnalogGyro(0);
  private final WPI_TalonSRX leftFrontMotor;
  private final WPI_TalonSRX leftBackMotor;
  private final WPI_TalonSRX rightFrontMotor;
  private final WPI_TalonSRX rightBackMotor;
  private final DifferentialDrive differentialDrive;

  // Simulation components
  private DifferentialDrivetrainSim drivetrainSim;
  private DifferentialDriveOdometry odometry;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    // Initialize motors
    leftFrontMotor = new WPI_TalonSRX(mapDrivetrain.LEFT_FRONT_MOTOR);
    leftBackMotor = new WPI_TalonSRX(mapDrivetrain.LEFT_BACK_MOTOR);
    rightFrontMotor = new WPI_TalonSRX(mapDrivetrain.RIGHT_FRONT_MOTOR);
    rightBackMotor = new WPI_TalonSRX(mapDrivetrain.RIGHT_BACK_MOTOR);

    // Set up motor groups
    leftBackMotor.follow(leftFrontMotor);
    rightBackMotor.follow(rightFrontMotor);

    // Create the differential drive
    differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param speed    The forward/backward speed.
   * @param rotation The rotation speed.
   */
  public void arcadeDrive(double speed, double rotation) {
    differentialDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /*
   * SIMULATION CODE
   */
  boolean configureSim = false;

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  @Override
  public void simulationPeriodic() {
    if (configureSim == false) {
      configureSim();
      configureSim = true;
    }

    // Update simulation
    drivetrainSim.setInputs(
        leftFrontMotor.get() * 12.0, // Convert percent output to voltage
        rightFrontMotor.get() * 12.0);
    drivetrainSim.update(0.02); // Update simulation every 20ms
    odometry.update(
        Rotation2d.fromDegrees(drivetrainSim.getHeading().getDegrees()),
        drivetrainSim.getLeftPositionMeters(),
        drivetrainSim.getRightPositionMeters());

  }

  public void configureSim() {
    // Initialize simulation components
    drivetrainSim = new DifferentialDrivetrainSim(
        DCMotor.getCIM(2), // 2 CIM motors per side
        7.29, // Gear ratio
        7.5, // Moment of inertia (kg*m^2)
        60.0, // Robot mass (kg)
        Units.inchesToMeters(3), // Wheel radius (m)
        0.546, // Track width (m)
        null // Standard measurement noise
    );

    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(m_gyro.getAngle()),
        leftBackMotor.getSelectedSensorPosition(),
        leftBackMotor.getSelectedSensorPosition());
  }
}
