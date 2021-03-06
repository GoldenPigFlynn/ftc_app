package org.firstinspires.ftc.teamcode;

/**
 *  This is from the position closest to the crater.
 */
public abstract class CraterDescendSampleRun extends StandardChassis {

    private boolean madeTheRun = false;
    private GoldStatus pos = GoldStatus.Unknown;

    protected CraterDescendSampleRun(ChassisConfig config) {
        super(config);
    }

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        initMotors();
        initArm();
        initGyroscope();
        initTimeouts();
        initSampling();
    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop () {
    }

    /**
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start () {
        // Reset the game timer.
        runtime.reset();
    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop () {
        stopSampling();
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop () {

        if (madeTheRun == false) {

            pos = loopSampling();

            descendFromLander();

            if (pos == GoldStatus.Unknown)
                pos = sampleProbe();

            craterSampleRun(pos);

            madeTheRun = true;

        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Pos: " + pos.toString());
        telemetry.addData("Status", "time: " + runtime.toString());
        telemetry.addData("Status", "madeTheRun=%b", madeTheRun);
    }
}