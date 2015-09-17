package com.example.donanobispacem.mobiledcares;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donanobispacem on 8/26/15.
 */
public class ProjectListAdapter extends ArrayAdapter<ProjectList> implements Filterable {

    private List<ProjectList> projectsList;
    private List<ProjectList> projectsListFiltered;
    private Context context;
    private ItemFilter mFilter = new ItemFilter();

    public ProjectListAdapter(List<ProjectList> projectsList, Context ctx) {
        super(ctx, R.layout.projects_list, projectsList);
        this.projectsList = projectsList;
        this.projectsListFiltered = projectsList;
        this.context = ctx;
    }

    public int getCount() {
        if (projectsListFiltered != null)
            return projectsListFiltered.size();
        return 0;
    }

    public ProjectList getProject(int position) {
        if (projectsListFiltered != null)
            return projectsListFiltered.get(position);
        return null;
    }

    public long getProjectId(int position) {
        if (projectsListFiltered != null)
            return projectsListFiltered.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.projects_list, null);
        }

        ProjectList p = projectsListFiltered.get(position);
        TextView text = (TextView) v.findViewById(R.id.project_name);
        text.setText(p.getProjectName());

        TextView text1 = (TextView) v.findViewById(R.id.project_code);
        text1.setText(p.getProjectCode());

        return v;

    }

    public List<ProjectList> getProjectList() {
        return projectsListFiltered;
    }

    public void setProjectList(List<ProjectList> projectsList) {
        this.projectsListFiltered = projectsList;
        this.projectsList = projectsList;
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            int count = projectsList.size();
            final ArrayList<ProjectList> mListResult = new ArrayList<>();

            String name;

            for (int i = 0; i < count; i++) {

                ProjectList mProjectListObject = projectsList.get(i);
                name = mProjectListObject.getProjectName();

                if (name.toLowerCase().contains(filterString)) {
                    mListResult.add(mProjectListObject);
                }
            }

            results.values = mListResult;
            results.count = mListResult.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            projectsListFiltered = (ArrayList<ProjectList>) results.values;
            notifyDataSetChanged();
        }
    }

}