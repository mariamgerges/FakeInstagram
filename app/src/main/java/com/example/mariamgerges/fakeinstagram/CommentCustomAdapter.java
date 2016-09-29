package com.example.mariamgerges.fakeinstagram;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CommentCustomAdapter extends ArrayAdapter<CommentData> {
    private ArrayList<CommentData> comments = new ArrayList<CommentData>();
    private final Activity context;
    public CommentCustomAdapter(Activity context, ArrayList<CommentData> comments) {
        super(context, R.layout.small_list_layout, comments);
        this.context = context;
        this.comments=comments;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.small_list_layout, null, true);
        TextView commenter = (TextView) rowView.findViewById(R.id.commenter);
        commenter.setText(comments.get(position).commenter);
        TextView comment = (TextView) rowView.findViewById(R.id.comment);
        comment.setText(comments.get(position).comment);
        return rowView;

    }

}
