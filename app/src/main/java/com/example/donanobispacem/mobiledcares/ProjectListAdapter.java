package com.example.donanobispacem.mobiledcares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by donanobispacem on 8/26/15.
 */
public class ProjectListAdapter extends ArrayAdapter<ProjectList> {

    private List<ProjectList> projectsList;
    private Context context;

    public ProjectListAdapter(List<ProjectList> projectsList, Context ctx) {
        super(ctx, R.layout.projects_list, projectsList);
        this.projectsList = projectsList;
        this.context = ctx;
    }

    public int getCount() {
        if (projectsList != null)
            return projectsList.size();
        return 0;
    }

    public ProjectList getProject(int position) {
        if (projectsList != null)
            return projectsList.get(position);
        return null;
    }

    public long getProjectId(int position) {
        if (projectsList != null)
            return projectsList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.projects_list, null);
        }

        ProjectList p = projectsList.get(position);
        TextView text = (TextView) v.findViewById(R.id.project_name);
        text.setText(p.getProjectName());

        TextView text1 = (TextView) v.findViewById(R.id.project_code);
        text1.setText(p.getProjectCode());

        return v;

    }

    public List<ProjectList> getProjectList() {
        return projectsList;
    }

    public void setProjectList(List<ProjectList> projectsList) {
        this.projectsList = projectsList;
    }
}