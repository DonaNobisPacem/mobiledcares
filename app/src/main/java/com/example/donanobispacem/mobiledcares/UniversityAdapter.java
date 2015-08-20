package com.example.donanobispacem.mobiledcares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by donanobispacem on 8/20/15.
 */
public class UniversityAdapter extends ArrayAdapter<University> {

    private List<University> universitiesList;
    private Context context;

    public UniversityAdapter(List<University> universitiesList, Context ctx) {
        super(ctx, R.layout.universities_list, universitiesList);
        this.universitiesList = universitiesList;
        this.context = ctx;
    }

    public int getCount() {
        if (universitiesList != null)
            return universitiesList.size();
        return 0;
    }

    public University getUniversity(int position) {
        if (universitiesList != null)
            return universitiesList.get(position);
        return null;
    }

    public long getUniversityId(int position) {
        if (universitiesList != null)
            return universitiesList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.universities_list, null);
        }

        University u = universitiesList.get(position);
        TextView text = (TextView) v.findViewById(R.id.university_name);
        text.setText(u.getUniversityName());

        TextView text1 = (TextView) v.findViewById(R.id.university_code);
        text1.setText(u.getUniversityCode());

        return v;

    }

    public List<University> getUniversityList() {
        return universitiesList;
    }

    public void setUniversityList(List<University> universitiesList) {
        this.universitiesList = universitiesList;
    }
}
