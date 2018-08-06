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
    private DcMotor elevador;
    private DcMotor motorRight, motorLeft, rotacion;
    private CRServo elevacionGarra, eolica, apretarGarra;

    public boolean succion = false;
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

        elevador = hardwareMap.get(DcMotor.class, "elevador");
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rotacion = hardwareMap.get(DcMotor.class, "rotacion");
        rotacion.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotacion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevacionGarra = hardwareMap.get(CRServo.class, "elevacionGarra");
        elevacionGarra.setDirection(CRServo.Direction.FORWARD); //PENDIENTE
        eolica = hardwareMap.get(CRServo.class, "eolica");
        eolica.setDirection(CRServo.Direction.FORWARD); //PENDIENTE
        apretarGarra = hardwareMap.get(CRServo.class, "apretarGarra");
        apretarGarra.setDirection(CRServo.Direction.FORWARD); //PENDIENTE

        double ejex, ejey, fuerzaRotacion, ajusteVelocidad = 1, power = 1;
        int posicion2, rotacion2;
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

            fuerzaRotacion= gamepad2.right_stick_y;
            fuerzaRotacion = fuerzaRotacion * 100;
            rotacion2 = (int) fuerzaRotacion;

            int posicionRotacion = rotacion.getCurrentPosition();
            int posicionElevador = elevador.getCurrentPosition();

            int posicionRotacionNueva = posicionRotacion + rotacion2;

            elevador.setTargetPosition(posicionRotacionNueva);
            elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevador.setPower(power);

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
                posicion2 = posicionElevador + 80;
                elevador.setTargetPosition(posicion2);
                elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevador.setPower(power);
            }
            else if(down){
                posicion2 = posicionElevador - 80;
                elevador.setTargetPosition(posicion2);
                elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevador.setPower(power);
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
