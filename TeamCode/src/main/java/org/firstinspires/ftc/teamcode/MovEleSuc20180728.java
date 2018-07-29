package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Final 07/28/2018", group = "final")
public class MovEleSuc20180728 extends LinearOpMode {

    private DcMotor motorF1, motorF2;
    private DcMotor motorR1, motorR2;
    private DcMotor elevador1, elevador2;
    private DcMotor motorSuccion;

    public boolean succion = false;
    public boolean elevador = false;

    public void runOpMode(){
        motorSuccion = hardwareMap.get(DcMotor.class, "motorSuccion");

        motorF1 = hardwareMap.get(DcMotor.class, "motorF1");
        motorF2 = hardwareMap.get(DcMotor.class, "motorF2");
        motorR1 = hardwareMap.get(DcMotor.class, "motorR1");
        motorR2 = hardwareMap.get(DcMotor.class, "motorR2");

        motorF1.setDirection(DcMotor.Direction.REVERSE);
        motorF2.setDirection(DcMotor.Direction.FORWARD);
        motorR1.setDirection(DcMotor.Direction.FORWARD);
        motorR2.setDirection(DcMotor.Direction.REVERSE);

        elevador1 = hardwareMap.get(DcMotor.class, "elevador1");
        elevador2 = hardwareMap.get(DcMotor.class, "elevador2");
        elevador1.setDirection(DcMotor.Direction.REVERSE);
        elevador2.setDirection(DcMotor.Direction.FORWARD);



        double ejex, ejey, ejexFinal, ejeyFinal, left, right;
        boolean r1, l1, a, b, y, up, down;


        waitForStart();
        while (opModeIsActive()){

            ejex = gamepad1.left_stick_x;
            ejey = gamepad1.left_stick_y;
            a = gamepad1.a;
            b = gamepad1.b;
            y = gamepad1.y;
            left = gamepad1.left_trigger;
            right = gamepad1.right_trigger;

            up = gamepad1.dpad_up;
            down = gamepad1.dpad_down;

            ejexFinal = Range.clip(ejex, -0.6, 0.6);
            ejeyFinal = Range.clip(ejey, -0.6, 0.6);

            l1 = gamepad1.left_bumper;
            r1 = gamepad1.right_bumper;

            motorF1.setPower(ejeyFinal);
            motorF2.setPower(ejeyFinal);
            motorR1.setPower(ejexFinal);
            motorR2.setPower(ejexFinal);

            //R1 para vueltas a la izquierda, L1 para vueltas a la derecha
            if (r1){
                motorR1.setPower(0.6);
                motorR2.setPower(-0.6);
                motorF1.setPower(-0.6);
                motorF2.setPower(0.6);
            }else if (l1){
                motorR1.setPower(-0.6);
                motorR2.setPower(0.6);
                motorF1.setPower(0.6);
                motorF2.setPower(-0.6);
            }

            if (a){
                succion = true;
            }
            if (b){
                succion = false;
                motorSuccion.setPower(-1);
            }

            if (succion){
                motorSuccion.setPower(1);
            }else{
                motorSuccion.setPower(0);
            }

            //Elevador
            if (up){
                elevador1.setPower(0.6);
                elevador2.setPower(0.6);
            }
            else if(down){
                elevador1.setPower(-0.6);
                elevador2.setPower(-0.6);
            }else{
                elevador1.setPower(0);
                elevador2.setPower(0);
            }
            if (left > 0.2){
                elevador2.setPower(0.6);
            }
            if (right > 0.2){
                elevador1.setPower(0.6);
            }

        }
    }
}
