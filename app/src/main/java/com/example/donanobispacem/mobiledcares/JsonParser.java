package com.example.donanobispacem.mobiledcares;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JsonParser()
    {
    }

    public JSONObject getJSONFromUrl(String urlString)
    {
        // Making HTTP request
        try
        {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            is = new BufferedInputStream(conn.getInputStream());
            Log.e("Buffer CheckPoint", "Obtained Connection");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    is, "iso-8859-1"), 8);
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "n");
//            }

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n>0)
            {
                byte[] b = new byte[4096];
                n =  is.read(b);
                if (n>0) out.append(new String(b, 0, n));
            }
            is.close();
            json = out.toString();
            Log.e("Buffer CheckPoint", "Obtained json string " + json);
        }
        catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try
        {
            Log.e("Buffer Checkpoint", "convert string into object");
            jObj = new JSONObject(json);
            Log.e("Buffer Checkpoint", "return object");
        } catch (JSONException e)
        {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }
}
