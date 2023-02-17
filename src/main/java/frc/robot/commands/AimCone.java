package frc.robot.commands;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;

public class AimCone extends CommandBase{
    private LimelightSubsystem limelight;

    public AimCone(LimelightSubsystem limelight){
        limelight = this.limelight;
        addRequirements(limelight);
    }

    private Translation3d mResult = new Translation3d(0, 0, 0);
    private double mTx;
    private double mTy;
    private double mDis;
    private double kX = 0.1;
    private double kY = 0.1;
    private double kZ = 0.1;

    @Override
    public void initialize() {
        mResult = new Translation3d(0,0,0);
    }

    @Override
    public void execute() {
        mTx = limelight.getX_2();
        mTy = limelight.getY_2();
        mDis = Math.sqrt(Math.pow(mTx, 2) + Math.pow(mTy, 2));

        mResult = new Translation3d( kX * mTx, kY * mTy, kZ * mDis);
    }

    @Override
    public void end(boolean interrupted) {
        
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }

}
