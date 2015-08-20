package com.example.donanobispacem.mobiledcares;

import java.io.Serializable;

/**
 * Created by donanobispacem on 8/20/15.
 */
public class University implements Serializable {

    private String university_name;
    private String university_code;
    private int _id;

    public University(int _id, String university_code, String university_name ) {
        super();
        this._id = _id;
        this.university_code = university_code;
        this.university_name = university_name;
    }

    public int getID(){
        return _id;
    }

    public String getUniversityName(){
        return university_name;
    }

    public String getUniversityCode(){
        return university_code;
    }

    public void setID(int _newID) {
        _id = _newID;
    }

    public void setUniversityName(String newUniversityName) {
        university_name = newUniversityName;
    }

    public void setUniversityCode(String newUniversityCode) {
        university_code = newUniversityCode;
    }
}