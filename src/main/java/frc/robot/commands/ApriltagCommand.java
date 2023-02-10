// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.ApriltagSubsystem;
import frc.robot.subsystems.DriveSubsystem;

import org.photonvision.SimVisionSystem;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  // Variables
  double forward;
  double turn;
  double time;  
  double lastTime;
  double deltaT;
  double deltaForward;
  double deltaTurn;
  double sum_Forward;
  double sum_Turn;
  double output_Forward;
  double output_Turn;

  // Normal Target
  boolean hasTarget;
  Transform3d targetToCamera;
 
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
  public void initialize() {
    drive.resetEncoder();
  }

  @Override
  public void execute() {
    hasTarget = apriltag.hasTarget();
    targetToCamera = apriltag.getCameratoTarget();
    forward = 0;
    turn = 0;

    if( apriltag.getTargetID() == targetID ){
      forward = targetToCamera.getX() - 0.36;
      turn = -targetToCamera.getY();
      time = Timer.getFPGATimestamp();
  
      deltaT = lastTime - time;
      deltaForward = forward / deltaT;
      deltaTurn = turn / deltaT;
  
      if( forward < 1 ) sum_Forward += forward;
      if( turn < 0.3 ) sum_Turn += turn;
  
      // output_Forward = PIDConstants.kP_foward * forward + PIDConstants.kI_foward * sum_Forward + PIDConstants.kD_foward * deltaForward;
      output_Turn = -1 * (PIDConstants.kP_turn * turn + PIDConstants.kI_turn * sum_Turn + PIDConstants.kD_turn * deltaTurn);

      // SmartDashboard.putNumber("X", forward);
      // SmartDashboard.putNumber("Y", turn);
      // SmartDashboard.putNumber("output_forward", output_Forward);
      // SmartDashboard.putNumber("output_turn", output_Turn);
  
      drive.arcadeDrive(output_Forward, output_Turn);
    }else{
      drive.arcadeDrive(0, 0.3);
    }

    // SmartDashboard.putBoolean("HasTarget", hasTarget);
    // SmartDashboard.putNumber("LeftDitance", drive.getTwoRelativeEncoderData().getFirst());
    // SmartDashboard.putNumber("LeftDitance", drive.getTwoRelativeEncoderData().getSecond());
    lastTime = time;
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}