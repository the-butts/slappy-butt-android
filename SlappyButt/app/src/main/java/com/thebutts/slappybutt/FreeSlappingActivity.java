package com.thebutts.slappybutt;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.thebutts.slappybutt.Helpers.*;

import java.util.Random;

public class FreeSlappingActivity extends Activity implements SensorEventListener {

    AnimationDrawable rightButtSlappAnimation;
    AnimationDrawable leftButtSlappAnimation;

    AnimationDrawable rightButtFlintLeft;
    AnimationDrawable leftButtSFlintLeft;

    AnimationDrawable rightButtFlintRight;
    AnimationDrawable leftButtFlintRight;

    AnimationDrawable rightButtFlintUp;
    AnimationDrawable leftButtSFlintUp;

    AnimationDrawable rightButtFlintDown;
    AnimationDrawable leftButtFlintDown;

    AnimationDrawable rightButtPinchIn;
    AnimationDrawable leftButtPinchIn;

    AnimationDrawable rightButtPinchOut;
    AnimationDrawable leftButtPinchOut;

    AnimationDrawable rightShake;
    AnimationDrawable leftShake;

    Animation fadeIn;
    Animation fadeOut;


    ImageView mBodyTop;
    ImageView mLevtButtContainer;
    ImageView mRightButtContainer;

    FrameLayout mSlappinngFrame;
    FrameLayout mActivityLayout;

    Integer mScreenWidth;

    Vibrator vibrator;

    TypedArray mBackgroundImages;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_free_slapping);

        this.getScreenWidth();
        this.SetupAnimations();
        this.setupUiEvents();
        this.SetupSensors();
    }

    void SetupSensors() {
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    void SetupAnimations() {
        mRightButtContainer = (ImageView) findViewById(R.id.ivRightButt);
        mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mLevtButtContainer = (ImageView) findViewById(R.id.ivLeftButt);
        mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);
        mLevtButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mBodyTop = (ImageView) findViewById(R.id.ivBodyTop);

        mSlappinngFrame = (FrameLayout) findViewById(R.id.slappingFrame);
        mActivityLayout = (FrameLayout) findViewById(R.id.freeSlapActivityLayout);

        mBackgroundImages = getResources().obtainTypedArray(R.array.background_images);

        fadeIn = AnimationUtils.loadAnimation(FreeSlappingActivity.this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(FreeSlappingActivity.this, R.anim.fade_out);

        setRandomBackGround();
    }

    void getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mScreenWidth = metrics.widthPixels;
    }

    void setRandomBackGround(){
        Random rand = new Random();

        final int index = rand.nextInt(mBackgroundImages.length());

        mActivityLayout.startAnimation(fadeOut);
        mActivityLayout.setBackgroundResource(mBackgroundImages.getResourceId(index, -1));
        mActivityLayout.startAnimation(fadeIn);
    }

    void setupUiEvents() {

        final GestureDetectorCompat doubleTapGestureDetector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                FreeSlappingActivity.this.setRandomBackGround();
                return true;
            }
        });

        mBodyTop.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                doubleTapGestureDetector.onTouchEvent(event);

                return true;
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(this, new SwipeGestureDetector() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                String LOGTAG = "Custom detector";

                switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
                    case 1:
                        Log.d(LOGTAG, "top");
                        FreeSlappingActivity.this.onClickFlintUp();
                        return true;
                    case 2:
                        Log.d(LOGTAG, "left");
                        FreeSlappingActivity.this.onClickFlintLeft();
                        return true;
                    case 3:
                        FreeSlappingActivity.this.onClickFlintDown();
                        Log.d(LOGTAG, "down");
                        return true;
                    case 4:
                        FreeSlappingActivity.this.onClickFlintRight();
                        Log.d(LOGTAG, "right");
                        return true;
                }

                return false;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("onSingleTap :", "" + e.getAction());

                float x = e.getX();

                if (x < mScreenWidth / 2) {
                    FreeSlappingActivity.this.onClickBtnLeftButt();
                } else {
                    FreeSlappingActivity.this.onClickBtnRightButt();
                }

                return true;
            }
        });

        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                float scaleFactor = detector.getScaleFactor();

                if (scaleFactor > 1) {
                    FreeSlappingActivity.this.onClickPinchOut();
                    return true;
                } else {
                    FreeSlappingActivity.this.onClickPinchIn();
                    return false;
                }
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
            }
        });

        final View.OnTouchListener scaleGestureListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();

                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_POINTER_DOWN:
                        FreeSlappingActivity.this.onClickBtnLeftButt();
                        FreeSlappingActivity.this.onClickBtnRightButt();
                        break;
                }

                boolean res = gestureDetector.onTouchEvent(event);

                if (res) {
                    return true;
                }

                res = scaleGestureDetector.onTouchEvent(event);

                return res;
            }
        };

        mSlappinngFrame.setOnTouchListener(scaleGestureListener);
    }

    private void onClickPinchOut() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_out);
        rightButtPinchOut = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_out);
        leftButtPinchOut = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtPinchOut.start();
        leftButtPinchOut.start();
    }

    private void onClickPinchIn() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_in);
        rightButtPinchIn = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_in);
        leftButtPinchIn = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtPinchIn.start();
        leftButtPinchIn.start();
    }

    private void onClickFlintUp() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_up);
        rightButtFlintUp = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_up);
        leftButtSFlintUp = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtFlintUp.start();
        leftButtSFlintUp.start();
    }

    private void onClickFlintDown() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_down);
        rightButtFlintDown = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_down);
        leftButtFlintDown = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtFlintDown.start();
        leftButtFlintDown.start();
    }

    private void onClickFlintRight() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_right);
        rightButtFlintRight = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_right);
        leftButtFlintRight = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtFlintRight.start();
        leftButtFlintRight.start();
    }

    private void onClickFlintLeft() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_left);
        rightButtFlintLeft = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_left);
        leftButtSFlintLeft = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightButtFlintLeft.start();
        leftButtSFlintLeft.start();
    }

    private void onClickShake() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_shake);
        rightShake = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_shake);
        leftShake = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        rightShake.start();
        leftShake.start();

        long[] pattern = new long[]{75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 150, 150, 75, 75, 75, 225, 75, 375, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225};

        vibrator.vibrate(pattern, -1);
    }

    private void onClickBtnRightButt() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_butt_slap);
        rightButtSlappAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        this.stopAllActiveAnimations("onRight");

//        if (rightButtSlappAnimation.isRunning()) {
//
//            rightButtSlappAnimation.stop();
//            vibrator.cancel();
//        }
//
//        if (leftButtSlappAnimation == null || leftButtSlappAnimation.isRunning()) {
//            mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);
//        }

        rightButtSlappAnimation.start();
    }

    private void onClickBtnLeftButt() {
        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_butt_slap);
        leftButtSlappAnimation = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations("onLeft");

//        if (leftButtSlappAnimation.isRunning()) {
//            leftButtSlappAnimation.stop();
//            vibrator.cancel();
//        }
//
//        if (rightButtSlappAnimation == null || !rightButtSlappAnimation.isRunning()) {
//            mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
//        }

        leftButtSlappAnimation.start();
    }

    private void stopAllActiveAnimations(String clickedOn) {

        if (rightButtFlintLeft != null && (rightButtFlintLeft.isRunning() || leftButtSFlintLeft.isRunning())) {
            //  setInitialButts();
            rightButtFlintLeft.stop();
            leftButtSFlintLeft.stop();
        }

        if (rightButtFlintDown != null && (rightButtFlintDown.isRunning() || leftButtFlintDown.isRunning())) {
            // setInitialButts();
            rightButtFlintDown.stop();
            leftButtFlintDown.stop();
        }

        if (rightButtFlintRight != null && (rightButtFlintRight.isRunning() || leftButtFlintRight.isRunning())) {
            //  setInitialButts();
            rightButtFlintRight.stop();
            leftButtFlintRight.stop();
        }

        if (rightButtFlintUp != null && (rightButtFlintUp.isRunning() || leftButtSFlintUp.isRunning())) {
            // setInitialButts();
            rightButtFlintUp.stop();
            leftButtSFlintUp.stop();
        }

        if (rightButtPinchIn != null && (rightButtPinchIn.isRunning() || leftButtPinchIn.isRunning())) {
            //setInitialButts();
            rightButtPinchIn.stop();
            leftButtPinchIn.stop();
        }

        if (rightButtPinchOut != null && (rightButtPinchOut.isRunning() || leftButtPinchOut.isRunning())) {
            // setInitialButts();
            rightButtPinchOut.stop();
            leftButtPinchOut.stop();
        }

        if (rightShake != null && (rightShake.isRunning() || leftShake.isRunning())) {
            //setInitialButts();
            rightShake.stop();
            leftShake.stop();
            vibrator.cancel();
        }

        if (leftButtSlappAnimation != null && leftButtSlappAnimation.isRunning()) {
            if (!clickedOn.equals("onRight")) {
                leftButtSlappAnimation.stop();
            }
        }

        if (rightButtSlappAnimation != null && rightButtSlappAnimation.isRunning()) {

            if (!clickedOn.equals("onLeft")) {
                rightButtSlappAnimation.stop();
            }
        }

        if (clickedOn.equals("onRight") && (leftButtSlappAnimation == null || !leftButtSlappAnimation.isRunning())) {
            mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);
        }

        if (clickedOn.equals("onLeft") && (rightButtSlappAnimation == null || !rightButtSlappAnimation.isRunning())) {
            mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
        }

    }

    private void setInitialButts() {
        mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
        mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 300) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {

                    FreeSlappingActivity.this.onClickShake();
                    // Toast.makeText(this, "Shake it baby", Toast.LENGTH_SHORT).show();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
