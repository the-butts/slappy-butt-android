package com.thebutts.slappybutt;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.thebutts.slappybutt.Helpers.*;

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

    ImageView mLevtButtContainer;
    ImageView mRightButtContainer;

    FrameLayout mSlappinngFrame;

    Integer mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_free_slapping);

        getScreenWidth();
        SetupAnimations();
        setupUiEvents();
    }

    void SetupAnimations() {
        mRightButtContainer = (ImageView) findViewById(R.id.ivRightButt);
        mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mLevtButtContainer = (ImageView) findViewById(R.id.ivLeftButt);
        mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);
        mLevtButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        mSlappinngFrame = (FrameLayout) findViewById(R.id.slappingFrame);
    }

    void getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mScreenWidth = metrics.widthPixels;
    }

    void setupUiEvents() {
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

                switch(action & MotionEvent.ACTION_MASK)
                {
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

        this.stopAllActiveAnimations();

        rightButtPinchOut.start();
        leftButtPinchOut.start();
    }

    private void onClickPinchIn() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_in);
        rightButtPinchIn = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_in);
        leftButtPinchIn = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations();

        rightButtPinchIn.start();
        leftButtPinchIn.start();
    }

    private void onClickFlintUp() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_up);
        rightButtFlintUp = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_up);
        leftButtSFlintUp = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations();

        rightButtFlintUp.start();
        leftButtSFlintUp.start();
    }

    private void onClickFlintDown() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_down);
        rightButtFlintDown = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_down);
        leftButtFlintDown = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations();

        rightButtFlintDown.start();
        leftButtFlintDown.start();
    }

    private void onClickFlintRight() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_right);
        rightButtFlintRight = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_right);
        leftButtFlintRight = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations();

        rightButtFlintRight.start();
        leftButtFlintRight.start();
    }

    private void onClickFlintLeft() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_left);
        rightButtFlintLeft = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_left);
        leftButtSFlintLeft = (AnimationDrawable) mLevtButtContainer.getBackground();

        this.stopAllActiveAnimations();

        rightButtFlintLeft.start();
        leftButtSFlintLeft.start();
    }

    private void onClickBtnRightButt() {
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_butt_slap);
        rightButtSlappAnimation = (AnimationDrawable) mRightButtContainer.getBackground();

        if (rightButtSlappAnimation.isRunning()) {
            rightButtSlappAnimation.stop();
        }

        rightButtSlappAnimation.start();
    }

    private void onClickBtnLeftButt() {
        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_butt_slap);
        leftButtSlappAnimation = (AnimationDrawable) mLevtButtContainer.getBackground();

        if (leftButtSlappAnimation.isRunning()) {
            leftButtSlappAnimation.stop();
        }

        leftButtSlappAnimation.start();
    }

    private void stopAllActiveAnimations() {
        if (leftButtSlappAnimation != null && leftButtSlappAnimation.isRunning()) {
            leftButtSlappAnimation.stop();
        }

        if (rightButtSlappAnimation != null && rightButtSlappAnimation.isRunning()) {
            rightButtSlappAnimation.stop();
        }

        if (rightButtFlintLeft != null && (rightButtFlintLeft.isRunning() || leftButtSFlintLeft.isRunning())) {
            rightButtFlintLeft.stop();
            leftButtSFlintLeft.stop();
        }

        if (rightButtFlintDown != null && (rightButtFlintDown.isRunning() || leftButtFlintDown.isRunning())) {
            rightButtFlintDown.stop();
            leftButtFlintDown.stop();
        }

        if (rightButtFlintRight != null && (rightButtFlintRight.isRunning() || leftButtFlintRight.isRunning())) {
            rightButtFlintRight.stop();
            leftButtFlintRight.stop();
        }

        if (rightButtFlintUp != null && (rightButtFlintUp.isRunning() || leftButtSFlintUp.isRunning())) {
            rightButtFlintUp.stop();
            leftButtSFlintUp.stop();
        }

        if (rightButtPinchIn != null && (rightButtPinchIn.isRunning() || leftButtPinchIn.isRunning())) {
            rightButtPinchIn.stop();
            leftButtPinchIn.stop();
        }

        if (rightButtPinchOut != null && (rightButtPinchOut.isRunning() || leftButtPinchOut.isRunning())) {
            rightButtPinchOut.stop();
            leftButtPinchOut.stop();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
