package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@TeleOp(name="PruebaEleCutz")
public class PruebaElevadorCutz extends LinearOpMode {

    private DcMotor elevador;
    public boolean subir = false, bajar = false;

    public void runOpMode(){
        elevador = hardwareMap.get(DcMotor.class, "elevador");
        waitForStart();

        while (opModeIsActive()){
            subir = gamepad1.dpad_up;
            bajar = gamepad1.dpad_down;

            if(subir){
                elevador.setPower(1);
            }else if (bajar){
                elevador.setPower(-1);
            }else{
                elevador.setPower(0);
            }
        }
    }
}
