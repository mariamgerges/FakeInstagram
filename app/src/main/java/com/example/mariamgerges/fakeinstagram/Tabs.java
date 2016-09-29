package com.example.mariamgerges.fakeinstagram;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;

public class Tabs extends TabActivity {

    ArrayList<RowData>rowData=new ArrayList<RowData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        //Get resources to use drawables as tab icons
        //First, set the tab host the has the five tabs of the instagarm app and assign an xml file
        // to each tab that selects a specific image if the tab was selected and a default image for
        // when the tab is not selected

        Resources res =getResources();
        final TabHost tabHost = getTabHost();
        tabHost.setup();
        TabHost.TabSpec tabspec = tabHost.newTabSpec("Home");
        tabspec.setContent(R.id.home);
        tabspec.setIndicator((""), res.getDrawable(R.drawable.icon_android_home));
        tabHost.addTab(tabspec);
        tabspec = tabHost.newTabSpec("Search");
        tabspec.setContent(R.id.search);
        tabspec.setIndicator((""), res.getDrawable(R.drawable.icon_android_search));
        tabHost.addTab(tabspec);
        tabspec = tabHost.newTabSpec("Camera");
        tabspec.setContent(new Intent(this, CameraActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabspec.setIndicator((""), res.getDrawable(R.drawable.icon_android_camera));
        tabHost.addTab(tabspec);
        tabspec = tabHost.newTabSpec("Love");
        tabspec.setContent(R.id.love);
        tabspec.setIndicator((""), res.getDrawable(R.drawable.icon_android_love));
        tabHost.addTab(tabspec);
        tabspec = tabHost.newTabSpec("Profile");
        tabspec.setContent(R.id.profile);
        tabspec.setIndicator((""), res.getDrawable(R.drawable.icon_android_profile));
        tabHost.addTab(tabspec);

        //the tab host is given a white background
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = (int) (43 * this.getResources().getDisplayMetrics().density);
        }

        //a tab-changed-listener that will keep track if the tab was changed. If the tab of the
        // camera was selected, a new activity for the camera is opened

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                Log.i("***Selected Tab", "Im currently in tab with index::" + tabHost.getCurrentTab());
                if(tabHost.getCurrentTab()==2) {
                    Intent intent =new Intent(getCurrentActivity(), CameraActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Create an array list of data that will be displayed on the newsfeed screen
        ListView rowList = (ListView) findViewById(R.id.myList);
        RowData rowData1 = new RowData();
        RowData rowData2 = new RowData();
        rowData1.profileName="Beethoven";
        rowData2.profileName="Mozart";
        rowData1.profileImage =R.drawable.beethoven;
        rowData2.profileImage =R.drawable.mozart;
        CommentData commentData1 =new CommentData();
        commentData1.comment="Gamila";
        commentData1.commenter="Mozart";
        rowData1.commentData.add(commentData1);
        CommentData commentData2=new CommentData();
        commentData2.comment="Wow";
        commentData2.commenter="Beethoven";
        rowData2.commentData.add(commentData2);
        rowData1.postPicture =R.drawable.nature1;
        rowData2.postPicture=R.drawable.nature2;
        rowData1.dateOfPost="3 weeks ago";
        rowData2.dateOfPost="2 weeks ago";
        rowData1.likeButton =R.drawable.lovebutton;
        rowData2.likeButton =R.drawable.lovebutton;
        rowData1.likeNumber = 3;
        rowData2.likeNumber = 4;
        rowData.add(rowData1);
        rowData.add(rowData2);

    // get data from the table by the ListAdapter
        RowCustomAdapter rowCustomAdapter = new RowCustomAdapter(this, rowData);
        rowList.setAdapter(rowCustomAdapter);
    }

    //this is the onclick handler for the username and user picture that will move us to a new
    // activity for the profile the profile activity is not complete; it has only the username
    // and the user picture of the user

    public void gotoProfile(View v) {
        Intent intent = new Intent(this,Profile.class);
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TextView profileName = (TextView)vwParentRow.getChildAt(1);
        ImageView profileImage = (ImageView)vwParentRow.getChildAt(0);
        intent.putExtra("username", profileName.getText());
        profileImage.buildDrawingCache();
        Bitmap bmp= profileImage.getDrawingCache();
        intent.putExtra("BitmapImage", bmp);
        startActivity(intent);
    }

    // this is the onclick handler for the like image button that will change the image for the like
    // button if it was clicked and it will change number of likes for that image. It will change
    // the original array sent to the custom adapter

    public void likeImg(View view) {
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        ImageButton likeButton = (ImageButton)vwParentRow.getChildAt(0);
        ImageButton commentButton = (ImageButton)vwParentRow.getChildAt(2);
        int pos=(int) commentButton.getTag();
        if(rowData.get(pos).likeButton ==R.drawable.lovebutton)
        {
            likeButton.setImageResource(R.drawable.lovebuttonpressed);
            ImageButton repBut = (ImageButton)vwParentRow.getChildAt(1);
            int likeNumber = (int)repBut.getTag();
            likeNumber++;
            TextView numberOfLikes = (TextView)vwParentRow.getChildAt(3);
            numberOfLikes.setText(Integer.toString(likeNumber));
            rowData.get(pos).likeNumber=likeNumber;
            rowData.get(pos).likeButton =R.drawable.lovebuttonpressed;
        }
        else
        {
            likeButton.setImageResource(R.drawable.lovebutton);
            ImageButton repBut = (ImageButton)vwParentRow.getChildAt(1);
            int x= (int)repBut.getTag();
            x--;
            TextView numberOfLikes = (TextView)vwParentRow.getChildAt(3);
            numberOfLikes.setText(Integer.toString(x));
            rowData.get(pos).likeNumber=x;
            rowData.get(pos).likeButton =R.drawable.lovebutton;
        }


    }
}

