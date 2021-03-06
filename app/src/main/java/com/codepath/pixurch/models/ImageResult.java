package com.codepath.pixurch.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ImageResult implements Serializable{
    public String fullUrl;
    public String thumbUrl;
    public String title;

    public ImageResult(JSONObject result) {
        try {
           fullUrl = result.getString("url");
           thumbUrl = result.getString("tbUrl");
           title = result.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
