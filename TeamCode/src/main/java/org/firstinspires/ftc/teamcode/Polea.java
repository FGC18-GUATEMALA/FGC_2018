package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Polea", group = "Polea")
public class Polea extends LinearOpMode {

    private DcMotor motor;
    private Servo servo;

     @Override
    public void runOpMode(){
         boolean encendido = false;

         waitForStart();
         while (opModeIsActive()){
             motor = hardwareMap.get(DcMotor.class, "motor");
             servo = hardwareMap.get(Servo.class, "servo");


             boolean botonA = gamepad1.a;
             boolean botonB = gamepad1.b;
             boolean botonY = gamepad1.y;

             if (botonA){
                 encendido = true;
             }else if (botonB){
                 encendido = false;
             }

             if (encendido){
                 motor.setPower(0.3);
             }else{
                 motor.setPower(0);
             }
             if (botonY){
                 motor.setPower(-0.1);
             }
         }
     }
}
