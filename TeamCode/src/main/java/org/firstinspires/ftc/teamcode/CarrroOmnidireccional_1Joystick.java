package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.util.concurrent.TimeUnit;

@Disabled
@TeleOp(name = "CarrroOmnidireccional_1Joystick-Gabriel", group = "Omnidireccional")
public class CarrroOmnidireccional_1Joystick extends LinearOpMode {

    private DcMotor motorX1;
    private DcMotor motorX2;
    private DcMotor motorY1;
    private DcMotor motorY2;
//    private Servo servo;

    @Override
    public void runOpMode(){

        motorX1 = hardwareMap.get(DcMotor.class, "motorX1");
        motorX2 = hardwareMap.get(DcMotor.class, "motorX2");
        motorY1 = hardwareMap.get(DcMotor.class, "motorY1");
        motorY2 = hardwareMap.get(DcMotor.class, "motorY2");

//        servo = hardwareMap.get(Servo.class, "servo");

        motorX1.setDirection(DcMotor.Direction.FORWARD);
        motorY1.setDirection(DcMotor.Direction.FORWARD);

        motorX2.setDirection(DcMotor.Direction.REVERSE);
        motorY2.setDirection(DcMotor.Direction.REVERSE);

//        servo.setPosition(0.3);

        boolean rapido = false;

        double poderGarra =0;

        waitForStart();
        while (opModeIsActive()){

            double ejeX = 0;
            double ejeY = 0;
            double leftPower = 0;
            double rightPower = 0;


            boolean botonA = false;
            boolean botonB = false;
            boolean bumberRight = false;
            boolean bumberLeft = false;

            boolean botonY = false;
            boolean botonX = false;

            boolean botonCaballoR = gamepad2.dpad_right;
            boolean botonCaballoL = gamepad2.dpad_left;

            float R2, L2;

            botonY = gamepad1.y;
            botonX = gamepad1.x;
            botonA = gamepad1.a;
            botonB = gamepad1.b;
            bumberRight = gamepad1.right_bumper;
            bumberLeft = gamepad1.left_bumper;

            R2 = gamepad1.right_trigger;
            L2 = gamepad1.right_trigger;

            if (botonA){
                rightPower = 1;
                leftPower = 1;
            }
            else if (botonB){
                rightPower = -1;
                leftPower = -1;

            }
            else if (bumberRight){
                motorX1.setPower(1);
                motorX2.setPower(-1);
            }
            else if (bumberLeft){
                motorX1.setPower(-1);
                motorX2.setPower(1);
            }
            else{
                ejeX = gamepad1.left_stick_x;
                ejeY = gamepad1.left_stick_y;

                rightPower = Range.clip(ejeX + ejeY, -1, 1);
                leftPower = Range.clip(ejeX -  ejeY, -1, 1);

                rightPower = rightPower * -1;
            }

            if (R2 > 1){
                rapido = true;
            }else if (L2 > 1){
                rapido = false;
            }

            if (rapido){
                motorX1.setPower(rightPower * .8);
                motorX2.setPower(rightPower * .8);
                motorY1.setPower(leftPower * .8);
                motorY2.setPower(leftPower * .8);
            }else if (!rapido){
                motorX1.setPower(rightPower * .3);
                motorX2.setPower(rightPower * .3);
                motorY1.setPower(leftPower * .3);
                motorY2.setPower(leftPower * .3);
            }
/*
            if (botonY){
                poderGarra = Range.clip(poderGarra + 0.2, 0, 1);
                try {
                    TimeUnit.MILLISECONDS.sleep(150);
                }catch (InterruptedException e){
                }

            }
            else if (botonX){
                poderGarra = Range.clip(poderGarra -0.2, 0, 1);

            }

            servo.setPosition(poderGarra);
*/
            if (botonCaballoR){

                try {
                    motorX2.setPower(-0.5);
                    motorY2.setPower(0.5);
                    motorX1.setPower(-0.5);
                    motorY1.setPower(0.5);

                    TimeUnit.MILLISECONDS.sleep(300);

                    motorX1.setPower(0.5);
                    motorX2.setPower(0.5);
                    motorY1.setPower(0.5);
                    motorY2.setPower(0.5);

                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                }

            }else if (botonCaballoL){
                try {
                    motorX1.setPower(0.5);
                    motorY1.setPower(-0.5);
                    motorX2.setPower(0.5);
                    motorY2.setPower(-0.5);

                    TimeUnit.MILLISECONDS.sleep(300);
                    motorX1.setPower(0.5);
                    motorX2.setPower(0.5);
                    motorY1.setPower(0.5);
                    motorY2.setPower(0.5);

                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                }
            }

        }
    }
}
