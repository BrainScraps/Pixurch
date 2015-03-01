package com.codepath.pixurch.models;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchPreferences implements Serializable {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public String color;
    public String size;
    public String site;
    public String type;

    public static ArrayList sizesArray(){
        ArrayList sizes = new ArrayList<String>(
            Arrays.asList("", "icon", "medium", "xxlarge", "huge")
        );
        return sizes;
    }

    public static ArrayList colorsArray(){
        ArrayList colors = new ArrayList<String>(
                Arrays.asList("", "blue", "black", "white", "red", "brown", "gray", "green", "orange", "pink","purple", "yellow", "teal")
        );
        return colors;
    }

    public static ArrayList typesArray(){
        ArrayList types = new ArrayList<String>(
                Arrays.asList("", "face", "photo", "clipart", "lineart")
        );
        return types;
    }

    public SearchPreferences(SharedPreferences pref) {
        preferences  = pref;
        editor = pref.edit();
        getPrefsFromSharedPreferences();
    }

    public void getPrefsFromSharedPreferences(){
       this.size = preferences.getString("size", "");
       this.site = preferences.getString("site", "");
       this.color = preferences.getString("color", "");
       this.type = preferences.getString("type", "");
    }

    public void setSize(String size){
        editor.putString("size", size).commit();
        this.size = size;
    }

    public void setColor(String color){
        editor.putString("color", color).commit();
        this.color = color;
    }

    public void setSite(String site){
        editor.putString("site", site).commit();
        this.site = site;
    }

    public void setType(String type){
        editor.putString("type", type).commit();
        this.type = type;
    }
}
