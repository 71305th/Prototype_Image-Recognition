// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveConstants {
        // Motors
        public static final int RFmotorID = 1;
        public static final int LFmotorID = 4;
        public static final int RRmotorID = 3;
        public static final int LRmotorID = 2;

        // Encoder
        public static final int kLeftEncoderPort = 3;
        public static final int kRightEncoderPort = 3;
        public static final int kEncoderCPR = 4096;
        public static final double kWheelDiameterMeters = 0.097;
        public static final double kWheelCircumference = kWheelDiameterMeters * Math.PI;
        public static final double kGearRatio = 0.12;
        public static final double kDistancePerPulse = kGearRatio * kWheelCircumference / kEncoderCPR * 100;

        // Camera
        public static final int kCamera1Port = 0;
    }

    public static final class JoystickConstants {
        public static final int joystick_1_Port = 0;
        public static final int leftStick_X = 0;
        public static final int leftStick_Y = 1;
        public static final int rightStick_X = 4;
        public static final int rightStick_Y = 5;
        public static final int trigger_L = 2;
        public static final int trigger_R = 3;
        public static final int btn_A = 1;
        public static final int btn_B = 2;
        public static final int btn_X = 3;
        public static final int btn_Y = 4;
        public static final int btn_LB = 5;
        public static final int btn_RB = 6;
        public static final int btn_LS = 9;  
        public static final int btn_RS = 10;
    }

    public static final class PIDConstants {
        // PID constants
        public static final double kP_foward = 0.3;
        public static final double kI_foward = 0;
        public static final double kD_foward = 0;
        public static final double kP_turn = 0.3;
        public static final double kI_turn = 0;
        public static final double kD_turn = 0;
    }

    public static final class field {
       /**
        *  length: meters 
        * blue aliance (0,0)
        * @param targetID
        * @param position
        */
        public static Map<Integer, Translation2d> fieldmMap = new HashMap<>(){{
            put(1, new Translation2d(15.69085, 1.597025));
            put(2, new Translation2d(15.69085, 4.054475));
            put(3, new Translation2d(15.69085, 6.156325));
            put(4, new Translation2d(16.18615, 6.7262375));
            put(5, new Translation2d(0.3556, 6.7262375));
            put(6, new Translation2d(0.8509, 6.156325));
            put(7, new Translation2d(0.8509, 4.054475));
            put(8, new Translation2d(0.8509, 1.597025));
        }};
    }
}
