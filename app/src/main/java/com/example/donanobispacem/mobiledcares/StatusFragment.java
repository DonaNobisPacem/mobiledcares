package com.example.donanobispacem.mobiledcares;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class StatusFragment extends Fragment {

    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_COMPLETED_BY = "completed_by";
    private static final String TAG_STATUS = "status";
    private static final String TAG_CLASSIFICATION = "classification";
    private static final String TAG_PERCENT_ACCOMPLISHMENT = "percent_accomplishment";

    private String updated_at;
    private String completed_by;
    private String status;
    private String classification;
    private String percent_accomplishment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updated_at = getArguments().getString(TAG_UPDATED_AT);
        completed_by = getArguments().getString(TAG_COMPLETED_BY);
        status = getArguments().getString(TAG_STATUS);
        classification = getArguments().getString(TAG_CLASSIFICATION);
        percent_accomplishment = getArguments().getString(TAG_PERCENT_ACCOMPLISHMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_page, container, false);

        TextView updatedTextView = (TextView) view.findViewById(R.id.updated);
        TextView completedTextView = (TextView) view.findViewById(R.id.completed);
        TextView statusTextView = (TextView) view.findViewById(R.id.status);
        TextView classificationTextView = (TextView) view.findViewById(R.id.classification);
        TextView percentTextView = (TextView) view.findViewById(R.id.percent);

        updatedTextView.setText(convertDate(updated_at));
        completedTextView.setText(convertDate(completed_by));
        statusTextView.setText(setStatus(status));
        classificationTextView.setText(setClassification(classification));
        percentTextView.setText(percent_accomplishment);

        return view;
    }

    private String setClassification( String id ){
        switch ( Integer.parseInt(id) ) {
            case 1:
                return "Construction of New Facility";
            case 2:
                return "Road Works and Utilities Connection";
            case 3:
                return "Renovation and Rehabilitation";
            case 4:
                return "Information and Communication Technology";
            case 5:
                return "Maintenance, Upgrading, and Enhancement";
            case 6:
                return "Architectural and Schematic Design";
            case 7:
                return "Static and Mobile Furniture";
            case 8:
                return "Fitout and Interior Design";
            case 9:
                return "Equipment and Other Non ICT Peripherals";
            default:
                return "Unspecified";
        }
    }

    private String setStatus( String id ){
        switch ( Integer.parseInt(id) ) {
            case 1:
                return "Completed";
            case 2:
                return "Ongoing";
            case 3:
                return "On Hold";
            case 4:
                return "Terminated";
            case 5:
                return "Proposal Stage";
            case 6:
                return "Bidding Stage";
            default:
                return "Unspecified";
        }
    }

    private String convertDate( String input ){
        String formatted = new String("N/A");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy MMMM dd");
        try {
            Date date = inputDateFormat.parse(input);
            formatted = outputDateFormat.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formatted;
    }

}
