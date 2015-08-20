package com.example.donanobispacem.mobiledcares;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        getSupportActionBar().setTitle("Universities");

        String url = "http://52.74.232.161/api/1/universities";

        adapter = new UniversityAdapter(new ArrayList<University>(), this);
        ListView university_list = (ListView) findViewById(R.id.university_list);
        university_list.setAdapter( adapter );

        new CallAPI().execute(url);
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
        if (id == R.id.action_settings) {
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

                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");

                conn.connect();
                InputStream is = conn.getInputStream();

                // Read the stream
                byte[] b = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                while ( is.read(b) != -1)
                    baos.write(b);

                String JSONResp = new String(baos.toByteArray());

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


