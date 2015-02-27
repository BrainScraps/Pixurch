package com.codepath.pixurch.models;

import android.media.Image;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchRequest {

    private static final String ENDPOINT = "https://ajax.googleapis.com/ajax/services/search/images";
    static AsyncHttpClient client;
    static String queryText;
    static ArrayList<ImageResult> resultArray;

    public SearchRequest(String queryText) {
        queryText = queryText;
    }

    public static interface ResultLoaderListener {
        public void onResultLoaded(ArrayList<ImageResult> resultsArray);
    }
    private static String searchUrlWithParams() {
        return ENDPOINT + "?v=1.0&rsz=8&q=" + queryText;
    }

    public static void response(final ResultLoaderListener listener) {
        client = new AsyncHttpClient();
        client.get(searchUrlWithParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                resultArray = new ArrayList<ImageResult>();
                try {
                    JSONArray array = response.getJSONObject("responseData").getJSONArray("results");
                    Log.d("WHAT", String.valueOf(array.length()));
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        ImageResult result = new ImageResult(obj);
                        resultArray.add(result);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listener.onResultLoaded(resultArray);
            }
        });

    }
}
