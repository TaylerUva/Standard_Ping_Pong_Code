// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.frcteam3255.joystick.SN_XboxController;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.RobotMap.mapControllers;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

@Logged
public class RobotContainer {
  private final SN_XboxController conDriver = new SN_XboxController(mapControllers.DRIVER_USB);

  private final Drivetrain drivetrain = new Drivetrain();
  private final Shooter shooter = new Shooter();
  private final Hopper hopper = new Hopper();

  public RobotContainer() {
    drivetrain.setDefaultCommand(new DriveManual(drivetrain, conDriver.axis_LeftY, conDriver.axis_RightX));
    configureBindings();
  }

  private void configureBindings() {
    conDriver.btn_A.onTrue(new StartFlywheel(shooter));
    conDriver.btn_B.onTrue(new StopFlywheel(shooter));
    conDriver.btn_RightTrigger.whileTrue(new RunHopper(hopper));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
