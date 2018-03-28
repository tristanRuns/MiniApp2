package com.example.tristan.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by Tristan on 3/24/18.
 */

public class SearchActivity extends AppCompatActivity {
    private Button SearchButton;
    private Context mContext;
    private Spinner DietSpin;
    private Spinner PrepSpin;
    private Spinner ServeSpin;
    public String dietText;
    public String prepText;
    public String serveText;
    public String[] diets;
    public String [] servings;
    public String [] preps;

    @Override


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        mContext = this;
        SearchButton=findViewById(R.id.search_button);
        DietSpin=findViewById(R.id.DietSpin);
        PrepSpin=findViewById(R.id.PrepSpin);
        ServeSpin=findViewById(R.id.ServeSpin);

        diets= new String[]{" ","Vegetarian", "High-Carb", "Medium-Carb","Low-Carb","Balanced","Low-Sodium","Low-Fat"};
        servings= new String[]{" ","Less than 4", "4-6","7-9", "More than 10"};
        preps= new String[]{" ","30 minutes or less", "less than 1 hour", "more than 1 hour"};

        ArrayAdapter<String> dietAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, diets);
        ArrayAdapter<String> servingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, servings);
        ArrayAdapter<String> prepsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, preps);

        DietSpin.setAdapter(dietAdapter);
        ServeSpin.setAdapter(servingAdapter);
        PrepSpin.setAdapter(prepsAdapter);


        SearchButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launchActivity();

            }
        });
    }
    private void launchActivity() {
        Intent myIntent = new Intent(SearchActivity.this,
                ResultActivity.class);
        Intent infoIntent = new Intent(SearchActivity.this,
                RecipeAdapter.class);

        int position1 = DietSpin.getSelectedItemPosition();
        int position2=ServeSpin.getSelectedItemPosition();
        int position3=PrepSpin.getSelectedItemPosition();
        dietText = diets[position1].toString();
        serveText=servings[position2].toString();
        prepText=preps[position3].toString();
        //myIntent.putExtra("DietChoice", DietSpin.getSelectedItem().toString());
        myIntent.putExtra("DietChoice", dietText);
        myIntent.putExtra("ServingChoice", ServeSpin.getSelectedItem().toString());
        myIntent.putExtra("PrepChoice", PrepSpin.getSelectedItem().toString());


        startActivity(myIntent);


    }
    }
