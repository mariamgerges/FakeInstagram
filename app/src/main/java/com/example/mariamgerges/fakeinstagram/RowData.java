package com.example.mariamgerges.fakeinstagram;

import java.util.ArrayList;

/* RowData is a class made to encapsulate the data to be viewed in the rows of the newsfeed*/

public class RowData {
    public String profileName;
    public int profileImage;
    public  int postPicture;
    public int likeButton;
    public  int likeNumber;
    public ArrayList<CommentData> commentData = new ArrayList<CommentData>();
    public  String dateOfPost;
}
