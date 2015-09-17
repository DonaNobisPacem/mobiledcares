package com.example.donanobispacem.mobiledcares;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UniversitiesActivity extends AppCompatActivity {
    private static final String TAG_RESPONSE = "response";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "university_name";
    private static final String TAG_CODE = "university_code";
    private UniversityAdapter adapter;
    private String url;
    private String userToken;
    private String userId;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

    }

    @Override
    public void onResume() {
        super.onResume();

        if (mPreferences.contains("AuthToken") && mPreferences.contains("UserID")) {
            Constants constant = new Constants();
            url = constant.getUrl() + "/universities";
            getSupportActionBar().setTitle("Universities");

            adapter = new UniversityAdapter(new ArrayList<University>(), this);
            final ListView university_list = (ListView) findViewById(R.id.list);
            university_list.setAdapter( adapter );
            university_list.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int position, long id) {
                            //Toast.makeText(UniversitiesActivity.this,
                            //        "You Clicked at " + adapter.getUniversity(+position).getUniversityName(),
                            //        Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(v.getContext(), ProjectListActivity.class);
                            i.putExtra(TAG_ID, adapter.getUniversity(+position).getID());
                            i.putExtra(TAG_NAME, adapter.getUniversity(+position).getUniversityName());
                            startActivity(i);
                        }
                    }
            );

            userToken = mPreferences.getString("AuthToken", "");
            userId = mPreferences.getString("UserID", "");
            new CallAPI().execute(url, userToken, userId );
        } else {
            Intent intent = new Intent(UniversitiesActivity.this, LoginActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_universities, menu);
        return true;
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

    private class CallAPI extends AsyncTask<String, String, List<University>> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UniversitiesActivity.this);
            pDialog.setMessage("Getting University List ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected List<University> doInBackground(String... params) {
            List<University> result = new ArrayList<>();

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
                JSONArray jArr = jObj.getJSONArray(TAG_RESPONSE);
                for (int i=0; i < jArr.length(); i++) {
                    result.add(convertUniversity(jArr.getJSONObject(i)));
                }

                return result;
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<University> result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            adapter.setUniversityList(result);
            adapter.notifyDataSetChanged();
        }

        private University convertUniversity(JSONObject obj) throws JSONException {
            String _idString = obj.getString(TAG_ID);
            int _id = Integer.parseInt(_idString);
            String university_name = obj.getString(TAG_NAME);
            String university_code = obj.getString(TAG_CODE);

            return new University( _id, university_code, university_name);
        }
    }

}


