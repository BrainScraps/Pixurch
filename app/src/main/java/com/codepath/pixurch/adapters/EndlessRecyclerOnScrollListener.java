package com.codepath.pixurch.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.lang.reflect.Array;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
    int  visibleItemCount, totalItemCount;
    int[] firstVisibleItems;

    private int current_page = 1;

    private StaggeredGridLayoutManager manager;

    public EndlessRecyclerOnScrollListener(StaggeredGridLayoutManager manager) {
        this.manager = manager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = manager.getItemCount();
        firstVisibleItems = new int[]{0,0};
        manager.findFirstVisibleItemPositions(firstVisibleItems);

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItems[0] + visibleThreshold)) {
            Log.d("math", String.valueOf((totalItemCount - visibleItemCount)) + " " + String.valueOf(firstVisibleItems[0] + visibleThreshold));
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page, totalItemCount);

            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemCount);
}
