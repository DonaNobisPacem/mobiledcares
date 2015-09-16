package com.example.donanobispacem.mobiledcares;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class PhaseFragment extends Fragment {

    private static final String TAG_PROJECT_PHASES = "project_phases";

    ArrayList<String> phases = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phases = getArguments().getStringArrayList(TAG_PROJECT_PHASES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phase_page, container, false);
        TextView componentTextView = (TextView) view.findViewById(R.id.phases);
        componentTextView.setText(convertList(phases));
        return view;
    }

    private String convertList( ArrayList<String> source ){
        String result = "";

        if( source.size() > 0 ) {
            for (int i = 0; i < source.size(); i++) {
                result = result + source.get(i);
                if( i < source.size() - 1 ) result = result + "\n";
            }
        }
        else result = "N/A";

        return result;
    }
}
