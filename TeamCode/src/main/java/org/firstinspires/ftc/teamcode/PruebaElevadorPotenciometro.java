package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name = "ElevadorPotenciometro")
public class PruebaElevadorPotenciometro extends LinearOpMode {

    private AnalogInput poten;
    private DcMotor stepper;

    public double power = 0.8;

    public void runOpMode(){

        double lectura;
        int stepperLectura;

        poten = hardwareMap.get(AnalogInput.class, "poten");

        stepper = hardwareMap.get(DcMotor.class, "stepper");

        stepper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stepper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (opModeIsActive()){
//0 - 290
            lectura = poten.getVoltage();

            double rangeLectura = Math.round(lectura * 100/3.352);

            int rangeLectura2 = (int)rangeLectura;

            int stepperPosicion = Math.round(rangeLectura2 * 290 / 100);

            stepperLectura = stepper.getCurrentPosition();

            telemetry.addData("Direccion","range: " + rangeLectura2 + "stepper:" + stepperLectura);
            telemetry.update();

            stepper.setTargetPosition(stepperPosicion);
            stepper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            stepper.setPower(power);
        }
    }

    public void inclinacionArriba(double power){
    }

    public void inclinacionAabajo(double power){
    }

}
