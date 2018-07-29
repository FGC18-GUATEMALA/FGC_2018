package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Motores de Lado")
public class MotoresDeLado extends LinearOpMode {

    private DcMotor motorF1, motorF2;
    private DcMotor motorR1, motorR2;

    @Override
    public void runOpMode(){
        motorF1 = hardwareMap.get(DcMotor.class, "motorF1");
        motorF2 = hardwareMap.get(DcMotor.class, "motorF2");
        motorR1 = hardwareMap.get(DcMotor.class, "motorR1");
        motorR2 = hardwareMap.get(DcMotor.class, "motorR2");

        motorF1.setDirection(DcMotor.Direction.REVERSE);
        motorF2.setDirection(DcMotor.Direction.FORWARD);
        motorR1.setDirection(DcMotor.Direction.FORWARD);
        motorR2.setDirection(DcMotor.Direction.REVERSE);

        double ejex, ejey;
        boolean r1, l1;

        waitForStart();
        while (opModeIsActive()){
            ejex = gamepad1.left_stick_x;
            ejey = gamepad1.left_stick_y;

            l1 = gamepad1.left_bumper;
            r1 = gamepad1.right_bumper;

            motorF1.setPower(ejey);
            motorF2.setPower(ejey);
            motorR1.setPower(ejex);
            motorR2.setPower(ejex);

            if (l1){
                motorR1.setPower(1);
                motorR2.setPower(-1);
            }else if (r1){
                motorR1.setPower(-1);
                motorR2.setPower(1);
            }

        }
    }
}
