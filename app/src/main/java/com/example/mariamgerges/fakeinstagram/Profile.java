package com.example.mariamgerges.fakeinstagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageView image =(ImageView) findViewById(R.id.pic);
        Intent intent = getIntent();
        Bitmap bmp = (Bitmap) intent.getParcelableExtra("BitmapImage");
        image.setImageBitmap(bmp);
        TextView text = (TextView) findViewById(R.id.name);
        text.setText(getIntent().getStringExtra("username"));
    }
}
