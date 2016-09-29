package com.example.mariamgerges.fakeinstagram;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/* This class has the implementation of the custom adapter that will fit the data of the newsfeed to
  the listview. It creates each row in the listview in the newsfeed page; each row has the username
  and the picture of the user posting an image, the image s/he posted, image button for liking
  commenting and sending the post, the number of likes for this post, the comments posted and when
  the iumage was posted. So this adapter receives an array list that has the data to be put in each
  row and it will fill it to the row. It fits to an xml file called list_layout that has the design
  for each row; it has buttons, images and imagebuttons for the data of each user to be viewed*/

public class RowCustomAdapter extends ArrayAdapter<RowData> {
    private ArrayList<RowData> rowData = new ArrayList<RowData>();
    private final Activity context;
    public RowCustomAdapter(Activity context, ArrayList<RowData> rowData) {
        super(context, R.layout.list_layout, rowData);
        this.context = context;
        this.rowData=rowData;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_layout, null, true);
        RowData p = getItem(position);

        if (p != null) {
            TextView profileName = (TextView) rowView.findViewById(R.id.profileName);
            ImageView profilePicture = (ImageView) rowView.findViewById(R.id.profilePicture);
            ImageView postPicture = (ImageView) rowView.findViewById(R.id.postPicture);
            ImageButton likeButton = (ImageButton) rowView.findViewById(R.id.likeButton);
            TextView likesNumber = (TextView) rowView.findViewById(R.id.likesNumber);
            ImageButton commentButton = (ImageButton) rowView.findViewById(R.id.commentButton);
            ImageButton sendButton = (ImageButton) rowView.findViewById(R.id.sendButton);
            ListView list = (ListView) rowView.findViewById(R.id.commentList);
            TextView postDate = (TextView) rowView.findViewById(R.id.dateOfPost);
            profileName.setText(rowData.get(position).profileName);
            likesNumber.setText(rowData.get(position).likeNumber + "");
            postDate.setText(rowData.get(position).dateOfPost);
            profilePicture.setImageResource(rowData.get(position).profileImage);
            postPicture.setImageResource(rowData.get(position).postPicture);
            likeButton.setImageResource(rowData.get(position).likeButton);
            commentButton.setImageResource(R.drawable.commentbutton);

            //for each row, the send image button has tag that will help when the like button is
            //clicked it has the position of this item in the array to be able to access it and
            // edit the data, and the comment image button has the number of likes for this image

            commentButton.setTag(rowData.get(position).likeNumber);
            sendButton.setImageResource(R.drawable.replybutton);
            sendButton.setTag(position);
            CommentCustomAdapter smallAdapter = new CommentCustomAdapter(context, rowData.get(position).commentData);
            list.setAdapter(smallAdapter);
        }

        return rowView;

    }

}
