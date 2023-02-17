package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Cones;
import frc.robot.subsystems.LimelightSubsystem;

public class CheckCones extends CommandBase {

    private final LimelightSubsystem limelight;

    public CheckCones(LimelightSubsystem limelight){
        this.limelight = limelight;

        addRequirements(limelight);
    }

    private double tangentCone_1;
    private double tangentCone_2;
    private double limeLightTheta;
    private double limeLightHight;
    private double cone_2hightTheorectic;
    
    private double adjustConstant1;//limelight degrees
    private double adjustConstant2;//meters

    private boolean Cone_2Empty;

    @Override
    public void initialize() {
        Cone_2Empty = false;
    }

    @Override
    public void execute() {
        variableReturntoDefault();

        tangentCone_1 = Math.abs( Math.tan( limelight.getY_1() * Math.PI / 180 + limeLightTheta * Math.PI / 180 + Math.PI / 2 ) );
        tangentCone_2 = Math.abs( Math.tan( limelight.getY_2() * Math.PI / 180 + limeLightTheta * Math.PI / 180 + Math.PI / 2 ) );
        cone_2hightTheorectic = Cones.distenceBetweenCones * tangentCone_2 + Cones.conesHight_1*tangentCone_2/tangentCone_1;

        if( Math.abs( limelight.getY_1() - limelight.getY_2() ) < 2 ){
            if( Math.abs( cone_2hightTheorectic - Cones.conesHight_2 ) < adjustConstant2)Cone_2Empty = true;
            else Cone_2Empty = false;
        }else{
            Cone_2Empty = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
    private void variableReturntoDefault(){
        tangentCone_1 = 0;
        tangentCone_2 = 0;
        limeLightTheta = 0;
        limeLightHight = 0;
        cone_2hightTheorectic = 0;
        
        adjustConstant1 = 2; //limelight degrees
        adjustConstant2 = 0.2; //meters

        Cone_2Empty = false;
    }
}
