package com.example.donanobispacem.mobiledcares;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class GeneralFragment extends Fragment {

    private static final String TAG_PROJECT_NAME = "project_name";
    private static final String TAG_PROJECT_CODE = "project_code";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_EXPECTED_OUTCOME = "expected_outcome";
    private static final String TAG_END_USER = "end_user";
    private static final String TAG_REMARKS = "remarks";

    private String project_name;
    private String project_code;
    private String description;
    private String expected_outcome;
    private String end_user;
    private String remarks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        project_name = getArguments().getString(TAG_PROJECT_NAME);
        project_code = getArguments().getString(TAG_PROJECT_CODE);
        description = getArguments().getString(TAG_DESCRIPTION);
        expected_outcome = getArguments().getString(TAG_EXPECTED_OUTCOME);
        end_user = getArguments().getString(TAG_END_USER);
        remarks = getArguments().getString(TAG_REMARKS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_page, container, false);

        TextView nameTextView = (TextView) view.findViewById(R.id.project_name);
        TextView codeTextView = (TextView) view.findViewById(R.id.project_code);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.description);
        TextView outcomeTextView = (TextView) view.findViewById(R.id.expected_outcome);
        TextView userTextView = (TextView) view.findViewById(R.id.end_user);
        TextView remarksTextView = (TextView) view.findViewById(R.id.remarks);

        nameTextView.setText(project_name);
        codeTextView.setText(project_code);
        descriptionTextView.setText(description);
        outcomeTextView.setText(expected_outcome);
        userTextView.setText(end_user);
        remarksTextView.setText(remarks);

        return view;
    }
}
