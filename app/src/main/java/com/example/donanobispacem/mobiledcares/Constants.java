package com.example.donanobispacem.mobiledcares;

/**
 * Created by donanobispacem on 9/2/15.
 */
public class Constants {
    private static final String api_version = "1";
    private static final String url = "http://52.74.232.161/api/";

    public Constants()
    {}

    public String getUrl(){
        return url + api_version;
    }
}
