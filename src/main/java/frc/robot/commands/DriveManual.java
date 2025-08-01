// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveManual extends Command {
  private Drivetrain globalDrivetrain;
  private DoubleSupplier globalSpeedSupplier;
  private DoubleSupplier globalRotationSupplier;

  /** Creates a new ArcadeDrive. */
  public DriveManual(Drivetrain inDrivetrain, DoubleSupplier inSpeedSupplier, DoubleSupplier inRotationSupplier) {
    // Use addRequirements() here to declare subsystem dependencies.
    globalDrivetrain = inDrivetrain;
    globalSpeedSupplier = inSpeedSupplier;
    globalRotationSupplier = inRotationSupplier;
    addRequirements(inDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    globalDrivetrain.arcadeDrive(globalSpeedSupplier.getAsDouble(), globalRotationSupplier.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
