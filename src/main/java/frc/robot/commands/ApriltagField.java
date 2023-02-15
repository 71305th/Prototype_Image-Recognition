package frc.robot.commands;

import javax.swing.text.Position;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.field;
import frc.robot.subsystems.ApriltagSubsystem;

public class ApriltagField extends CommandBase{

    private ApriltagSubsystem apriltag;

    public ApriltagField(ApriltagSubsystem apriltag){
        apriltag = this.apriltag;
        addRequirements(apriltag);
    }
    /**
     * In meters
     */
    public Translation2d position = new Translation2d();
    int targetID;
    Translation2d IDposition;
    Transform3d error;
    double kx = 0.1;
    double ky = 0.1;

    @Override
    public void initialize() {
        targetID = 0;
    }

    @Override
    public void execute() {
        targetID = apriltag.getTargetID();

       if(targetID != 0){
       IDposition =  field.fieldmMap.get(targetID);
       error = apriltag.getCameratoTarget();

       position = new Translation2d(kx*(IDposition.getX() + error.getX()), ky*(IDposition.getY() + error.getY()));
       }
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }


    
}
