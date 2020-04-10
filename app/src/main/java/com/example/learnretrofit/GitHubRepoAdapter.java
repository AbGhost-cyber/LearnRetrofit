package com.example.learnretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GitHubRepoAdapter extends ArrayAdapter<GithubRepo> {


    private Context context;
    private List<GithubRepo> values;

    public GitHubRepoAdapter(Context context, List<GithubRepo> values) {
        super(context, android.R.layout.simple_list_item_1, values);

        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                row = inflater.inflate(R.layout.simple_stuff, parent, false);
            }
        }

        TextView textView =  row.findViewById(R.id.list_text);

        GithubRepo item = values.get(position);
        String message = item.getName();
        textView.setText(message);

        return row;
    }
}