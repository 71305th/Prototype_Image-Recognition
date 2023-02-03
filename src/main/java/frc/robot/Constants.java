// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /**
     * This class contains : 
     * 
     * <ul>
     *  <li> Four Direction MotorID
     * </ul>
     **/
    public static final class DriveConstants {
        public static final int RFmotorID = 4;
        public static final int LFmotorID = 2;
        public static final int RRmotorID = 3;
        public static final int LRmotorID = 5;
    }

    /**
     * This class contains : 
     * 
     * <ul>
     *  <li> joystick_1_Port
     *  <li> left and rightStick_X, Y
     *  <li> trigger_L, R
     *  <li> btn_A, B, X, Y
     *  <li> btn_LB, RB, LS, RS
     * </ul>
     **/
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
}
