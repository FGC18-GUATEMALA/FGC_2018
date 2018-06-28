package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "CarrroOmnidireccional_1Joystick", group = "Omnidireccional")
public class CarrroOmnidireccional_1Joystick extends LinearOpMode {

    private DcMotor motorX1;
    private DcMotor motorX2;
    private DcMotor motorY1;
    private DcMotor motorY2;

    @Override
    public void runOpMode(){

        motorX1 = hardwareMap.get(DcMotor.class, "motorX1");
        motorX2 = hardwareMap.get(DcMotor.class, "motorX2");
        motorY1 = hardwareMap.get(DcMotor.class, "motorY1");
        motorY2 = hardwareMap.get(DcMotor.class, "motorY2");

        motorX1.setDirection(DcMotor.Direction.FORWARD);
        motorY1.setDirection(DcMotor.Direction.FORWARD);

        motorX2.setDirection(DcMotor.Direction.REVERSE);
        motorY2.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        while (opModeIsActive()){

            double ejeX = 0;
            double ejeY = 0;
            

            ejeX = gamepad1.left_stick_x;
            ejeY = gamepad1.left_stick_y;



        }
    }
}
