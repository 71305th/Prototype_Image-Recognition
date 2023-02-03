// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ApriltagSubsystem extends SubsystemBase {

  PhotonCamera pv_cam = new PhotonCamera("photonvision");
  PhotonPipelineResult pv_result = new PhotonPipelineResult();

  PhotonPipelineResult result = pv_cam.getLatestResult();

  //define target
  PhotonTrackedTarget target = result.getBestTarget();

  //define information of the target
  boolean hasTarget = result.hasTargets();
  double yaw = target.getYaw();
  double pitch = target.getPitch();
  double area = target.getArea();
  double skew = target.getSkew();
  int targetID = target.getFiducialId();
  double poseAmbiguity = target.getPoseAmbiguity();

  Transform3d bestCameraToTarget = target.getBestCameraToTarget();
  Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();
  List<TargetCorner> corners = target.getDetectedCorners();

  public ApriltagSubsystem() {}


  // Change this to match the name of your camera
  NetworkTableInstance table;
  PhotonCamera webcam = new PhotonCamera(table, "Webcam");

  // Query the latest result from PhotonVision
  PhotonPipelineResult webcam_result = webcam.getLatestResult();


  @Override
  public void periodic() {
    yaw = target.getYaw();
    pitch = target.getPitch();
    area = target.getArea();
    skew = target.getSkew();
    targetID = target.getFiducialId();
    poseAmbiguity = target.getPoseAmbiguity();

    bestCameraToTarget = target.getBestCameraToTarget();
    corners = target.getDetectedCorners();

    getCameratoTaget();
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


  public void snapshot(){
    pv_cam.takeInputSnapshot();
    pv_cam.takeOutputSnapshot();
  }

  /**
   * 
   * @return
   * x -> front
   * y -> left
   * z -> up
   */
  public Transform3d getCameratoTaget(){
    return bestCameraToTarget;
  }


  public int getTargetID(){
    return targetID;
  }

  /**
   * @return
   * degrees
   */
  public double getYaw(){
    return yaw;
  }

    /**
   * @return
   * degrees
   */
  public double getSkew(){
    return skew;
  }

    /**
   * @return
   * degrees
   */
  public double getPitch(){
    return pitch;
  }

  public boolean hasTarget(){
    return hasTarget;
  }

  public double getPoseAmbiguity(){
    return poseAmbiguity;
  }


}
