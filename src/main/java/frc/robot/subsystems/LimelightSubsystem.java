package frc.robot.subsystems;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase{
    
    NetworkTable mTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTable mTable_2 = NetworkTableInstance.create().getTable("limelight");
    
    NetworkTableEntry tx = mTable.getEntry("tx");
    NetworkTableEntry ty = mTable.getEntry("ty");
    NetworkTableEntry tv = mTable.getEntry("tv");
    NetworkTableEntry ta = mTable.getEntry("ta");

    NetworkTableEntry tx_2 = mTable_2.getEntry("tx");
    NetworkTableEntry ty_2 = mTable_2.getEntry("ty");
    NetworkTableEntry tv_2 = mTable_2.getEntry("tv");
    NetworkTableEntry ta_2 = mTable_2.getEntry("ta");
    
    double mX_1, mY_1, mA_1, mX_2, mY_2, mA_2;
    boolean mV_1, mV_2;

    @Override
    public void periodic(){

        mX_1 = tx.getDouble(0);
        mY_1 = ty.getDouble(0);
        mA_1 = ta.getDouble(0);
        mV_1 = tv.getBoolean(false);

        mX_2 = tx_2.getDouble(0);
        mY_2 = ty_2.getDouble(0);
        mA_2 = ta_2.getDouble(0);
        mV_2 = tv_2.getBoolean(false);
    
        SmartDashboard.putNumber("tx", mX_1);
        SmartDashboard.putNumber("ty", mY_1);
        SmartDashboard.putNumber("ta", mA_1);
        SmartDashboard.putBoolean("tv", mV_1);

        SmartDashboard.putNumber("tx_2", mX_2);
        SmartDashboard.putNumber("ty_2", mY_2);
        SmartDashboard.putNumber("ta_2", mA_2);
        SmartDashboard.putBoolean("tv_2", mV_2);
    }

    /**
     * Cone_1
     */
    public double getX_1(){
        return mX_1;
    }
    
    /**
     * Cone_1
     */
    public double getY_1(){
        return mY_1;
    }

    /**
     * Cone_1
     */
    public double getA_1(){
        return mA_1;
    }

    /**
     * Cone_1
     */
    public boolean getV_1(){
        return mV_1;
    }
    
    /**
     * Cone_2
     */
    public double getX_2(){
        return mX_2;
    }

    /**
     * Cone_2
     */
    public double getY_2(){
        return mY_2;
    }

    /**
     * Cone_2
     */
    public double getA_2(){
        return mA_2;
    }

    /**
     * Cone_2
     */
    public boolean getV_2(){
        return mV_2;
    }
}
