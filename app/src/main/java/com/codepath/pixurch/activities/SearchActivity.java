package com.codepath.pixurch.activities;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.codepath.pixurch.R;
import com.codepath.pixurch.adapters.ResultAdapter;
import com.codepath.pixurch.models.ImageResult;
import com.codepath.pixurch.models.SearchRequest;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    ArrayList<ImageResult> imageResults;
    RecyclerView rvResults;
    ResultAdapter aResults;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        imageResults = new ArrayList<>();
        rvResults = (RecyclerView) findViewById(R.id.rvResults);
        manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rvResults.setLayoutManager(manager);
        aResults = new ResultAdapter(getApplicationContext(), imageResults);
        rvResults.setAdapter(aResults);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchBar = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)  MenuItemCompat.getActionView(searchBar);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new SearchRequest(query).response(new SearchRequest.ResultLoaderListener() {
                    @Override
                    public void onResultLoaded(ArrayList<ImageResult> resultsArray) {
                        imageResults.addAll(resultsArray);
                        aResults.notifyDataSetChanged();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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
