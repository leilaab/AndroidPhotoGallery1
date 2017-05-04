package com.example.photogallery1.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.photogallery1.R;
import com.example.photogallery1.util.Constants;
import com.squareup.picasso.Picasso;




/**
 * Created by LeilaArbab  on 3/5/2017.
 */
public class FullScreenImageActivity extends AppCompatActivity {


    public ImageView ivPhoto;

    public TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        ivPhoto=(ImageView) findViewById(R.id.fullscreen_image);
        txtTitle=(TextView) findViewById(R.id.fullscreen_title);
        String imageUrl = getIntent().getStringExtra(Constants.KEY_IMAGE_URL);
        String title = getIntent().getStringExtra(Constants.KEY_IMAGE_TITLE);

        if (imageUrl == null) {
            Toast.makeText(this, "Original URL is empty", Toast.LENGTH_SHORT).show();
            finish();
        }

        Picasso.with(this).load(imageUrl).into(ivPhoto);
        txtTitle.setText(title);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

