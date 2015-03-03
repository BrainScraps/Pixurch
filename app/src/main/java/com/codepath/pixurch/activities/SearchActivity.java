package com.codepath.pixurch.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.codepath.pixurch.ImageDetail;
import com.codepath.pixurch.R;
import com.codepath.pixurch.adapters.EndlessRecyclerOnScrollListener;
import com.codepath.pixurch.adapters.RecyclerItemClickListener;
import com.codepath.pixurch.adapters.ResultAdapter;
import com.codepath.pixurch.models.ImageResult;
import com.codepath.pixurch.models.SearchPreferences;
import com.codepath.pixurch.models.SearchRequest;
import com.codepath.pixurch.adapters.ResultViewHolder;

import java.util.ArrayList;


public class SearchActivity extends ActionBarActivity {

    ArrayList<ImageResult> imageResults;
    RecyclerView rvResults;
    ResultAdapter aResults;
    RecyclerView.LayoutManager manager;
    MenuItem searchBar;
    SharedPreferences preferences;
    SearchPreferences searchPreferences;
    ResultViewHolder holder;

    private final int FILTERS_REQUEST_CODE = 1337;

    public final MenuItem getSearchBar (){
        return searchBar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        preferences = getSharedPreferences("img_search_prefs", MODE_PRIVATE);
        searchPreferences = new SearchPreferences(preferences);

        imageResults = new ArrayList<>();
        rvResults = (RecyclerView) findViewById(R.id.rvResults);
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvResults.setLayoutManager(manager);
        aResults = new ResultAdapter(getApplicationContext(), imageResults);

        rvResults.setAdapter(aResults);
        rvResults.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Intent i = new Intent(getApplicationContext(), ImageDetail.class);
                        i.putExtra("imageResult", imageResults.get(position));
                        startActivity(i);
                    }
                })
        );
//        rvResults.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
//                Intent i = new Intent(getApplicationContext(), ImageDetail.class);
//
////                i.putExtra("image", image);
//
//            }
//        });
        rvResults.setOnScrollListener(new EndlessRecyclerOnScrollListener((StaggeredGridLayoutManager) manager) {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                new SearchRequest(page, totalItemCount).loadMore(new SearchRequest.ResultLoaderListener() {
                    @Override
                    public void onResultLoaded(ArrayList<ImageResult> resultsArray) {
                        Log.d("WHAAA RESULTS", String.valueOf(resultsArray.size()) );
                        for(int i = 0; i < resultsArray.size(); i++){
                           imageResults.add(resultsArray.get(i));
                        }
                        aResults.notifyDataSetChanged();
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchBar = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)  MenuItemCompat.getActionView(searchBar);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MenuItem searchBar = getSearchBar();
                searchBar.collapseActionView();
                setActionBarTitleForQuery(query);
                new SearchRequest(query, searchPreferences).response(new SearchRequest.ResultLoaderListener() {
                    @Override
                    public void onResultLoaded(ArrayList<ImageResult> resultsArray) {
                        imageResults.clear();
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
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.btnFilters){
            Intent i = new Intent(this.getApplicationContext(), SettingsActivity.class);
            this.startActivityForResult(i, FILTERS_REQUEST_CODE);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setActionBarTitleForQuery(String query){
        String title = "Images for \"" + query + "\"";
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == FILTERS_REQUEST_CODE && resultCode == RESULT_OK) {
            searchPreferences.setSize(data.getStringExtra("size"));
            searchPreferences.setColor(data.getStringExtra("color"));
            searchPreferences.setType(data.getStringExtra("type"));
            searchPreferences.setSite(data.getStringExtra("site"));
            imageResults.clear();
            searchPreferences.getPrefsFromSharedPreferences();
            aResults.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
