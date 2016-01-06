package com.thebutts.slappybutt;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FreeSlappingActivity extends Activity {

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


   // AnimateTask rightButtAnimateTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_free_slapping);

        setupUiEvents();
        SetupAnimations();
    }

    void SetupAnimations(){

      //  rightButtSlappAnimation = (AnimationDrawable)   mRightButtContainer.getBackground();
        Log.d("Butte", "on setup animations");

        mRightButtContainer = (ImageView) findViewById(R.id.ivRightButt);
        mLevtButtContainer = (ImageView) findViewById(R.id.ivLeftButt);

//
        mRightButtContainer = (ImageView) findViewById(R.id.ivRightButt);
        mRightButtContainer.setBackgroundResource(R.drawable.first_right_initial);
//
//
//        // set right but animations
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_butt_slap);
//        rightButtSlappAnimation = (AnimationDrawable) mRightButtContainer.getBackground();
//
////        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_right);
////        rightButtFlintRight = (AnimationDrawable) mRightButtContainer.getBackground();
//
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_left);
//        rightButtFlintLeft = (AnimationDrawable) mRightButtContainer.getBackground();
//
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_up);
//        rightButtFlintUp = (AnimationDrawable) mRightButtContainer.getBackground();
//
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_down);
//        rightButtFlintDown = (AnimationDrawable) mRightButtContainer.getBackground();
//
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_in);
//        rightButtPinchIn = (AnimationDrawable) mRightButtContainer.getBackground();
//
//        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_out);
//        rightButtPinchOut = (AnimationDrawable) mRightButtContainer.getBackground();

        // set left but animations
        mLevtButtContainer.setBackgroundResource(R.drawable.first_left_initial);

////        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_right);
////        leftButtFlintRight = (AnimationDrawable) mLevtButtContainer.getBackground();
//
//        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_left);
//        leftButtSFlintLeft = (AnimationDrawable) mLevtButtContainer.getBackground();
//
//        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_up);
//        leftButtSFlintUp = (AnimationDrawable) mLevtButtContainer.getBackground();
//
//        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_down);
//        leftButtFlintDown = (AnimationDrawable) mLevtButtContainer.getBackground();
//
//        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_in);
//        leftButtPinchIn = (AnimationDrawable) mLevtButtContainer.getBackground();
//
//        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_out);
//        leftButtPinchOut = (AnimationDrawable) mLevtButtContainer.getBackground();

    }


    void setupUiEvents() {
        Button firstButton = (Button) findViewById(R.id.btn_slapp_left_butt);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickBtnLeftButt((Button) v);
            }
        });

        Button secondButton = (Button) findViewById(R.id.btn_slap_right_butt);

        Log.d("Butte", "on secund button get" + secondButton);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.d("Butte", "on click listener right butt");
                FreeSlappingActivity.this.onClickBtnRightButt((Button) v);
            }
        });

        Button btnFlintLeft = (Button) findViewById(R.id.btnFlintLeft);
        btnFlintLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickFlintLeft((Button) v);
            }
        });

        Button btnFlintRight = (Button) findViewById(R.id.btnFlintRight);
        btnFlintRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickFlintRight((Button) v);
            }
        });

        Button btnFlintDown = (Button) findViewById(R.id.btnFlintDown);
        btnFlintDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickFlintDown((Button) v);
            }
        });

        Button btnFlintUp = (Button) findViewById(R.id.btnFlintUp);
        btnFlintUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickFlintUp((Button) v);
            }
        });

        Button btnPinchIn = (Button) findViewById(R.id.btnPinchIn);
        btnPinchIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickPinchIn((Button) v);
            }
        });

        Button btnPinchIOut= (Button) findViewById(R.id.btnPinchOut);
        btnPinchIOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FreeSlappingActivity.this.onClickPinchOut((Button) v);
            }
        });
    }

    private void onClickPinchOut(Button v) {
        Log.d("Butte", "on right butt pinch out");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_out);
        rightButtPinchOut = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_out);
        leftButtPinchOut = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtPinchOut.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtPinchOut.stop();
            leftButtPinchOut.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtPinchOut.start();
        leftButtPinchOut.start();
    }

    private void onClickPinchIn(Button v) {

        Log.d("Butte", "on right butt pinch in");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_pinch_in);
        rightButtPinchIn = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_pinch_in);
        leftButtPinchIn = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtPinchIn.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtPinchIn.stop();
            leftButtPinchIn.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtPinchIn.start();
        leftButtPinchIn.start();

    }

    private void onClickFlintUp(Button v) {

        Log.d("Butte", "on right butt flint up");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_up);
        rightButtFlintUp = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_up);
        leftButtSFlintUp = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtFlintUp.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtFlintUp.stop();
            leftButtSFlintUp.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtFlintUp.start();
        leftButtSFlintUp.start();

    }

    private void onClickFlintDown(Button v) {

        Log.d("Butte", "on right butt flint down");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_down);
        rightButtFlintDown = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_down);
        leftButtFlintDown = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtFlintDown.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtFlintDown.stop();
            leftButtFlintDown.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtFlintDown.start();
        leftButtFlintDown.start();

    }

    private void onClickFlintRight(Button v) {

        Log.d("Butte", "on right butt flint right");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_right);
        rightButtFlintRight = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_right);
        leftButtFlintRight = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtFlintRight.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtFlintRight.stop();
            leftButtFlintRight.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtFlintRight.start();
        leftButtFlintRight.start();
    }

    private void onClickFlintLeft(Button button) {

        Log.d("Butte", "on right butt flint left");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_flint_left);
        rightButtFlintLeft = (AnimationDrawable) mRightButtContainer.getBackground();

        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_flint_left);
        leftButtSFlintLeft = (AnimationDrawable) mLevtButtContainer.getBackground();


        if(rightButtFlintLeft.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtFlintLeft.stop();
            leftButtSFlintLeft.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtFlintLeft.start();
        leftButtSFlintLeft.start();

    }

    private void onClickBtnRightButt(Button v) {

        Log.d("Butte", "on right butt slap");
        mRightButtContainer.setBackgroundResource(R.drawable.anim_first_right_butt_slap);
        rightButtSlappAnimation = (AnimationDrawable) mRightButtContainer.getBackground();



        if(rightButtSlappAnimation.isRunning()){
            mRightButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            rightButtSlappAnimation.stop();
        }


        mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        rightButtSlappAnimation.start();

         rightButtSlappAnimation.start();
        //TextView textView = (TextView) findViewById(R.id.tv_butt);
     //   textView.setText("Slapped right butt");

    }

    private void onClickBtnLeftButt(Button v) {

        Log.d("Butte", "on left butt slap");
        mLevtButtContainer.setBackgroundResource(R.drawable.anim_first_left_butt_slap);
        leftButtSlappAnimation = (AnimationDrawable) mLevtButtContainer.getBackground();

        if(leftButtSlappAnimation.isRunning()){
            mLevtButtContainer.setLayerType(View.LAYER_TYPE_NONE, null);
            leftButtSlappAnimation.stop();
        }


        mLevtButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        leftButtSlappAnimation.start();

        // rightButtSlappAnimation.stop();
       // TextView textView = (TextView) findViewById(R.id.tv_butt);
       // textView.setText("Slapped left butt");
    }

    private void stopAllActiveAnimations(){
        // TODO: do it
    }
}
