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

        waitForStart();
        while (opModeIsActive()){
            double rotar = gamepad1.right_stick_y;
            rotar = rotar * 100;
            int rotar2 = (int) rotar;

            int posicion = rotacion.getCurrentPosition();
            int nuevaPosicion = posicion + rotar2;

            rotacion.setTargetPosition(nuevaPosicion);
            rotacion.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rotacion.setPower(1);

            telemetry.addData("posicion: ", ""+posicion);
            telemetry.update();

        }
    }
}
