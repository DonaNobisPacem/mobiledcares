package com.example.donanobispacem.mobiledcares;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
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

public class ProjectListActivity extends AppCompatActivity {

    private static final String TAG_RESPONSE = "response";
    private static final String TAG_ID = "id";
    private static final String TAG_UNIVERSITY_NAME = "university_name";
    private static final String TAG_PROJECT_NAME = "project_name";
    private static final String TAG_PROJECT_CODE = "project_code";
    private int university_id;
    private String university_name;
    private ProjectListAdapter adapter;
    private String url;
    private String userToken;
    private String userId;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mPreferences.contains("AuthToken") && mPreferences.contains("UserID")) {

            Intent i = getIntent();
            university_id = i.getIntExtra( TAG_ID, 0 );
            university_name = i.getStringExtra(TAG_UNIVERSITY_NAME);

            url = "http://52.74.232.161/api/1/universities/" + String.valueOf(university_id);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle( university_name + " Project List");

            adapter = new ProjectListAdapter(new ArrayList<ProjectList>(), this);
            final ListView project_list = (ListView) findViewById(R.id.list);
            project_list.setAdapter(adapter);
            project_list.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int position, long id) {
//                        Toast.makeText(ProjectListActivity.this,
//                                "You Clicked at " + adapter.getProject(+position).getProjectName(),
//                                Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(v.getContext(), ProjectDetailActivity.class);
                            i.putExtra(TAG_ID, adapter.getProject(+position).getID());
                            i.putExtra(TAG_PROJECT_NAME, adapter.getProject(+position).getProjectName());
                            startActivity(i);
                        }
                    }
            );

            userToken = mPreferences.getString("AuthToken", "");
            userId = mPreferences.getString("UserID", "");
            new CallAPI().execute(url, userToken, userId );

        } else {
            Intent intent = new Intent(ProjectListActivity.this, LoginActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        SearchView mSearchView;
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        mSearchView.setQueryHint("Search Here");
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

    private class CallAPI extends AsyncTask<String, String, List<ProjectList>> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ProjectListActivity.this);
            pDialog.setMessage("Getting Project List ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected List<ProjectList> doInBackground(String... params) {
            List<ProjectList> result = new ArrayList<>();

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
                    result.add(convertProjectList(jArr.getJSONObject(i)));
                }

                return result;
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ProjectList> result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            adapter.setProjectList(result);
            adapter.notifyDataSetChanged();
        }

        private ProjectList convertProjectList(JSONObject obj) throws JSONException {
            String _idString = obj.getString(TAG_ID);
            int _id = Integer.parseInt(_idString);
            String project_name = obj.getString(TAG_PROJECT_NAME);
            String project_code = obj.getString(TAG_PROJECT_CODE);

            return new ProjectList( _id, project_code, project_name);
        }
    }
}
