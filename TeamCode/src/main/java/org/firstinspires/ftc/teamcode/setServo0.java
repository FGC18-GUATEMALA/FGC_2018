package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "SetServo")
public class setServo0 extends LinearOpMode {

    private CRServo servoLeft, servoRight ;

    public void runOpMode(){

        servoLeft = hardwareMap.get(CRServo.class, "servoLeft");
        servoRight = hardwareMap.get(CRServo.class, "servoRight");

        servoLeft.setDirection(CRServo.Direction.FORWARD);
        servoRight.setDirection(CRServo.Direction.REVERSE);

        boolean a, b, y;

        waitForStart();


        while (opModeIsActive()){

            a = gamepad1.a;
            b = gamepad1.b;
            y = gamepad1.y;

            if (a){
                servoLeft.setPower(1);
                servoRight.setPower(1);
            }
            if (b){
                servoLeft.setPower(-1);
                servoRight.setPower(-1);
            }
            if(y){
                servoLeft.setPower(0);
                servoRight.setPower(0);
            }


        }
    }
}
