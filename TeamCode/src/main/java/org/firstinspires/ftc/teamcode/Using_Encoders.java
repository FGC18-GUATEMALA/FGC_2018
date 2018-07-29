package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Encoder", group = "Encoder")

public class Using_Encoders extends LinearOpMode {
    static final double fuerza = 0.8;
    private DcMotor motor = null;

    @Override
    public void runOpMode(){

        motor  = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int pos1 = 0;
        int pos2 = 0;
        int pos3 = 0;

        waitForStart();
        while (opModeIsActive()){
            motor.setPower(fuerza);
            double fuerzax =0;
            do {
                try {
                    pos1 = motor.getCurrentPosition();
                    TimeUnit.MILLISECONDS.sleep(10);
                    pos2 = motor.getCurrentPosition();
                    TimeUnit.MILLISECONDS.sleep(10);
                    pos3 = pos2 - pos1;
                    telemetry.addData("", ""+pos3);
                    telemetry.update();

                    double x = 40 - pos3;

                    fuerzax = Range.clip(x, 0 , 0.9);
                    motor.setPower(fuerzax);
                }catch (InterruptedException e){
                }
            }while (fuerzax !=9878787);

        }
    }
}
