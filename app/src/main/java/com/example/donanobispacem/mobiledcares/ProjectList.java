package com.example.donanobispacem.mobiledcares;

import java.io.Serializable;

/**
 * Created by donanobispacem on 8/26/15.
 */
public class ProjectList implements Serializable {

    private String project_name;
    private String project_code;
    private int _id;

    public ProjectList(int _id, String project_code, String project_name ) {
        super();
        this._id = _id;
        this.project_code = project_code;
        this.project_name = project_name;
    }

    public int getID(){
        return _id;
    }

    public String getProjectName(){
        return project_name;
    }

    public String getProjectCode(){
        return project_code;
    }

    public void setID(int _newID) {
        _id = _newID;
    }

    public void setProjectName(String newProjectName) {
        project_name = newProjectName;
    }

    public void setProjectCode(String newProjectCode) {
        project_code = newProjectCode;
    }
}