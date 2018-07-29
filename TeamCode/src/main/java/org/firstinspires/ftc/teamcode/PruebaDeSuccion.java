package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "PruebaDeSuccion")
public class PruebaDeSuccion extends LinearOpMode {

    private DcMotor motor;
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "motor");
        double fuerza = 0;

        waitForStart();
        while (opModeIsActive()){
            fuerza = gamepad1.right_stick_y;
            motor.setPower(fuerza);

        }
    }
}
