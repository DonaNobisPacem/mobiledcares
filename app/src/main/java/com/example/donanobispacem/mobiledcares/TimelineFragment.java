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
import java.util.concurrent.TimeUnit;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class TimelineFragment extends Fragment {

    private static final String TAG_TIMELINE_TARGET_START = "timeline_target_start";
    private static final String TAG_TIMELINE_TARGET_END = "timeline_target_end";
    private static final String TAG_TIMELINE_ACTUAL_START = "timeline_actual_start";
    private static final String TAG_TIMELINE_ACTUAL_END = "timeline_actual_end";
    private static final String TAG_TIMELINE_DURATION = "timeline_duration";
    private static final String TAG_TIMELINE_EXTENSION = "timeline_extension";
    private static final String TAG_TIMELINE_REMARKS = "timeline_remarks";

    private String timeline_target_start;
    private String timeline_target_end;
    private String timeline_actual_start;
    private String timeline_actual_end;
    private String timeline_duration;
    private String timeline_extension;
    private String timeline_remarks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeline_target_start = getArguments().getString(TAG_TIMELINE_TARGET_START);
        timeline_target_end = getArguments().getString(TAG_TIMELINE_TARGET_END);
        timeline_actual_start = getArguments().getString(TAG_TIMELINE_ACTUAL_START);
        timeline_actual_end = getArguments().getString(TAG_TIMELINE_ACTUAL_END);
        timeline_duration = getArguments().getString(TAG_TIMELINE_DURATION);
        timeline_extension = getArguments().getString(TAG_TIMELINE_EXTENSION);
        timeline_remarks = getArguments().getString(TAG_TIMELINE_REMARKS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline_page, container, false);

        TextView targetStartTextView = (TextView) view.findViewById(R.id.target_start);
        TextView targetEndTextView = (TextView) view.findViewById(R.id.target_end);
        TextView actualStartTextView = (TextView) view.findViewById(R.id.actual_start);
        TextView actualEndTextView = (TextView) view.findViewById(R.id.actual_end);
        TextView durationTextView = (TextView) view.findViewById(R.id.duration);
        TextView extensionTextView = (TextView) view.findViewById(R.id.extension);
        TextView remarksTextView = (TextView) view.findViewById(R.id.remarks);

        targetStartTextView.setText(convertDate(timeline_target_start));
        targetEndTextView.setText(convertDate(timeline_target_end));
        actualStartTextView.setText(convertDate(timeline_actual_start));
        actualEndTextView.setText(convertDate(timeline_actual_end));
        durationTextView.setText(getDifferenceDays( timeline_actual_start, timeline_actual_end ));
        extensionTextView.setText(timeline_extension + " days");
        remarksTextView.setText(timeline_remarks);

        return view;
    }

    private String convertDate( String input ){
        String formatted = new String("N/A");
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy");

        if( input != null && !input.isEmpty() && !input.equals("null") ) {
            try {
                Date date = inputDateFormat.parse(input);
                formatted = outputDateFormat.format(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return formatted;
    }

    private String getDifferenceDays(String d1, String d2) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        long diff;
        String output = new String();

        if(d1 != null && !d1.equals("null") && !d1.isEmpty() && d2 != null && !d2.equals("null") && !d2.isEmpty())
            try {
                Date date1 = inputDateFormat.parse(d1);
                Date date2 = inputDateFormat.parse(d2);

                diff = date2.getTime() - date1.getTime();
                output = Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) ) + " days";
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        else output = "N/A";

        return output;
    }
}
