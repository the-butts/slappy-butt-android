package com.thebutts.slappybutt.AsyncTasks;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.thebutts.slappybutt.R;

public class AnimateTask extends AsyncTask<Void, Void, Void> {

    AnimationDrawable rightButtSlappAnimation;
    ImageView imageContainer;
    Activity mContext;

    public AnimateTask(Activity context, int imgContainer, int animation){
        this.mContext = context;

        SetupAnimations(imgContainer, animation );
    }

//    @Override
//    protected Void doInBackground(Void.) {
//
//        rightButtSlappAnimation.start();
//
//        return null;
//    }

    @Override
    protected Void doInBackground(Void... params) {

        Log.d("Start async", "do in backgrounf");
        rightButtSlappAnimation.start();

        return null;
    }

    @Override
    protected void onCancelled() {
        rightButtSlappAnimation.stop();
        super.onCancelled();
    }

    void SetupAnimations(int imgContainer, int animation){
        imageContainer = (ImageView) this.mContext.findViewById(imgContainer);

        imageContainer.setBackgroundResource(animation);
        rightButtSlappAnimation = (AnimationDrawable) imageContainer.getBackground();
    }


}
