package com.example.donanobispacem.mobiledcares;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class FinancialFragment extends Fragment {

    private static final String TAG_FINANCIAL_BUDGET = "financial_budget";
    private static final String TAG_FINANCIAL_CONTRACT_PRICE = "financial_contract_price";
    private static final String TAG_FINANCIAL_ACTUAL_COST = "financial_actual_cost";
    private static final String TAG_FINANCIAL_SOURCE = "financial_source";
    private static final String TAG_FINANCIAL_VARIATION = "financial_variation";
    private static final String TAG_FINANCIAL_REMARKS = "financial_remarks";
    private static final String TAG_FUND_SOURCE = "funds";
    private static final String TAG_FUND_SOURCE_BUDGET = "funds_budget";

    private String financial_budget;
    private String financial_contract_price;
    private String financial_actual_cost;
    private String financial_source;
    private String financial_variation;
    private String financial_remarks;
    ArrayList<String> fund_source = new ArrayList<>();
    ArrayList<String> fund_source_budget = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        financial_budget = getArguments().getString(TAG_FINANCIAL_BUDGET);
        financial_contract_price = getArguments().getString(TAG_FINANCIAL_CONTRACT_PRICE);
        financial_actual_cost = getArguments().getString(TAG_FINANCIAL_ACTUAL_COST);
        financial_source = getArguments().getString(TAG_FINANCIAL_SOURCE);
        financial_variation = getArguments().getString(TAG_FINANCIAL_VARIATION);
        financial_remarks = getArguments().getString(TAG_FINANCIAL_REMARKS);
        fund_source = getArguments().getStringArrayList(TAG_FUND_SOURCE);
        fund_source_budget = getArguments().getStringArrayList(TAG_FUND_SOURCE_BUDGET);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_financial_page, container, false);

        TextView budgetTextView = (TextView) view.findViewById(R.id.budget);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView costTextView = (TextView) view.findViewById(R.id.cost);
        TextView sourceTextView = (TextView) view.findViewById(R.id.source);
        TextView variationTextView = (TextView) view.findViewById(R.id.variation);
        TextView remarksTextView = (TextView) view.findViewById(R.id.remarks);

        budgetTextView.setText(financial_budget);
        priceTextView.setText(financial_contract_price);
        costTextView.setText(financial_actual_cost);
        sourceTextView.setText(combineSource( fund_source, fund_source_budget ));
        variationTextView.setText(financial_variation);
        remarksTextView.setText(financial_remarks);

        return view;
    }

    private String combineSource( ArrayList<String> source1, ArrayList<String> source2 ){
        String string1;
        String string2;
        String result = "";

        if( source1.size() == 0 ) return "N/A";

        for( int i = 0; i < source1.size(); i++ ){
            string1 = source1.get(i);
            string2 = source2.get(i);
            result = result + "-" + string1 + "\n--PhP " + string2;
            if( i < source1.size() - 1 ) result = result + "\n";
        }

        return result;
    }

}
