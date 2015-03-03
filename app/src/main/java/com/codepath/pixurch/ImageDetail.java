package com.codepath.pixurch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.codepath.pixurch.models.ImageResult;
import com.squareup.picasso.Picasso;


public class ImageDetail extends ActionBarActivity {
    ImageResult imageResult;
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        imageResult = (ImageResult) getIntent().getSerializableExtra("imageResult");
        mainImage = (ImageView) findViewById(R.id.mainImage);
        Picasso.with(getApplicationContext()).load(imageResult.fullUrl).into(mainImage);
        getSupportActionBar().setTitle(imageResult.title);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
