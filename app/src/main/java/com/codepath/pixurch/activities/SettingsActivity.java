package com.codepath.pixurch.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.codepath.pixurch.R;
import com.codepath.pixurch.models.SearchPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import static android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item;

public class SettingsActivity extends ActionBarActivity {
    SearchPreferences searchPreferences;
    EditText etSiteSearch;
    Button btnSave;
    Spinner spinSize;
    Spinner spinColor;
    Spinner spinType;
    String site;
    String size;
    String type;
    String color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadPreferences();
        initializeColorSpinner();
        initializeSizeSpinner();
        initializeTypeSpinner();
        etSiteSearch = (EditText) findViewById(R.id.etSiteSearch);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                String size = spinSize.getSelectedItem().toString();
                String color = spinColor.getSelectedItem().toString();
                String type = spinType.getSelectedItem().toString();
                String site = etSiteSearch.getText().toString().trim();
                i.putExtra("size", size );
                i.putExtra("color", color );
                i.putExtra("type", type );
                i.putExtra("site", site );
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    private void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("img_search_prefs", MODE_PRIVATE);
        searchPreferences = new SearchPreferences(preferences);
        Map map = preferences.getAll();
        site = preferences.getString("site", "");
        size = preferences.getString("size", "");
        color = preferences.getString("color", "");
        type = preferences.getString("type", "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.complete_back) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeSizeSpinner(){
        spinSize = (Spinner) findViewById(R.id.spinSize);
        ArrayAdapter<ArrayList> sizeAdapter = new ArrayAdapter<ArrayList>(getApplicationContext(),
                android.R.layout.simple_spinner_item,  searchPreferences.sizesArray());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSize.setAdapter(sizeAdapter);
        int selectedSize = searchPreferences.sizesArray().indexOf(size);
        spinSize.setSelection(selectedSize);
    }
    public void initializeTypeSpinner(){
        spinType = (Spinner) findViewById(R.id.spinType);
        ArrayAdapter<ArrayList> typeAdapter = new ArrayAdapter<ArrayList>(getApplicationContext(),
                android.R.layout.simple_spinner_item,  SearchPreferences.typesArray());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(typeAdapter);
        int selectedType = searchPreferences.typesArray().indexOf(type);
        spinType.setSelection(selectedType);
    }
    public void initializeColorSpinner(){
        spinColor = (Spinner) findViewById(R.id.spinColor);
        ArrayAdapter<ArrayList> colorAdapter = new ArrayAdapter<ArrayList>(getApplicationContext(),
                android.R.layout.simple_spinner_item,  searchPreferences.colorsArray());
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinColor.setAdapter(colorAdapter);
        int selectedColor = searchPreferences.colorsArray().indexOf(color);
        spinColor.setSelection(selectedColor);
    }
}
