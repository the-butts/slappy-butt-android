package com.thebutts.slappybutt.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thebutts.slappybutt.Common.Constants;
import com.thebutts.slappybutt.FreeSlappingActivityWithFragment;
import com.thebutts.slappybutt.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameOverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameOverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameOverFragment extends Fragment {

    private View inflatedView;

    private Integer mGameSlaps;
    private TextView mPointsContainer;

    private OnFragmentInteractionListener mListener;

    public GameOverFragment() {
        // Required empty public constructor
    }

    public static GameOverFragment newInstance(Integer gameSlaps) {
        GameOverFragment fragment = new GameOverFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_GAME_SLAPS, gameSlaps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGameSlaps = getArguments().getInt(Constants.ARG_GAME_SLAPS);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflatedView = inflater.inflate(R.layout.fragment_game_over, container, false);
        this.mPointsContainer = (TextView) this.inflatedView.findViewById(R.id.tv_game_over_slaps_count_container);

        this.mPointsContainer.setText(this.mGameSlaps.toString());
        this.setupUiEvents();

        return this.inflatedView;
    }


    void setupUiEvents() {
        Button firstButton = (Button) this.inflatedView.findViewById(R.id.btn_go_game_again);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameOverFragment.this.onClickPlayAgain((Button) v);
            }
        });
    }

    private void onClickPlayAgain(Button v) {
        ((FreeSlappingActivityWithFragment) getActivity()).onNewGame();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
