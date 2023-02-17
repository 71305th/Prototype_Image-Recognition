// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.Pair;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  // Motors
  CANSparkMax motorRF = new CANSparkMax(DriveConstants.RFmotorID, MotorType.kBrushless);
  CANSparkMax motorRR = new CANSparkMax(DriveConstants.RRmotorID, MotorType.kBrushless);
  CANSparkMax motorLF = new CANSparkMax(DriveConstants.LFmotorID, MotorType.kBrushless);
  CANSparkMax motorLR = new CANSparkMax(DriveConstants.LRmotorID, MotorType.kBrushless);

  MotorControllerGroup m_leftControllerGroup = new MotorControllerGroup(motorLR, motorLF);
  MotorControllerGroup m_rightControllerGroup = new MotorControllerGroup(motorRR, motorRF);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);

  // Encoder
  WPI_CANCoder leftEncoder = new WPI_CANCoder(DriveConstants.kLeftEncoderPort);
  WPI_CANCoder rightEncoder = new WPI_CANCoder(DriveConstants.kRightEncoderPort);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Set Inverted
    motorLF.setInverted(true);
    motorLR.setInverted(true);
    motorRF.setInverted(false);
    motorRR.setInverted(false);
    
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void arcadeDrive( double speed, double turn ){
    m_drive.arcadeDrive(speed * 0.75, -turn * 0.85);
  }

  public void setMotorToZero() {
    motorLF.set(0);
    motorLR.set(0);
    motorRF.set(0);
    motorRF.set(0);
  }

  /**
   * @return first -> leftPos
   *      <li>second -> rightPos
   */
  public Pair<Double, Double> getTwoRelativeEncoderData(){
    double DPP = DriveConstants.kDistancePerPulse;
    return new Pair<>(leftEncoder.getPosition() * DPP, rightEncoder.getPosition() * DPP); 
  }

  public void resetEncoders(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }
}