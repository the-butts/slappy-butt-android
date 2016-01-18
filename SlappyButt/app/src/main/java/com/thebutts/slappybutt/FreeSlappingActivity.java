package com.thebutts.slappybutt;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.res.ResourcesCompat;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thebutts.slappybutt.Helpers.*;

import java.util.Random;

public class FreeSlappingActivity extends Activity implements SensorEventListener {

    private Drawable mRightInitial;
    private Drawable mLeftInitial;
    private Drawable mTorso;

    private AnimationDrawable mRightButtSlapAnimation;
    private AnimationDrawable mLeftButtSlapAnimation;

    private AnimationDrawable mRightButtFlintLeftAnimation;
    private AnimationDrawable mLeftButtFlintLeftAnimation;

    private AnimationDrawable mRightButtFlintRightAnimation;
    private AnimationDrawable mLeftButtFlintRightAnimation;

    private AnimationDrawable mRightButtFlintUpAnimation;
    private AnimationDrawable mLeftButtFlintUpAnimation;

    private AnimationDrawable mRightButtFlintDownAnimation;
    private AnimationDrawable mLeftButtFlintDownAnimation;

    private AnimationDrawable mRightButtPinchInAnimation;
    private AnimationDrawable mLeftButtPinchInAnimation;

    private AnimationDrawable mRightButtPinchOutAnimation;
    private AnimationDrawable mLeftButtPinchOutAnimation;

    private AnimationDrawable mRightButtShakeAnimation;
    private AnimationDrawable mLeftButtShakeAnimation;

    private Animation mBackgroundFadeInAnimation;
    private Animation mBackgroundFadeOutAnimation;

    private ImageView mTorsoContainer;
    private ImageView mLeftButtContainer;
    private ImageView mRightButtContainer;

    private FrameLayout mSlappingFrame;
    private FrameLayout mActivityLayout;

    private Integer mScreenWidth;

    private Vibrator mVibrator;

    private TypedArray mBackgroundImages;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private long mLastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void SetupSensors() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    void SetupAnimations() {

        this.mRightInitial = ResourcesCompat.getDrawable(getResources(), R.drawable.first_right_initial, null);
        this.mLeftInitial = ResourcesCompat.getDrawable(getResources(), R.drawable.first_left_initial, null);
        this.mTorso = ResourcesCompat.getDrawable(getResources(), R.drawable.first_torso_initial, null);

        this.mRightButtContainer = (ImageView) findViewById(R.id.ivRightButt);
        this.mRightButtContainer.setBackground(mRightInitial);
        this.mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        this.mLeftButtContainer = (ImageView) findViewById(R.id.ivLeftButt);
        this.mLeftButtContainer.setBackground(mLeftInitial);
        this.mLeftButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        this.mTorsoContainer = (ImageView) findViewById(R.id.ivBodyTop);
        this.mTorsoContainer.setBackground(mTorso);

        this.mSlappingFrame = (FrameLayout) findViewById(R.id.slappingFrame);
        this.mActivityLayout = (FrameLayout) findViewById(R.id.freeSlapActivityLayout);

        this.mBackgroundImages = getResources().obtainTypedArray(R.array.background_images);

        this.mBackgroundFadeInAnimation = AnimationUtils.loadAnimation(FreeSlappingActivity.this, R.anim.fade_in);
        this.mBackgroundFadeOutAnimation = AnimationUtils.loadAnimation(FreeSlappingActivity.this, R.anim.fade_out);

        //Resources res = this.context.getResources();



       this.mRightButtSlapAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_butt_slap, null);
       this.mLeftButtSlapAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_butt_slap, null);

       this.mRightButtFlintLeftAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_flint_left, null);
       this.mLeftButtFlintLeftAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_flint_left, null);
       this.mRightButtFlintRightAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_flint_right, null);
       this.mLeftButtFlintRightAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_flint_right, null);
       this.mRightButtFlintUpAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_flint_up, null);
       this.mLeftButtFlintUpAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_flint_up, null);
       this.mRightButtFlintDownAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_flint_down, null);
       this.mLeftButtFlintDownAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_flint_down, null);
       this.mRightButtPinchInAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_pinch_in, null);
       this.mLeftButtPinchInAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_pinch_in, null);
       this.mRightButtPinchOutAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_pinch_out, null);
       this.mLeftButtPinchOutAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_pinch_out, null);
       this.mRightButtShakeAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_right_shake, null);
       this.mLeftButtShakeAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.anim_first_left_shake, null);


        setRandomBackGround();
    }

    void getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mScreenWidth = metrics.widthPixels;
    }

    void setRandomBackGround() {
        Random rand = new Random();

        final int index = rand.nextInt(mBackgroundImages.length());

        mActivityLayout.startAnimation(mBackgroundFadeOutAnimation);
        mActivityLayout.setBackgroundResource(mBackgroundImages.getResourceId(index, -1));
        mActivityLayout.startAnimation(mBackgroundFadeInAnimation);
    }

    void setupUiEvents() {

        final GestureDetectorCompat doubleTapGestureDetector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                FreeSlappingActivity.this.setRandomBackGround();
                return true;
            }
        });

        mTorsoContainer.setOnTouchListener(new View.OnTouchListener() {

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

        mSlappingFrame.setOnTouchListener(scaleGestureListener);
    }

    private void onClickPinchOut() {
        mRightButtContainer.setBackground(mRightButtPinchOutAnimation);
        //mRightButtPinchOutAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtPinchOutAnimation);
        // mLeftButtPinchOutAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtPinchOutAnimation.start();
        mLeftButtPinchOutAnimation.start();
    }

    private void onClickPinchIn() {
        mRightButtContainer.setBackground(mRightButtPinchInAnimation);
        //mRightButtPinchInAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtPinchInAnimation);
        // mLeftButtPinchInAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtPinchInAnimation.start();
        mLeftButtPinchInAnimation.start();
    }

    private void onClickFlintUp() {
        mRightButtContainer.setBackground(mRightButtFlintUpAnimation);
        // mRightButtFlintUpAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtFlintUpAnimation);
        // mLeftButtFlintUpAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtFlintUpAnimation.start();
        mLeftButtFlintUpAnimation.start();
    }

    private void onClickFlintDown() {
        mRightButtContainer.setBackground(mRightButtFlintDownAnimation);
        // mRightButtFlintDownAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtFlintDownAnimation);
        // mLeftButtFlintDownAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtFlintDownAnimation.start();
        mLeftButtFlintDownAnimation.start();
    }

    private void onClickFlintRight() {
        mRightButtContainer.setBackground(mRightButtFlintRightAnimation);
        //  mRightButtFlintRightAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtFlintRightAnimation);
        // mLeftButtFlintRightAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtFlintRightAnimation.start();
        mLeftButtFlintRightAnimation.start();
    }

    private void onClickFlintLeft() {
        mRightButtContainer.setBackground(mRightButtFlintLeftAnimation);
        // mRightButtFlintLeftAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtFlintLeftAnimation);
        // mLeftButtFlintLeftAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtFlintLeftAnimation.start();
        mLeftButtFlintLeftAnimation.start();
    }

    private void onClickShake() {
        mRightButtContainer.setBackground(mRightButtShakeAnimation);
        // mRightButtShakeAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        mLeftButtContainer.setBackground(mLeftButtShakeAnimation);
        // mLeftButtShakeAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onHuc");

        mRightButtShakeAnimation.start();
        mLeftButtShakeAnimation.start();

        long[] pattern = new long[]{75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 150, 150, 75, 75, 75, 225, 75, 375, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225};

        mVibrator.vibrate(pattern, -1);
    }

    private void onClickBtnRightButt() {
        mRightButtContainer.setBackground(mRightButtSlapAnimation);
        //  mRightButtSlapAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        this.stopAllActiveAnimations("onRight");

//        if (mRightButtSlapAnimation.isRunning()) {
//
//            mRightButtSlapAnimation.stop();
//            mVibrator.cancel();
//        }
//
//        if (mLeftButtSlapAnimation == null || mLeftButtSlapAnimation.isRunning()) {
//            mLeftButtContainer.setBackgroundResource(R.drawable.first_left_initial);
//        }

        mRightButtSlapAnimation.start();
    }

    private void onClickBtnLeftButt() {
        mLeftButtContainer.setBackground(mLeftButtSlapAnimation);
        // mLeftButtSlapAnimation = (AnimationDrawable) mLeftButtContainer.getBackground();

        this.stopAllActiveAnimations("onLeft");

//        if (mLeftButtSlapAnimation.isRunning()) {
//            mLeftButtSlapAnimation.stop();
//            mVibrator.cancel();
//        }
//
//        if (mRightButtSlapAnimation == null || !mRightButtSlapAnimation.isRunning()) {
//            mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
//        }

        mLeftButtSlapAnimation.start();
    }

    private void stopAllActiveAnimations(String clickedOn) {

        if (mRightButtFlintLeftAnimation != null && (mRightButtFlintLeftAnimation.isRunning() || mLeftButtFlintLeftAnimation.isRunning())) {
            //  setInitialButts();
            mRightButtFlintLeftAnimation.stop();
            mLeftButtFlintLeftAnimation.stop();
        }

        if (mRightButtFlintDownAnimation != null && (mRightButtFlintDownAnimation.isRunning() || mLeftButtFlintDownAnimation.isRunning())) {
            // setInitialButts();
            mRightButtFlintDownAnimation.stop();
            mLeftButtFlintDownAnimation.stop();
        }

        if (mRightButtFlintRightAnimation != null && (mRightButtFlintRightAnimation.isRunning() || mLeftButtFlintRightAnimation.isRunning())) {
            //  setInitialButts();
            mRightButtFlintRightAnimation.stop();
            mLeftButtFlintRightAnimation.stop();
        }

        if (mRightButtFlintUpAnimation != null && (mRightButtFlintUpAnimation.isRunning() || mLeftButtFlintUpAnimation.isRunning())) {
            // setInitialButts();
            mRightButtFlintUpAnimation.stop();
            mLeftButtFlintUpAnimation.stop();
        }

        if (mRightButtPinchInAnimation != null && (mRightButtPinchInAnimation.isRunning() || mLeftButtPinchInAnimation.isRunning())) {
            //setInitialButts();
            mRightButtPinchInAnimation.stop();
            mLeftButtPinchInAnimation.stop();
        }

        if (mRightButtPinchOutAnimation != null && (mRightButtPinchOutAnimation.isRunning() || mLeftButtPinchOutAnimation.isRunning())) {
            // setInitialButts();
            mRightButtPinchOutAnimation.stop();
            mLeftButtPinchOutAnimation.stop();
        }

        if (mRightButtShakeAnimation != null && (mRightButtShakeAnimation.isRunning() || mLeftButtShakeAnimation.isRunning())) {
            //setInitialButts();
            mRightButtShakeAnimation.stop();
            mLeftButtShakeAnimation.stop();
            mVibrator.cancel();
        }

        if (mLeftButtSlapAnimation != null && mLeftButtSlapAnimation.isRunning()) {
            if (!clickedOn.equals("onRight")) {
                mLeftButtSlapAnimation.stop();
            }
        }

        if (mRightButtSlapAnimation != null && mRightButtSlapAnimation.isRunning()) {

            if (!clickedOn.equals("onLeft")) {
                mRightButtSlapAnimation.stop();
            }
        }

        if (clickedOn.equals("onRight") && (mLeftButtSlapAnimation == null || !mLeftButtSlapAnimation.isRunning())) {
            mLeftButtContainer.setBackground(mLeftInitial);
        }

        if (clickedOn.equals("onLeft") && (mRightButtSlapAnimation == null || !mRightButtSlapAnimation.isRunning())) {
            mRightButtContainer.setBackground(mRightInitial);
        }

    }

    private void setInitialButts() {
        mRightButtContainer.setBackground(mRightInitial);
        mLeftButtContainer.setBackground(mLeftInitial);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - mLastUpdate) > 300) {
                long diffTime = (curTime - mLastUpdate);
                mLastUpdate = curTime;

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
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FreeSlapping Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.thebutts.slappybutt/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FreeSlapping Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.thebutts.slappybutt/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
