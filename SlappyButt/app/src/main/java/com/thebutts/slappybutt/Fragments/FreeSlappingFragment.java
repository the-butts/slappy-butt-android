package com.thebutts.slappybutt.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.thebutts.slappybutt.Common.Constants;
import com.thebutts.slappybutt.Helpers.SwipeGestureDetector;
import com.thebutts.slappybutt.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FreeSlappingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FreeSlappingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FreeSlappingFragment extends Fragment implements SensorEventListener {

    private final String TAG = getClass().getSimpleName();

    private GestureLibrary gLibrary;

    private View inflatedView;

    private OnFragmentInteractionListener mListener;

    private AnimationDrawable mRightButtSlapAnimation;
    private AnimationDrawable mRightButtFlintLeftAnimation;
    private AnimationDrawable mRightButtFlintRightAnimation;
    private AnimationDrawable mRightButtFlintUpAnimation;
    private AnimationDrawable mRightButtFlintDownAnimation;
    private AnimationDrawable mRightButtPinchOutAnimation;
    private AnimationDrawable mRightButtPinchInAnimation;
    private AnimationDrawable mRightButtShakeAnimation;
    private Drawable mRightInitial;

    private AnimationDrawable mLeftButtSlapAnimation;
    private AnimationDrawable mLeftButtFlintLeftAnimation;
    private AnimationDrawable mLeftButtFlintRightAnimation;
    private AnimationDrawable mLeftButtFlintUpAnimation;
    private AnimationDrawable mLeftButtFlintDownAnimation;
    private AnimationDrawable mLeftButtPinchOutAnimation;
    private AnimationDrawable mLeftButtPinchInAnimation;
    private AnimationDrawable mLeftButtShakeAnimation;
    private Drawable mLeftInitial;

    private Drawable mTorso;

    private Animation mBackgroundFadeInAnimation;
    private Animation mBackgroundFadeOutAnimation;

    private ImageView mTorsoContainer;
    private ImageView mLeftButtContainer;
    private ImageView mRightButtContainer;

    private FrameLayout mSlappingFrame;
    private FrameLayout mActivityLayout;

    private FrameLayout mGestureListener;
    private GestureOverlayView mGestureOverlay;


    private Integer mScreenWidth;

    private Vibrator mVibrator;

    private TypedArray mBackgroundImages;
    private TypedArray mTorsoImages;
    private TypedArray mSlapSounds;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private MediaPlayer mMediaPlayer;

    private long mLastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public FreeSlappingFragment() {
        // Required empty public constructor
    }

    public static FreeSlappingFragment newInstance(
            Integer AnimRightSlap,
            Integer AnimRightFlintLeft,
            Integer AnimRightFlintRight,
            Integer AnimRightFlintUp,
            Integer AnimRightFlintDown,
            Integer AnimRightPinchOut,
            Integer AnimRightPinchIn,
            Integer AnimRightShake,
            Integer DrawRightInitial,
            Integer AnimLeftSlap,
            Integer AnimLeftFlintLeft,
            Integer AnimLeftFlintRight,
            Integer AnimLeftFlintUp,
            Integer AnimLeftFlintDown,
            Integer AnimLeftPinchOut,
            Integer AnimLeftPinchIn,
            Integer AnimLeftShake,
            Integer DrawLeftInitial,
            Integer DrawTorso,
            Integer ArrayTorsoImages) {
        FreeSlappingFragment fragment = new FreeSlappingFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_ANIM_RIGHT_SLAP_ID, AnimRightSlap);
        args.putInt(Constants.ARG_ANIM_RIGHT_FLINT_LEFT_ID, AnimRightFlintLeft);
        args.putInt(Constants.ARG_ANIM_RIGHT_FLINT_RIGHT_ID, AnimRightFlintRight);
        args.putInt(Constants.ARG_ANIM_RIGHT_FLINT_UP_ID, AnimRightFlintUp);
        args.putInt(Constants.ARG_ANIM_RIGHT_FLINT_DOWN_ID, AnimRightFlintDown);
        args.putInt(Constants.ARG_ANIM_RIGHT_PUNCH_OUT_ID, AnimRightPinchOut);
        args.putInt(Constants.ARG_ANIM_RIGHT_PUNCH_IN_ID, AnimRightPinchIn);
        args.putInt(Constants.ARG_ANIM_RIGHT_SHAKE_ID, AnimRightShake);
        args.putInt(Constants.ARG_DRAWABLE_RIGHT_INITIAL_ID, DrawRightInitial);
        args.putInt(Constants.ARG_ANIM_LEFT_SLAP_ID, AnimLeftSlap);
        args.putInt(Constants.ARG_ANIM_LEFT_FLINT_LEFT_ID, AnimLeftFlintLeft);
        args.putInt(Constants.ARG_ANIM_LEFT_FLINT_RIGHT_ID, AnimLeftFlintRight);
        args.putInt(Constants.ARG_ANIM_LEFT_FLINT_UP_ID, AnimLeftFlintUp);
        args.putInt(Constants.ARG_ANIM_LEFT_FLINT_DOWN_ID, AnimLeftFlintDown);
        args.putInt(Constants.ARG_ANIM_LEFT_PUNCH_OUT_ID, AnimLeftPinchOut);
        args.putInt(Constants.ARG_ANIM_LEFT_PUNCH_IN_ID, AnimLeftPinchIn);
        args.putInt(Constants.ARG_ANIM_LEFT_SHAKE_ID, AnimLeftShake);
        args.putInt(Constants.ARG_DRAWABLE_LEFT_INITIAL_ID, DrawLeftInitial);
        args.putInt(Constants.ARG_DRAWABLE_TORSO_ID, DrawTorso);
        args.putInt(Constants.ARG_ARRAY_TORSO_DRAWABLES_ID, ArrayTorsoImages);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.setupAnimations();
            client = new GoogleApiClient.Builder(getActivity()).addApi(AppIndex.API).build();
        }
    }

    private void setupAnimations() {
        this.mRightButtSlapAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_SLAP_ID), null);
        this.mRightButtFlintLeftAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_FLINT_LEFT_ID), null);
        this.mRightButtFlintRightAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_FLINT_RIGHT_ID), null);
        this.mRightButtFlintUpAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_FLINT_UP_ID), null);
        this.mRightButtFlintDownAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_FLINT_DOWN_ID), null);
        this.mRightButtPinchOutAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_PUNCH_OUT_ID), null);
        this.mRightButtPinchInAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_PUNCH_IN_ID), null);
        this.mRightButtShakeAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_RIGHT_SHAKE_ID), null);
        this.mRightInitial = ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_DRAWABLE_RIGHT_INITIAL_ID), null);
        this.mLeftButtSlapAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_SLAP_ID), null);
        this.mLeftButtFlintLeftAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_FLINT_LEFT_ID), null);
        this.mLeftButtFlintRightAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_FLINT_RIGHT_ID), null);
        this.mLeftButtFlintUpAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_FLINT_UP_ID), null);
        this.mLeftButtFlintDownAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_FLINT_DOWN_ID), null);
        this.mLeftButtPinchOutAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_PUNCH_OUT_ID), null);
        this.mLeftButtPinchInAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_PUNCH_IN_ID), null);
        this.mLeftButtShakeAnimation = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_ANIM_LEFT_SHAKE_ID), null);
        this.mLeftInitial = ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_DRAWABLE_LEFT_INITIAL_ID), null);
        this.mTorso = ResourcesCompat.getDrawable(getResources(), getArguments().getInt(Constants.ARG_DRAWABLE_TORSO_ID), null);
        this.mTorsoImages = getResources().obtainTypedArray(getArguments().getInt(Constants.ARG_ARRAY_TORSO_DRAWABLES_ID));

        this.mRightInitial = ResourcesCompat.getDrawable(getResources(), R.drawable.first_right_initial, null);
        this.mLeftInitial = ResourcesCompat.getDrawable(getResources(), R.drawable.first_left_initial, null);

        this.mSlapSounds = getResources().obtainTypedArray(R.array.first_slap_sounds);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // super.onCreateView(inflater, container, savedInstanceState);

        this.inflatedView = inflater.inflate(R.layout.fragment_free_slapping, container, false);

        Log.d("in fragment get view", this.inflatedView.toString());

        this.getScreenWidth();
        this.setupSensors();
        this.setupAnimationsContainers();
        this.setupUiEvents();
        this.setRandomBackGround();

        return this.inflatedView;
    }


    private void setupAnimationsContainers() {
        this.mRightButtContainer = (ImageView) this.inflatedView.findViewById(R.id.ivRightButt);
        this.mRightButtContainer.setBackground(this.mRightInitial);
        this.mRightButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        this.mLeftButtContainer = (ImageView) this.inflatedView.findViewById(R.id.ivLeftButt);
        this.mLeftButtContainer.setBackground(this.mLeftInitial);
        this.mLeftButtContainer.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        this.mTorsoContainer = (ImageView) this.inflatedView.findViewById(R.id.ivBodyTop);
        this.mTorsoContainer.setBackgroundResource(this.mTorsoImages.getResourceId(0, -1));

        this.mSlappingFrame = (FrameLayout) this.inflatedView.findViewById(R.id.slappingFrame);
        this.mActivityLayout = (FrameLayout) this.inflatedView.findViewById(R.id.freeSlapFragmentBackground);

        this.mBackgroundImages = getResources().obtainTypedArray(R.array.background_images);


        this.mBackgroundFadeInAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in);
        this.mBackgroundFadeOutAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_out);

        this.mGestureListener = (FrameLayout) this.inflatedView.findViewById(R.id.gesture_listener_container);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (this.mListener != null) {
            this.mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    void setupSensors() {
       this.mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
       this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        this.mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
    }


    void getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        this.mScreenWidth = metrics.widthPixels;
    }

    void setRandomBackGround() {
        Random rand = new Random();

        final int index = rand.nextInt(this.mBackgroundImages.length());

        this.mActivityLayout.startAnimation(this.mBackgroundFadeOutAnimation);
        this.mActivityLayout.setBackgroundResource(this.mBackgroundImages.getResourceId(index, -1));
        this.mActivityLayout.startAnimation(this.mBackgroundFadeInAnimation);
    }

    void setupUiEvents() {
        this.gLibrary = GestureLibraries.fromRawResource(this.inflatedView.getContext(), R.raw.gestures);

        if (!this.gLibrary.load()) {
            Toast.makeText(this.inflatedView.getContext(), "Error loading gestures", Toast.LENGTH_SHORT).show();
        }

        this.mGestureOverlay = (GestureOverlayView) this.inflatedView.findViewById(R.id.gesture_overlay);

        this.mGestureOverlay.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {

            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {

                ArrayList<Prediction> predictions = FreeSlappingFragment.this.gLibrary.recognize(gesture);

                if (predictions.size() > 0 && predictions.get(0).score > 1.0) {

                    Integer index = 0;

                    String action = predictions.get(0).name;

                    switch (action) {
                        case "x":
                            index = 0;
                            break;
                        case "c":
                            index = 1;
                            break;
                        case "h":
                            index = 2;
                            break;
                        case "p":
                            index = 3;
                            break;
                    }


                    FreeSlappingFragment.this.mGestureListener.setVisibility(View.GONE);

                    FreeSlappingFragment.this.mTorsoContainer.setBackgroundResource(mTorsoImages.getResourceId(index, -1));
                }
            }
        });


        final GestureDetectorCompat doubleTapGestureDetector = new GestureDetectorCompat(getActivity(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                FreeSlappingFragment.this.setRandomBackGround();
                return true;
            }


            @Override
            public void onLongPress(MotionEvent e) {

                FreeSlappingFragment.this.mGestureListener.setVisibility(View.VISIBLE);
                super.onLongPress(e);
            }
        });

        FreeSlappingFragment.this.mTorsoContainer.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                doubleTapGestureDetector.onTouchEvent(event);

                return true;
            }
        });

        final GestureDetector gestureDetector = new GestureDetector(getActivity(), new SwipeGestureDetector() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                String LOGTAG = "Custom detector";

                switch (getSlope(e1.getX(), e1.getY(), e2.getX(), e2.getY())) {
                    case 1:
                        Log.d(LOGTAG, "top");
                        FreeSlappingFragment.this.onClickFlintUp();
                        return true;
                    case 2:
                        Log.d(LOGTAG, "left");
                        FreeSlappingFragment.this.onClickFlintLeft();
                        return true;
                    case 3:
                        FreeSlappingFragment.this.onClickFlintDown();
                        Log.d(LOGTAG, "down");
                        return true;
                    case 4:
                        FreeSlappingFragment.this.onClickFlintRight();
                        Log.d(LOGTAG, "right");
                        return true;
                }

                return false;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.i("onSingleTap :", "" + e.getAction());

                float x = e.getX();

                if (x < FreeSlappingFragment.this.mScreenWidth / 2) {
                    FreeSlappingFragment.this.onClickBtnLeftButt();
                } else {
                    FreeSlappingFragment.this.onClickBtnRightButt();
                }

                return true;
            }

        });

        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getActivity(), new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                float scaleFactor = detector.getScaleFactor();

                if (scaleFactor > 1) {
                    FreeSlappingFragment.this.onClickPinchOut();
                    return true;
                } else {
                    FreeSlappingFragment.this.onClickPinchIn();
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
                        FreeSlappingFragment.this.onClickBtnLeftButt();
                        FreeSlappingFragment.this.onClickBtnRightButt();
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

        FreeSlappingFragment.this.mSlappingFrame.setOnTouchListener(scaleGestureListener);
    }

    private void onClickPinchOut() {
        this.mRightButtContainer.setBackground(this.mRightButtPinchOutAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtPinchOutAnimation);

        this.stopAllActiveAnimations("onHuc");

       this.mRightButtPinchOutAnimation.start();
       this.mLeftButtPinchOutAnimation.start();
        this.playSlapSound(6);
    }

    public void onClickPinchIn() {
        this.mRightButtContainer.setBackground(this.mRightButtPinchInAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtPinchInAnimation);

        this.stopAllActiveAnimations("onHuc");

        this.mRightButtPinchInAnimation.start();
        this.mLeftButtPinchInAnimation.start();
        this.playSlapSound(7);
    }

    private void onClickFlintUp() {
        this.mRightButtContainer.setBackground(this.mRightButtFlintUpAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtFlintUpAnimation);

        this.stopAllActiveAnimations("onHuc");

        this.mRightButtFlintUpAnimation.start();
        this.mLeftButtFlintUpAnimation.start();
        this.playSlapSound(4);
    }

    private void onClickFlintDown() {
        this.mRightButtContainer.setBackground(this.mRightButtFlintDownAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtFlintDownAnimation);

        this.stopAllActiveAnimations("onHuc");

       this.mRightButtFlintDownAnimation.start();
       this.mLeftButtFlintDownAnimation.start();
        this.playSlapSound(5);
    }

    private void onClickFlintRight() {
        this.mRightButtContainer.setBackground(this.mRightButtFlintRightAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtFlintRightAnimation);

        this.stopAllActiveAnimations("onHuc");

        this.mRightButtFlintRightAnimation.start();
        this.mLeftButtFlintRightAnimation.start();
        this.playSlapSound(2);
    }

    private void onClickFlintLeft() {
        this.mRightButtContainer.setBackground(this.mRightButtFlintLeftAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtFlintLeftAnimation);

        this.stopAllActiveAnimations("onHuc");

        this.mRightButtFlintLeftAnimation.start();
        this.mLeftButtFlintLeftAnimation.start();
        this.playSlapSound(3);
    }

    private void onClickShake() {
        this.mRightButtContainer.setBackground(this.mRightButtShakeAnimation);
        this.mLeftButtContainer.setBackground(this.mLeftButtShakeAnimation);

        this.stopAllActiveAnimations("onHuc");

        this.mRightButtShakeAnimation.start();
        this.mLeftButtShakeAnimation.start();

        long[] pattern = new long[]{75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225, 75, 75, 75, 225, 75, 225, 75, 75, 75, 75, 75, 225, 75, 225, 150, 150, 75, 75, 75, 225, 75, 375, 75, 75, 75, 75, 75, 225, 75, 225, 75, 225};
        this.playSlapSound(8);
        this.mVibrator.vibrate(pattern, -1);
    }

    private void onClickBtnRightButt() {
        this. mRightButtContainer.setBackground(this.mRightButtSlapAnimation);
        this.stopAllActiveAnimations("onRight");
        this.mRightButtSlapAnimation.start();
        this.playSlapSound(1);
    }

    private void onClickBtnLeftButt() {
        this.mLeftButtContainer.setBackground(this.mLeftButtSlapAnimation);
        this.stopAllActiveAnimations("onLeft");
        this.mLeftButtSlapAnimation.start();
        this.playSlapSound(0);
    }

    private void stopAllActiveAnimations(String clickedOn) {

        if (this.mRightButtFlintLeftAnimation != null && (this.mRightButtFlintLeftAnimation.isRunning() || this.mLeftButtFlintLeftAnimation.isRunning())) {
            this.mRightButtFlintLeftAnimation.stop();
            this.mLeftButtFlintLeftAnimation.stop();
        }

        if (this.mRightButtFlintDownAnimation != null && (this.mRightButtFlintDownAnimation.isRunning() || this.mLeftButtFlintDownAnimation.isRunning())) {
            this.mRightButtFlintDownAnimation.stop();
            this.mLeftButtFlintDownAnimation.stop();
        }

        if (this.mRightButtFlintRightAnimation != null && (this.mRightButtFlintRightAnimation.isRunning() || this.mLeftButtFlintRightAnimation.isRunning())) {
            this.mRightButtFlintRightAnimation.stop();
            this.mLeftButtFlintRightAnimation.stop();
        }

        if (this.mRightButtFlintUpAnimation != null && (this.mRightButtFlintUpAnimation.isRunning() || this.mLeftButtFlintUpAnimation.isRunning())) {
            this.mRightButtFlintUpAnimation.stop();
            this.mLeftButtFlintUpAnimation.stop();
        }

        if (this.mRightButtPinchInAnimation != null && (this.mRightButtPinchInAnimation.isRunning() || this.mLeftButtPinchInAnimation.isRunning())) {
            this.mRightButtPinchInAnimation.stop();
            this.mLeftButtPinchInAnimation.stop();
        }

        if (this.mRightButtPinchOutAnimation != null && (this.mRightButtPinchOutAnimation.isRunning() || this.mLeftButtPinchOutAnimation.isRunning())) {
            this.mRightButtPinchOutAnimation.stop();
            this.mLeftButtPinchOutAnimation.stop();
        }

        if (this.mRightButtShakeAnimation != null && (this.mRightButtShakeAnimation.isRunning() || this.mLeftButtShakeAnimation.isRunning())) {
            this.mRightButtShakeAnimation.stop();
            this.mLeftButtShakeAnimation.stop();
            this.mVibrator.cancel();
        }

        if (this.mLeftButtSlapAnimation != null && this.mLeftButtSlapAnimation.isRunning()) {
            if (!clickedOn.equals("onRight")) {
                this.mLeftButtSlapAnimation.stop();
            }
        }

        if (this.mRightButtSlapAnimation != null && this.mRightButtSlapAnimation.isRunning()) {

            if (!clickedOn.equals("onLeft")) {
                this.mRightButtSlapAnimation.stop();
            }
        }

        if (clickedOn.equals("onRight") && (this.mLeftButtSlapAnimation == null || !this.mLeftButtSlapAnimation.isRunning())) {
            this.mLeftButtContainer.setBackground(this.mLeftInitial);
        }

        if (clickedOn.equals("onLeft") && (this.mRightButtSlapAnimation == null || !this.mRightButtSlapAnimation.isRunning())) {
            this.mRightButtContainer.setBackground(this.mRightInitial);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - this.mLastUpdate) > 300) {
                long diffTime = (curTime - this.mLastUpdate);
                this.mLastUpdate = curTime;

                float speed = Math.abs(x + y + z - this.last_x - this.last_y - this.last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {

                    FreeSlappingFragment.this.onClickShake();
                }

                this.last_x = x;
                this.last_y = y;
                this.last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        super.onPause();
        this.mSensorManager.unregisterListener(this);
        this.mVibrator.cancel();
        if(this.mMediaPlayer != null && (this.mMediaPlayer.isLooping() || this.mMediaPlayer.isPlaying())){
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        this.client.connect();
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
        AppIndex.AppIndexApi.start(this.client, viewAction);
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
        AppIndex.AppIndexApi.end(this.client, viewAction);
        this.client.disconnect();
    }

    void playSlapSound(Integer index) {

        if (this.mMediaPlayer != null && (this.mMediaPlayer.isPlaying() || this.mMediaPlayer.isLooping())) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mMediaPlayer = MediaPlayer.create(getActivity(), this.mSlapSounds.getResourceId(index, -1));
        } else {
            this.mMediaPlayer = MediaPlayer.create(getActivity(), this.mSlapSounds.getResourceId(index, -1));
        }

        this.mMediaPlayer.start();
    }
}
