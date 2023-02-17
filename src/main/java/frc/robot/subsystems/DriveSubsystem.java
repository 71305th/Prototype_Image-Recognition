// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  // Motors
  WPI_VictorSPX motorRF = new WPI_VictorSPX(DriveConstants.RFmotorID);
  WPI_VictorSPX motorRR = new WPI_VictorSPX(DriveConstants.RRmotorID);
  WPI_VictorSPX motorLF = new WPI_VictorSPX(DriveConstants.LFmotorID);
  WPI_VictorSPX motorLR = new WPI_VictorSPX(DriveConstants.LRmotorID);

  MotorControllerGroup m_leftControllerGroup = new MotorControllerGroup(motorLR, motorLF);
  MotorControllerGroup m_rightControllerGroup = new MotorControllerGroup(motorRR, motorRF);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Set Inverted
    motorRR.setInverted(true);
    motorRF.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void arcadeDrive( double speed, double turn ){
    m_drive.arcadeDrive(speed, turn);
  }

  public void setMotorToZero() {
    motorLF.set(0);
    motorLR.set(0);
    motorRF.set(0);
    motorRF.set(0);
  }
}