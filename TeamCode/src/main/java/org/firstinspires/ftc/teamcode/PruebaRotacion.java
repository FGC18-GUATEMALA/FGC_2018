package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Prueba Rotacion")
public class PruebaRotacion extends LinearOpMode {

    private DcMotor rotacion;

    public void runOpMode(){

        rotacion = hardwareMap.get(DcMotor.class, "rotacion");
        rotacion.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotacion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int nuevaPosicion = 0 ;

        waitForStart();
        while (opModeIsActive()){
            boolean rotarAtras = gamepad1.dpad_left;
            boolean rotarAdelante = gamepad1.dpad_right;

            int posicion = rotacion.getCurrentPosition();

            if (rotarAtras){
                nuevaPosicion = posicion - 50;
            }
            if (rotarAdelante){
                nuevaPosicion = posicion + 50;
            }

            rotacion.setTargetPosition(nuevaPosicion);
            rotacion.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotacion.setPower(1);

            telemetry.addData("posicion: ", ""+posicion);
            telemetry.update();

        }
    }
}
