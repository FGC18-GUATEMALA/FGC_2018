package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Reto 1 G", group = "Retos")
public class Reto1_29_06_2018 extends LinearOpMode {

    private DcMotor motor;
    private Servo servo;

    @Override
    public void runOpMode(){

        motor = hardwareMap.get(DcMotor.class, "motor");
        servo = hardwareMap.get(Servo.class, "servo");

        motor.setDirection(DcMotor.Direction.REVERSE);

        servo.setPosition(0.8);
        motor.setPower(0);

        double posicion = 0.8;

        waitForStart();
        while (opModeIsActive()){
            boolean botonA = false, botonB = false, left1 = false, right1 = false, botonY;
            botonA = gamepad1.a;
            botonB = gamepad1.b;
            botonY = gamepad1.y;

            left1 = gamepad1.left_bumper;
            right1 = gamepad1.right_bumper;


            if (botonA){
                motor.setPower(0.5);
            }else if(botonB){
                try {
                    motor.setPower(-0.2);
                    TimeUnit.MILLISECONDS.sleep(200);
                    motor.setPower(0.2);
                    TimeUnit.MILLISECONDS.sleep(30);
                }catch (InterruptedException e){}
            }else{
                motor.setPower(0);
            }

            if (left1){
                try {
                    posicion = Range.clip(posicion = posicion - 0.1, 0, 0.6);
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch (InterruptedException e){}
            }else if (right1){
                try {
                    posicion = Range.clip(posicion = posicion + 0.1, 0, 0.6);
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch (InterruptedException e){}
            }
            servo.setPosition(posicion);

            if (botonY){
                motor.setPower(-    0.3);
            }

        }
    }
}
