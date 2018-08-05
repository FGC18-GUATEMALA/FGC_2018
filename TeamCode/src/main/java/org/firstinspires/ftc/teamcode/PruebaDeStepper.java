package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.concurrent.TimeUnit;

@Autonomous(name = "PruebaDeStepper")
public class PruebaDeStepper extends LinearOpMode {

    private DcMotor stepper;

    public double power = 1;
    public int distance = 260;

    @Override
    public void runOpMode(){

        stepper = hardwareMap.get(DcMotor.class, "stepper");

        stepper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()){
            try {
                DriveForwardDistance(power, distance);
                TimeUnit.SECONDS.sleep(6);
            }catch (InterruptedException e){}
        }
    }

    public void DriveForwardDistance (double power, int distance){
        int texto;
        stepper.setMode(DcMotor.RunMode.RESET_ENCODERS);
        stepper.setTargetPosition(distance);
        stepper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        stepper.setPower(power);



        while (stepper.isBusy()){
            texto = stepper.getCurrentPosition();
            telemetry.addData("Posicion", ""+ texto);
            telemetry.update();

        }

    }
}
