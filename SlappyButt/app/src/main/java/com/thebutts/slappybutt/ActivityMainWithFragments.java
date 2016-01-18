package com.thebutts.slappybutt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thebutts.slappybutt.Fragments.FreeSlappingFragment;
import com.thebutts.slappybutt.Fragments.GameOverFragment;
import com.thebutts.slappybutt.Fragments.GameSlappingFragment;
import com.thebutts.slappybutt.Fragments.MainMenuFragment;

public class ActivityMainWithFragments extends FragmentActivity {

    GameSlappingFragment mGameFragment;
    GameOverFragment mGameOverFragment;
    MainMenuFragment mMainMenuFragment;
    FreeSlappingFragment mFreeSlappingFragment;

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

        this.loadMainMenu();

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
                "ActivityMainWithFragments Page", // TODO: Define a title for the content shown.
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
                "ActivityMainWithFragments Page", // TODO: Define a title for the content shown.
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

    public void loadGameOver(Integer slapsCount) {

        final Integer slaps = slapsCount;

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityMainWithFragments.this.mFt = getSupportFragmentManager().beginTransaction();
                ActivityMainWithFragments.this.mGameOverFragment = GameOverFragment.newInstance(slaps);
                ActivityMainWithFragments.this.mFt.replace(R.id.free_slapping_fragment_container, mGameOverFragment).commit();
            }
        }).start();
    }

    public void loadNewGame() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityMainWithFragments.this.mFt = getSupportFragmentManager().beginTransaction();
                ActivityMainWithFragments.this.getNewGameFragment();
                ActivityMainWithFragments.this.mFt.replace(R.id.free_slapping_fragment_container, ActivityMainWithFragments.this.mGameFragment).commit();
            }
        }).start();
    }

    public void loadMainMenu() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityMainWithFragments.this.mFt = getSupportFragmentManager().beginTransaction();

                ActivityMainWithFragments.this.getMainMenuFragment();

                ActivityMainWithFragments.this.mFt.replace(R.id.free_slapping_fragment_container, ActivityMainWithFragments.this.mMainMenuFragment).commit();
            }
        }).start();
    }

    public void loadFreeSlapping() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityMainWithFragments.this.mFt = getSupportFragmentManager().beginTransaction();

                ActivityMainWithFragments.this.getFreeSlappingFragment();

                ActivityMainWithFragments.this.mFt.replace(R.id.free_slapping_fragment_container, ActivityMainWithFragments.this.mFreeSlappingFragment).commit();
            }
        }).start();
    }


    private void getNewGameFragment() {

        if (ActivityMainWithFragments.this.mGameFragment == null) {

            ActivityMainWithFragments.this.mGameFragment = GameSlappingFragment.newInstance(
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

    private void getFreeSlappingFragment() {

        if (ActivityMainWithFragments.this.mFreeSlappingFragment == null) {

            ActivityMainWithFragments.this.mFreeSlappingFragment = FreeSlappingFragment.newInstance(
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

    private void getMainMenuFragment() {

        if (ActivityMainWithFragments.this.mMainMenuFragment == null) {

            ActivityMainWithFragments.this.mMainMenuFragment = MainMenuFragment.newInstance();
        }
    }

    @Override
    public void onBackPressed() {

        doExit();
    }

    private void doExit() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                ActivityMainWithFragments.this);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.setNegativeButton("No", null);

        alertDialog.setMessage("Do you really want to stop slapping??");
        alertDialog.setTitle("Slappy butt");
        alertDialog.show();
    }
}
