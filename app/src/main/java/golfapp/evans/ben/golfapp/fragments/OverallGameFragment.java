package golfapp.evans.ben.golfapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import golfapp.evans.ben.golfapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverallGameFragment extends Fragment {


    public OverallGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overall_game, container, false);
    }

}
