package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "CarroOmnidireccional", group = "Carro")
public class CarroOmnidireccional extends LinearOpMode {

    private DcMotor motorX1;
    private DcMotor motorX2;
    private DcMotor motorY1;
    private DcMotor motorY2;

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

            double potenciaX = 0, potenciaY = 0;
            boolean botonA = false;
            boolean botonB = false;
            boolean bumberRight = false;
            boolean bumberLeft = false;

            botonA = gamepad1.a;
            botonB = gamepad1.b;

            bumberLeft = gamepad1.left_bumper;
            bumberRight = gamepad1.right_bumper;

            if (botonA){
                potenciaX = 1;
                potenciaY = 1;
            }
            else if (botonB){
                potenciaX = -1;
                potenciaY = -1;
            }
            else if (bumberLeft){
                motorX1.setPower(0.5);
                motorX2.setPower(-0.5);
            }
            else if (bumberRight){
                motorX1.setPower(-0.5);
                motorX2.setPower(0.5);
            }
            else{
                potenciaX = gamepad1.left_stick_y;
                potenciaY = gamepad1.right_stick_y;

                potenciaX = potenciaX *-1;
                potenciaY = potenciaY *-1;

            }

            potenciaX = potenciaX * 0.3;
            potenciaY = potenciaY * 0.3;

            motorX1.setPower(potenciaX);
            motorX2.setPower(potenciaX);
            motorY1.setPower(potenciaY);
            motorY2.setPower(potenciaY);
        }
    }

}
