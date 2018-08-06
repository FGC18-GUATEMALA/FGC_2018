
package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.AnalogInput;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.util.Range;

        import java.util.concurrent.TimeUnit;

@Disabled

@TeleOp(name = "Elevador con Potenciometro y Control")
public class ElevadorConPotenciometro extends LinearOpMode {

    private AnalogInput poten;
    private DcMotor stepper;

    public double power = 1;
    public int posicion2 = 0;

    public void runOpMode(){

        poten = hardwareMap.get(AnalogInput.class, "poten");

        stepper = hardwareMap.get(DcMotor.class, "stepper");

        stepper.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stepper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (opModeIsActive()) {
            //0 - 290 rango de la llanta para los 360

            boolean up = gamepad1.dpad_up;
            boolean down = gamepad1.dpad_down;

            int posicion = stepper.getCurrentPosition();

            if (up) {
                posicion2 = posicion + 20;
            } else if (down) {
                posicion2 = posicion - 20;
            }

            stepper.setTargetPosition(posicion2);
            stepper.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            stepper.setPower(power);
        }
    }
} 