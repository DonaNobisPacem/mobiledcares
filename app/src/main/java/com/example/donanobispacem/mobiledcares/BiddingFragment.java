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
public class BiddingFragment extends Fragment {

    private static final String TAG_BIDDING_CONTRACTOR = "bidding_contractor";
    private static final String TAG_BIDDING_NUMBER = "bidding_number";
    private static final String TAG_BIDDING_PREPROCUREMENT = "bidding_preprocurement";
    private static final String TAG_BIDDING_PREBIDDING = "bidding_prebidding";
    private static final String TAG_BIDDING_BIDDING = "bidding_bidding";
    private static final String TAG_BIDDING_POSTQUALI = "bidding_postquali";
    private static final String TAG_BIDDING_AWARD = "bidding_award";
    private static final String TAG_BIDDING_PURCHASE = "bidding_purchase";
    private static final String TAG_BIDDING_PROCEED = "bidding_proceed";
    private static final String TAG_BIDDING_REMARKS = "bidding_remarks";

    private String bidding_contractor;
    private String bidding_number;
    private String bidding_preprocurement;
    private String bidding_prebidding;
    private String bidding_bidding;
    private String bidding_postquali;
    private String bidding_award;
    private String bidding_purchase;
    private String bidding_proceed;
    private String bidding_remarks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bidding_contractor = getArguments().getString(TAG_BIDDING_CONTRACTOR);
        bidding_number = getArguments().getString(TAG_BIDDING_NUMBER);
        bidding_preprocurement = getArguments().getString(TAG_BIDDING_PREPROCUREMENT);
        bidding_prebidding = getArguments().getString(TAG_BIDDING_PREBIDDING);
        bidding_bidding = getArguments().getString(TAG_BIDDING_BIDDING);
        bidding_postquali = getArguments().getString(TAG_BIDDING_POSTQUALI);
        bidding_award = getArguments().getString(TAG_BIDDING_AWARD);
        bidding_purchase = getArguments().getString(TAG_BIDDING_PURCHASE);
        bidding_proceed = getArguments().getString(TAG_BIDDING_PROCEED);
        bidding_remarks = getArguments().getString(TAG_BIDDING_REMARKS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bidding_page, container, false);

        TextView contractorTextView = (TextView) view.findViewById(R.id.contractor);
        TextView numberTextView = (TextView) view.findViewById(R.id.number);
        TextView preprocurementTextView = (TextView) view.findViewById(R.id.preprocurement);
        TextView prebiddingTextView = (TextView) view.findViewById(R.id.prebidding);
        TextView biddingTextView = (TextView) view.findViewById(R.id.bidding);
        TextView postqualiTextView = (TextView) view.findViewById(R.id.postquali);
        TextView awardTextView = (TextView) view.findViewById(R.id.award);
        TextView purchaseTextView = (TextView) view.findViewById(R.id.purchase);
        TextView proceedTextView = (TextView) view.findViewById(R.id.proceed);
        TextView period1TextView = (TextView) view.findViewById(R.id.period1);
        TextView period2TextView = (TextView) view.findViewById(R.id.period2);
        TextView period3TextView = (TextView) view.findViewById(R.id.period3);
        TextView period4TextView = (TextView) view.findViewById(R.id.period4);
        TextView period5TextView = (TextView) view.findViewById(R.id.period5);
        TextView period6TextView = (TextView) view.findViewById(R.id.period6);
        TextView remarksTextView = (TextView) view.findViewById(R.id.remarks);

        contractorTextView.setText(bidding_contractor);
        numberTextView.setText(bidding_number);
        preprocurementTextView.setText(convertDate(bidding_preprocurement));
        prebiddingTextView.setText(convertDate(bidding_prebidding));
        biddingTextView.setText(convertDate(bidding_bidding));
        postqualiTextView.setText(convertDate(bidding_postquali));
        awardTextView.setText(convertDate(bidding_award));
        purchaseTextView.setText(convertDate(bidding_purchase));
        proceedTextView.setText(convertDate(bidding_proceed));
        period1TextView.setText(getDifferenceDays(bidding_preprocurement, bidding_prebidding));
        period2TextView.setText(getDifferenceDays(bidding_prebidding, bidding_bidding));
        period3TextView.setText(getDifferenceDays(bidding_bidding, bidding_postquali));
        period4TextView.setText(getDifferenceDays(bidding_postquali, bidding_award));
        period5TextView.setText(getDifferenceDays(bidding_award, bidding_purchase));
        period6TextView.setText(getDifferenceDays(bidding_purchase, bidding_proceed));
        remarksTextView.setText(bidding_remarks);

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

        if(d1 != null && !d1.isEmpty() && !d1.equals("null") && d2 != null && !d2.isEmpty() && !d2.equals("null"))
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
