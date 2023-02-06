// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import org.photonvision.SimVisionSystem;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ApriltagCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  // Declare the subsystems
  private DriveSubsystem drive;
  private ApriltagSubsystem apriltag;
  private final int targetID;

  public ApriltagCommand(ApriltagSubsystem apriltag, DriveSubsystem drive, int targetID) {
    this.apriltag = apriltag;
    this.drive = drive;
    this.targetID = targetID;
    
    addRequirements(apriltag, drive);
  }

  //PID constants
  double kPf = 0.1;
  double kIf = 0.1;
  double kDf = 0.1;
  double kPt = 0.1;
  double kIt = 0.1;
  double kDt = 0.1;

  //variables
  double forward = 0;
  double turn = 0;
  double time = 0;  
  double lastTime = 0;
  double deltaT = 0;
  double deltaForward = 0;
  double deltaTurn = 0;
  double sum_Forward = 0;
  double sum_Turn = 0;
  double output_Forward = 0;
  double output_Turn = 0;

  // Normal Target
  boolean hasTarget = apriltag.hasTarget();
  double yaw = apriltag.getYaw();
  double pitch = apriltag.getPitch();
  Transform3d targetToCamera = apriltag.getCameratoTarget();
 
  // // Camera Constants
  // double camDiagFOV = 170.0; // degrees - assume wide-angle camera
  // double camPitch = 10; // degrees
  // double camHeightOffGround = 0.2; // meters
  // double maxLEDRange = 20; // meters
  // int camResolutionWidth = 640; // pixels
  // int camResolutionHeight = 480; // pixels
  // double minTargetArea = 10; // square pixels

  //  SimVisionSystem simVision =
  //   new SimVisionSystem(
  //     "AprilTag",
  //     camDiagFOV,
  //     new Transform3d( new Translation3d(0, 0, camHeightOffGround), new Rotation3d(0, camPitch, 0)),
  //     maxLEDRange,
  //     camResolutionWidth,
  //     camResolutionHeight,
  //     minTargetArea
  //   );

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    if( apriltag.getTargetID() == targetID ){
      forward = apriltag.getCameratoTarget().getX();
      turn = apriltag.getCameratoTarget().getY();
      time = Timer.getFPGATimestamp();
  
      deltaT = lastTime - time;
      deltaForward = forward / deltaT;
      deltaTurn = turn / deltaT;
  
      if( forward > 1 ) sum_Forward += forward;
      if( turn > 1 ) sum_Turn += turn;
  
      output_Forward = kPf * forward + kIf * sum_Forward + kDf * deltaForward;
      output_Turn = kPt * turn + kDt * sum_Turn + kDt * deltaTurn;
  
      drive.arcadeDrive(output_Forward, output_Turn);
    }else{
      drive.arcadeDrive(0, 0.2);
    }

    lastTime = time;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}