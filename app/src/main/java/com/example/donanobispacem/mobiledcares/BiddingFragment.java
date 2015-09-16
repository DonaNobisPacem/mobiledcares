package com.example.donanobispacem.mobiledcares;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class BiddingFragment extends Fragment {

    private static final String TAG_BIDDING_CONTRACTOR = "bidding_contractor";
    private static final String TAG_BIDDING_NUMBER = "bidding_number";
    private static final String TAG_BIDDING_AWARD = "bidding_award";
    private static final String TAG_BIDDING_PROCEED = "bidding_proceed";
    private static final String TAG_BIDDING_REMARKS = "bidding_remarks";

    private String bidding_contractor;
    private String bidding_number;
    private String bidding_award;
    private String bidding_proceed;
    private String bidding_remarks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bidding_contractor = getArguments().getString(TAG_BIDDING_CONTRACTOR);
        bidding_number = getArguments().getString(TAG_BIDDING_NUMBER);
        bidding_award = getArguments().getString(TAG_BIDDING_AWARD);
        bidding_proceed = getArguments().getString(TAG_BIDDING_PROCEED);
        bidding_remarks = getArguments().getString(TAG_BIDDING_REMARKS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bidding_page, container, false);

        TextView contractorTextView = (TextView) view.findViewById(R.id.contractor);
        TextView numberTextView = (TextView) view.findViewById(R.id.number);
        TextView awardTextView = (TextView) view.findViewById(R.id.award);
        TextView proceedTextView = (TextView) view.findViewById(R.id.proceed);
        TextView periodTextView = (TextView) view.findViewById(R.id.period);
        TextView remarksTextView = (TextView) view.findViewById(R.id.remarks);

        contractorTextView.setText(bidding_contractor);
        numberTextView.setText(bidding_number);
        awardTextView.setText(convertDate(bidding_award));
        proceedTextView.setText(convertDate(bidding_proceed));
        periodTextView.setText(getDifferenceDays(bidding_award, bidding_proceed));
        remarksTextView.setText(bidding_remarks);

        return view;
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

    private String getDifferenceDays(String d1, String d2) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        long diff;
        String output = new String();

        if(d1 != null && !d1.isEmpty() && d2 != null && !d2.isEmpty())
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
