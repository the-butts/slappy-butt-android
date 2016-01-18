package com.thebutts.slappybutt;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thebutts.slappybutt.Fragments.GameOverFragment;
import com.thebutts.slappybutt.Fragments.GameSlappingFragment;

public class FreeSlappingActivityWithFragment extends FragmentActivity {

    GameSlappingFragment mGameFragment;
    GameOverFragment mGameOverFragment;
    android.support.v4.app.FragmentTransaction mFt;
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

        setContentView(R.layout.activity_free_slapping_activity_with_fragment);

        new Thread(new Runnable() {
            @Override
            public void run() {
                FreeSlappingActivityWithFragment.this.mFt = getSupportFragmentManager().beginTransaction();

                FreeSlappingActivityWithFragment.this.getNewGameFragment();

                FreeSlappingActivityWithFragment.this.mFt.add(R.id.free_slapping_fragment_container, mGameFragment).commit();
            }
        }).start();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // mFt.add(R.id.free_slapping_fragment_container, mGameFragment).commit();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "FreeSlappingActivityWithFragment Page", // TODO: Define a title for the content shown.
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
                "FreeSlappingActivityWithFragment Page", // TODO: Define a title for the content shown.
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

    @Override
    protected void onPause() {
        super.onPause();

        // mFt.remove(mGameFragment);
    }

    public void onGameOver(Integer slapsCount){

        final Integer slaps = slapsCount;

        new Thread(new Runnable() {
            @Override
            public void run() {
                FreeSlappingActivityWithFragment.this.mFt.remove(FreeSlappingActivityWithFragment.this.mGameFragment);
                FreeSlappingActivityWithFragment.this.mFt = getSupportFragmentManager().beginTransaction();
                FreeSlappingActivityWithFragment.this.mGameOverFragment = GameOverFragment.newInstance(slaps);
                FreeSlappingActivityWithFragment.this.mFt.replace(R.id.free_slapping_fragment_container, mGameOverFragment).commit();
            }
        }).start();
    }

    public void onNewGame(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                FreeSlappingActivityWithFragment.this.mFt.remove(FreeSlappingActivityWithFragment.this.mGameOverFragment);
                FreeSlappingActivityWithFragment.this.mFt = getSupportFragmentManager().beginTransaction();
                FreeSlappingActivityWithFragment.this.getNewGameFragment();
                FreeSlappingActivityWithFragment.this.mFt.replace(R.id.free_slapping_fragment_container,  FreeSlappingActivityWithFragment.this.mGameFragment).commit();
            }
        }).start();
    }

    private void getNewGameFragment(){

        if (FreeSlappingActivityWithFragment.this.mGameFragment == null) {

            FreeSlappingActivityWithFragment.this.mGameFragment = GameSlappingFragment.newInstance(
                    R.drawable.anim_first_right_butt_slap,
                    R.drawable.anim_first_right_flint_left,
                    R.drawable.anim_first_right_flint_right,
                    R.drawable.anim_first_right_flint_up,
                    R.drawable.anim_first_right_flint_down,
                    R.drawable.anim_first_right_pinch_out,
                    R.drawable.anim_first_right_pinch_in,
                    R.drawable.anim_first_right_shake,
                    R.drawable.first_right_initial,
                    R.drawable.anim_first_left_butt_slap,
                    R.drawable.anim_first_left_flint_left,
                    R.drawable.anim_first_left_flint_right,
                    R.drawable.anim_first_left_flint_up,
                    R.drawable.anim_first_left_flint_down,
                    R.drawable.anim_first_left_pinch_out,
                    R.drawable.anim_first_left_pinch_in,
                    R.drawable.anim_first_left_shake,
                    R.drawable.first_left_initial,
                    R.drawable.first_torso_initial,
                    R.array.first_torso_images);
        }
    }
}
