package com.example.donanobispacem.mobiledcares;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProjectDetailActivity extends AppCompatActivity {

    private static final String TAG_RESPONSE = "response";
    private static final String TAG_ID = "id";
    private static final String TAG_PROJECT_NAME = "project_name";
    private static final String TAG_PROJECT_CODE = "project_code";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_EXPECTED_OUTCOME = "expected_outcome";
    private static final String TAG_END_USER = "end_user";
    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_COMPLETED_BY = "completed_by";
    private static final String TAG_STATUS = "status";
    private static final String TAG_CLASSIFICATION = "classification";
    private static final String TAG_REMARKS = "remarks";
    private static final String TAG_PERCENT_ACCOMPLISHMENT = "percent_accomplishment";
    private static final String TAG_PERCENT_ACCOMPLISHMENT_BY = "percent_accomplishment_by";
    private static final String TAG_BIDDING_CONTRACTOR = "bidding_contractor";
    private static final String TAG_BIDDING_NUMBER = "bidding_number";
    private static final String TAG_BIDDING_AWARD = "bidding_award";
    private static final String TAG_BIDDING_PROCEED = "bidding_proceed";
    private static final String TAG_BIDDING_REMARKS = "bidding_remarks";
    private static final String TAG_FINANCIAL_BUDGET = "financial_budget";
    private static final String TAG_FINANCIAL_CONTRACT_PRICE = "financial_contract_price";
    private static final String TAG_FINANCIAL_ACTUAL_COST = "financial_actual_cost";
    private static final String TAG_FINANCIAL_SOURCE = "financial_source";
    private static final String TAG_FINANCIAL_VARIATION = "financial_variation";
    private static final String TAG_FINANCIAL_REMARKS = "financial_remarks";
    private static final String TAG_TIMELINE_TARGET_START = "timeline_target_start";
    private static final String TAG_TIMELINE_TARGET_END = "timeline_target_end";
    private static final String TAG_TIMELINE_ACTUAL_START = "timeline_actual_start";
    private static final String TAG_TIMELINE_ACTUAL_END = "timeline_actual_end";
    private static final String TAG_TIMELINE_DURATION = "timeline_duration";
    private static final String TAG_TIMELINE_EXTENSION = "timeline_extension";
    private static final String TAG_TIMELINE_REMARKS = "timeline_remarks";
    private static final String TAG_FUND_SOURCES = "fund_sources";
    private static final String TAG_PROJECT_COMPONENTS = "project_components";
    private static final String TAG_PROJECT_PHASES = "project_phases";

    private int project_id;
    private String project_name;
    private String url;
    private String userToken;
    private String userId;

    private ViewPager viewPager;
    private ProjectTabsAdapter mAdapter;
    private TabLayout tabLayout;

    private Project project;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mPreferences.contains("AuthToken") && mPreferences.contains("UserID")) {

            Intent i = getIntent();
            project_id = i.getIntExtra(TAG_ID, 0);
            project_name = i.getStringExtra(TAG_PROJECT_NAME);

            Constants constant = new Constants();
            url = constant.getUrl() + "/projects/" + String.valueOf(project_id);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(project_name);

            userToken = mPreferences.getString("AuthToken", "");
            userId = mPreferences.getString("UserID", "");
            new CallAPI().execute(url, userToken, userId );

        } else {
            Intent intent = new Intent(ProjectDetailActivity.this, LoginActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            new CallAPI().execute(url, userToken, userId );
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class CallAPI extends AsyncTask<String, String, Project> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProjectDetailActivity.this);
            pDialog.setMessage("Getting Details ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Project doInBackground(String... params) {
            try {
                URL u = new URL(params[0]);
                String userToken = new String(params[1]);
                String userId = new String(params[2]);

                String basicAuth = "Token token=\"" + userToken + "\", user_id=\"" + userId + "\"";

                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", basicAuth);

                conn.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String JSONResp;
                while ((JSONResp = br.readLine()) != null) {
                    sb.append(JSONResp);
                }
                br.close();
                JSONResp = sb.toString();

                JSONObject jObj = new JSONObject(JSONResp);
                JSONObject jProject = jObj.getJSONObject(TAG_RESPONSE);

                Project result = convertProject( jProject );

                return result;
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Project result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            project= result;

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            mAdapter = new ProjectTabsAdapter( project, getSupportFragmentManager(), ProjectDetailActivity.this);
            viewPager.setAdapter(mAdapter);

            tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(viewPager);

        }

        private Project convertProject(JSONObject obj) throws JSONException {
            String _idString = obj.getString(TAG_ID);

            Project project = new Project();

            project.setID(Integer.parseInt(_idString));
            project.setProjectName(obj.getString(TAG_PROJECT_NAME));
            project.setProjectCode(obj.getString(TAG_PROJECT_CODE));
            project.setDescription(obj.getString(TAG_DESCRIPTION));
            project.setExpectedOutcome(obj.getString(TAG_EXPECTED_OUTCOME));
            project.setEndUser(obj.getString(TAG_END_USER));
            project.setUpdatedAt(obj.getString(TAG_UPDATED_AT));
            project.setCompletedBy(obj.getString(TAG_COMPLETED_BY));
            project.setStatus(obj.getString(TAG_STATUS));
            project.setClassification(obj.getString(TAG_CLASSIFICATION));
            project.setRemarks(obj.getString(TAG_REMARKS));
            project.setPercentAccomplishment(obj.getString(TAG_PERCENT_ACCOMPLISHMENT));
            project.setPercentAccomplishmentBy(obj.getString(TAG_PERCENT_ACCOMPLISHMENT_BY));
            project.setBiddingContractor(obj.getString(TAG_BIDDING_CONTRACTOR));
            project.setBiddingNumber(obj.getString(TAG_BIDDING_NUMBER));
            project.setBiddingAward(obj.getString(TAG_BIDDING_AWARD));
            project.setBiddingProceed(obj.getString(TAG_BIDDING_PROCEED));
            project.setBiddingRemarks(obj.getString(TAG_BIDDING_REMARKS));
            project.setFinancialBudget(obj.getString(TAG_FINANCIAL_BUDGET));
            project.setFinancialContractPrice(obj.getString(TAG_FINANCIAL_CONTRACT_PRICE));
            project.setFinancialActualCost(obj.getString(TAG_FINANCIAL_ACTUAL_COST));
            project.setFinancialSource(obj.getString(TAG_FINANCIAL_SOURCE));
            project.setFinancialVariation(obj.getString(TAG_FINANCIAL_VARIATION));
            project.setFinancialRemarks(obj.getString(TAG_FINANCIAL_REMARKS));
            project.setTimelineTargetStart(obj.getString(TAG_TIMELINE_TARGET_START));
            project.setTimelineTargetEnd(obj.getString(TAG_TIMELINE_TARGET_END));
            project.setTimelineActualStart(obj.getString(TAG_TIMELINE_ACTUAL_START));
            project.setTimelineActualEnd(obj.getString(TAG_TIMELINE_ACTUAL_END));
            project.setTimelineDuration(obj.getString(TAG_TIMELINE_DURATION));
            project.setTimelineExtension(obj.getString(TAG_TIMELINE_EXTENSION));
            project.setTimelineRemarks(obj.getString(TAG_TIMELINE_REMARKS));

            ArrayList<String> fund_source = new ArrayList<>();
            JSONArray jArrFS = obj.getJSONArray(TAG_FUND_SOURCES);
            for (int i=0; i < jArrFS.length(); i++) {
                fund_source.add(jArrFS.getJSONObject(i).getString("source_name"));
            }
            project.setFundSource( fund_source );

            ArrayList<String> components = new ArrayList<>();
            JSONArray jArrComp = obj.getJSONArray(TAG_PROJECT_COMPONENTS);
            for (int i=0; i < jArrComp.length(); i++) {
                components.add(jArrComp.getJSONObject(i).getString("component_name"));
            }
            project.setComponents( components );

            ArrayList<String> phases = new ArrayList<>();
            JSONArray jArrPhase = obj.getJSONArray(TAG_PROJECT_PHASES);
            for (int i=0; i < jArrPhase.length(); i++) {
                phases.add(jArrPhase.getJSONObject(i).getString("phase_name"));
            }
            project.setPhases( phases );

            return project;
        }

    }
}
