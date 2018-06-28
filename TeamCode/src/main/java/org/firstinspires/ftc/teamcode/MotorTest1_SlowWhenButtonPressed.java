package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "MotorTest1", group = "Tests")
public class MotorTest1_SlowWhenButtonPressed extends LinearOpMode {

    private DcMotor motor1 = null;

    @Override
    public void runOpMode(){

        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        waitForStart();

        while (opModeIsActive()){

            double speed1 = 0, speed2 = 0;
            boolean buttonA = false;

            speed1 = gamepad1.left_stick_y;
            buttonA = gamepad1.a;

            if (buttonA == true){
                speed2 = speed1 * 0.3;
            }else{
                speed2 = speed1;
            }

            motor1.setPower(speed2);

            telemetry.addData("Motor", ""+speed2);
        }
    }
}
