 package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.AnalogInput;
        import com.qualcomm.robotcore.hardware.DcMotor;

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
//0 - 290 posicion para 360 de la llanta
            lectura = poten.getVoltage();

            double rangeLectura = Math.round(lectura * 100/3.352); //volver valor del potenciometro a rango de 0 a 100

            int rangeLectura2 = (int)rangeLectura; //volver el rango a int

            int stepperPosicion = Math.round(rangeLectura2 * 290 / 100); //Volver el rango2 a la posicion de la llanta

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