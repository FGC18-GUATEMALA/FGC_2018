package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "MovEleSuc20180728", group = "final")
public class MovEleSuc20180728 extends LinearOpMode {

    private DcMotor motorF1, motorF2;
    private DcMotor motorR1, motorR2;
    private DcMotor elevador1, elevador2;
    private DcMotor motorRight, motorLeft;
    private CRServo rotacionRight, rotacionLeft, elevacionGarra, eolica, apretarGarra;

    public boolean succion = false;
    public boolean elevador = false;
    public double ejexFinal = 0, ejeyFinal = 0;
    public int cont = 0;

    public void runOpMode(){

        motorF1 = hardwareMap.get(DcMotor.class, "motorF1");
        motorF2 = hardwareMap.get(DcMotor.class, "motorF2");
        motorR1 = hardwareMap.get(DcMotor.class, "motorR1");
        motorR2 = hardwareMap.get(DcMotor.class, "motorR2");

        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");

        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setDirection(DcMotor.Direction.FORWARD);

        motorF1.setDirection(DcMotor.Direction.REVERSE);
        motorF2.setDirection(DcMotor.Direction.FORWARD);
        motorR1.setDirection(DcMotor.Direction.FORWARD);
        motorR2.setDirection(DcMotor.Direction.REVERSE);

        elevador1 = hardwareMap.get(DcMotor.class, "elevador1");
        elevador2 = hardwareMap.get(DcMotor.class, "elevador2");
        elevador1.setDirection(DcMotor.Direction.REVERSE);
        elevador2.setDirection(DcMotor.Direction.FORWARD);

        rotacionLeft = hardwareMap.get(CRServo.class, "rotacionLeft");
        rotacionRight = hardwareMap.get(CRServo.class, "rotacionRight");
        rotacionLeft.setDirection(CRServo.Direction.FORWARD);
        rotacionRight.setDirection(CRServo.Direction.REVERSE);

        elevacionGarra = hardwareMap.get(CRServo.class, "elevacionGarra");
        elevacionGarra.setDirection(CRServo.Direction.FORWARD); //PENDIENTE
        eolica = hardwareMap.get(CRServo.class, "eolica");
        eolica.setDirection(CRServo.Direction.FORWARD); //PENDIENTE
        apretarGarra = hardwareMap.get(CRServo.class, "apretarGarra");
        apretarGarra.setDirection(CRServo.Direction.FORWARD); //PENDIENTE

        double ejex, ejey, left, right, rotacion, rotacionFinal, ajusteVelocidad = 1;
        boolean r1, l1, a, b, up, down, eleGarra, bajaGarra, apreGarra, solGarra, eol, lento = true, rapido = false;

        waitForStart();
        while (opModeIsActive()){

            //Inicio de garra abajo
            if (cont == 0){
                try {
                    elevacionGarra.setPower(-0.6);
                    TimeUnit.MILLISECONDS.sleep(400);
                    cont ++;
                }catch (InterruptedException e){}
            }

            lento = gamepad1.dpad_left;
            rapido = gamepad1.dpad_right;

            boolean y = false;
            apreGarra = false;
            solGarra = false;
            eol = false;

            eol = gamepad2.y;
            apreGarra = gamepad2.left_bumper;
            solGarra = gamepad2.right_bumper;
            eleGarra = gamepad2.a;
            bajaGarra = gamepad2.b;

            ejex = gamepad1.left_stick_x;
            ejey = gamepad1.left_stick_y;
            a = gamepad1.a;
            b = gamepad1.b;
            y = gamepad1.y;
            left = gamepad2.left_trigger;
            right = gamepad2.right_trigger;
            rotacion = gamepad2.right_stick_y;
            rotacion = rotacion *-1;

            rotacionFinal = Range.clip(rotacion *-1, -0.5, 0.5);
            rotacionRight.setPower(rotacionFinal);
            rotacionLeft.setPower(rotacionFinal);

            up = gamepad2.dpad_up;
            down = gamepad2.dpad_down;

            if(rapido){
                ajusteVelocidad = 1.8;
                ejexFinal = Range.clip(ejex , 1, 1);
                ejeyFinal = Range.clip(ejey, 1, 1);
            }
            if(lento){
                ajusteVelocidad = 1.0;
                ejexFinal = Range.clip(ejex, -0.6, 0.6);
                ejeyFinal = Range.clip(ejey, -0.6, 0.6);
            }

            l1 = gamepad1.left_bumper;
            r1 = gamepad1.right_bumper;


            motorF1.setPower(ejeyFinal);
            motorF2.setPower(ejeyFinal);
            motorR1.setPower(ejexFinal);
            motorR2.setPower(ejexFinal);

            //R1 para vueltas a la izquierda, L1 para vueltas a la derecha
            if (r1){
                motorR1.setPower(0.4 * ajusteVelocidad);
                motorR2.setPower(-0.4 * ajusteVelocidad);
                motorF1.setPower(-0.4 * ajusteVelocidad);
                motorF2.setPower(0.4 * ajusteVelocidad);
            }else if (l1){
                motorR1.setPower(-0.4 * ajusteVelocidad);
                motorR2.setPower(0.4 * ajusteVelocidad);
                motorF1.setPower(0.4 * ajusteVelocidad);
                motorF2.setPower(-0.4 * ajusteVelocidad);
            }

            if (a){
                motorLeft.setPower(1);
                motorRight.setPower(1);
            }
            if (b){
                motorLeft.setPower(-1);
                motorRight.setPower(-1);
            }
            if(y){
                motorLeft.setPower(0);
                motorRight.setPower(0);
            }

            if (succion){
                motorRight.setPower(1);
                motorLeft.setPower(1);
            }

            //Elevador
            if (up){
                elevador1.setPower(0.8);
                elevador2.setPower(0.8);
            }
            else if(down){
                elevador1.setPower(-0.4);
                elevador2.setPower(-0.4);
            }else{
                elevador1.setPower(0);
                elevador2.setPower(0);
            }
            if (left > 0.2){
                elevador2.setPower(0.8);
            }
            if (right > 0.2){
                elevador1.setPower(0.8);
            }

            //Funcion de las herramientas
            if (apreGarra){
                apretarGarra.setPower(0.5);
            }else if(solGarra){
                apretarGarra.setPower(-0.5);
            }else{
                apretarGarra.setPower(0);
            }

            if(eol){
                eolica.setPower(-1);
            }else{
                eolica.setPower(0);
            }

            if (eleGarra){
                elevacionGarra.setPower(0.2);
            }else if(bajaGarra){
                elevacionGarra.setPower(-0.6);
            }else{
                elevacionGarra.setPower(0);
            }

        }
    }
}
