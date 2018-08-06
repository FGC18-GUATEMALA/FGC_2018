package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "Prototipo2", group = "final")
public class Prototipo2 extends LinearOpMode {

    private DcMotor motorF1, motorF2;
    private DcMotor motorR1, motorR2;
    private DcMotor elevador;
    private DcMotor motorRight, motorLeft, rotacion;
    private CRServo elevacionGarra, eolica, apretarGarra;

    public double ejexFinal = 0, ejeyFinal = 0;
    public int cont = 0;

    public void runOpMode(){

        motorF1 = hardwareMap.get(DcMotor.class, "motorF1"); //Motor para Adelante en el Frente
        motorF2 = hardwareMap.get(DcMotor.class, "motorF2"); //Motor para adelante en atras
        motorR1 = hardwareMap.get(DcMotor.class, "motorR1"); //Motor para lado colocado adelante
        motorR2 = hardwareMap.get(DcMotor.class, "motorR2"); //Motor para lado colocado atras

        motorRight = hardwareMap.get(DcMotor.class, "motorRight"); //Succion de lado derecho
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft"); //Succion de lado izquierdo

        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLeft.setDirection(DcMotor.Direction.FORWARD);

        motorF1.setDirection(DcMotor.Direction.REVERSE);
        motorF2.setDirection(DcMotor.Direction.FORWARD);
        motorR1.setDirection(DcMotor.Direction.FORWARD);
        motorR2.setDirection(DcMotor.Direction.REVERSE);

        elevador = hardwareMap.get(DcMotor.class, "elevador"); //Parar motor Elevador y setear encoders
        elevador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevador.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rotacion = hardwareMap.get(DcMotor.class, "rotacion"); //Parar motor Rotacion y setear encoders
        rotacion.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rotacion.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevacionGarra = hardwareMap.get(CRServo.class, "elevacionGarra"); //Servo para subir/bajar garra
        elevacionGarra.setDirection(CRServo.Direction.FORWARD);
        eolica = hardwareMap.get(CRServo.class, "eolica"); //Servo para dar vuelta a eolica
        eolica.setDirection(CRServo.Direction.FORWARD);
        apretarGarra = hardwareMap.get(CRServo.class, "apretarGarra"); //Servo para apretar la garra
        apretarGarra.setDirection(CRServo.Direction.FORWARD);

        double ejex, ejey, fuerzaRotacion, ajusteVelocidad = 1, power = 1;
        int posicion2, rotacion2;
        boolean r1, l1, a, b, up, down, eleGarra, bajaGarra, apreGarra, solGarra, eol, lento = true, rapido = false, y;

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

            eol = gamepad2.y; //Accion de eolica en el boton Y
            apreGarra = gamepad2.left_bumper; //Accion de apretar garra en el boton L1
            solGarra = gamepad2.right_bumper; //Accion de soltar garra en el boton R1
            eleGarra = gamepad2.a;  //Accion de elevar garra en el boton A
            bajaGarra = gamepad2.b; //Accion de bajar garra en el boton B

            ejex = gamepad1.left_stick_x; //Detectar posicion del joystick izquierdo en X
            ejey = gamepad1.left_stick_y; //Detectar posicion del joystick izquierdo en Y
            a = gamepad1.a; //Iniciar succion
            b = gamepad1.b; //Revertit succion
            y = gamepad1.y; //Parar succion

            fuerzaRotacion= gamepad2.right_stick_y; //Activar la rotacion de la succion
            fuerzaRotacion = fuerzaRotacion * 100; //Volver el valor a un rango de 100
            rotacion2 = (int) fuerzaRotacion; //Volver el valor double a integer

            int posicionRotacion = rotacion.getCurrentPosition(); //Obtener posicion de la succion
            int posicionElevador = elevador.getCurrentPosition(); //Obtener posicion del elevador

            //Aumentar la posicion de la succion con el Joystick
            int posicionRotacionNueva = posicionRotacion + rotacion2;

            elevador.setTargetPosition(posicionRotacionNueva); //Set target para la nueva posicion
            elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Mandar el motor a la posicion
            elevador.setPower(power); //Mandar el motor a la posicion con cierta fuerza

            up = gamepad2.dpad_up; //Elevador hacia arriba
            down = gamepad2.dpad_down; //Elevador hacia abajo


            /* Al momento de que quiera ir rapido el ajuste de velocidad se aumenta el cual se usa para
             * controlar la velocidad de giro, se aumenta el rango de los valores que se mandan a los
             * motores de movimiento. Cuando se quiere ir lento el ajuste de velocidad y el rango de los
             * valores se disminuye para mejor precision*/

            if(rapido){
                ajusteVelocidad = 1.8; //velocidad con la que girara el robot
                ejexFinal = Range.clip(ejex , -1, 1); //rango para el movimiento en X
                ejeyFinal = Range.clip(ejey, -1, 1); //rango para el movimiento en Y
            }
            if(lento){
                ajusteVelocidad = 1.0;
                ejexFinal = Range.clip(ejex, -0.6, 0.6);
                ejeyFinal = Range.clip(ejey, -0.6, 0.6);
            }

            l1 = gamepad1.left_bumper; //Girar hacia la izquierda
            r1 = gamepad1.right_bumper; //Girar hacia la derecha

            /* Mandar los valores anteriormente limitados o amplificados hacia los motores usados
             * para el movimiento */
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

            //Activar Succion
            if (a){
                motorLeft.setPower(1);
                motorRight.setPower(1);
            }
            //Revertir succion
            if (b){
                motorLeft.setPower(-1);
                motorRight.setPower(-1);
            }
            //Parar succion
            if(y){
                motorLeft.setPower(0);
                motorRight.setPower(0);
            }

            //Elevador hacia arriba con Encoder
            if (up){
                posicion2 = posicionElevador + 80; //determinar la siguiente posicion deseada
                elevador.setTargetPosition(posicion2); //setear el target
                elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Mandarlo a la posicion
                elevador.setPower(power); //Ponerle la fuerza para que realice la accion
            }
            else if(down){
                posicion2 = posicionElevador - 80;
                elevador.setTargetPosition(posicion2);
                elevador.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevador.setPower(power);
            }

            //Apretar la garra
            if (apreGarra){
                apretarGarra.setPower(0.5);
            }
            //Soltar la garra
            else if(solGarra){
                apretarGarra.setPower(-0.5);
            }
            //No hacer nada
            else{
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
