package com.example.tristan.miniapp2;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by Tristan on 3/24/18.
 */

public class ResultActivity extends AppCompatActivity {

    private TextView searchText;
    private ListView mListView;
    private Button button;
    private Context mContext;
    public Boolean hasDiet=false;
    public Boolean hasPrep=false;
    public Boolean hasServe=false;
    public String serveCount;




    public int parseTime(String a){

        int hour=0;
        int min=0;

        int total=0;
        if (a.contains("hour")) {
            String hourCheck = a.substring(a.indexOf(" hour") - 1, a.indexOf(" hour"));
            hour=Integer.parseInt(hourCheck);
        }
        if (a.contains("minute")) {
            String minuteCheck = a.substring(a.indexOf(" minute") - 2, a.indexOf(" minute"));
            min = Integer.parseInt(minuteCheck);
        }
        total=hour*60+min;
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        button = findViewById(R.id.button2);
        mContext = this;
        searchText = findViewById(R.id.searchText);
        String dietChoice = this.getIntent().getExtras().getString("DietChoice");
        String servingChoice = this.getIntent().getExtras().getString("ServingChoice");
        String prepChoice = this.getIntent().getExtras().getString("PrepChoice");

        // data to display
        ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
        ArrayList<Recipe> editRecipeList = new ArrayList<>();
        for (int i = 0; i < recipeList.size(); i++) {
            hasDiet = false;
            hasServe = false;
            hasPrep = false;
            serveCount = "a";
            int prepTime = 0;
            if (prepChoice.equals("more than 1 hour")) {
                prepTime = 1000;
            } else {
                prepTime = parseTime(prepChoice);
            }

            if ((recipeList.get(i).dietLabel.equals(dietChoice) || dietChoice.equals(" "))) {
                // editRecipeList.add(recipeList.get(i));
                hasDiet = true;

            }
            if (recipeList.get(i).servings > 10) {
                serveCount = "More than 10";
            } else if (recipeList.get(i).servings < 4) {
                serveCount = "Less than 4";
            } else if (recipeList.get(i).servings >= 4 && recipeList.get(i).servings <= 6) {
                serveCount = "4-6";
            } else if (recipeList.get(i).servings >= 7 && recipeList.get(i).servings <= 9) {
                serveCount = "7-9";
            }

            if (servingChoice.equals(serveCount) || servingChoice.equals(" ")) {
                hasServe = true;
            }

            if (prepChoice.equals(" ")) {
                hasPrep = true;
            } else if (parseTime(recipeList.get(i).prepTime) <= prepTime) {
                hasPrep = true;
            }

            if (hasServe && hasDiet && hasPrep) {
                editRecipeList.add(recipeList.get(i));
                searchText.setText(" ");
            }

        }
        // create the adapter
        RecipeAdapter adapter = new RecipeAdapter(this, editRecipeList);

        // find the listview in the layout
        // set the adapter to listview
        mListView = findViewById(R.id.recipe_list_view);
        mListView.setAdapter(adapter);
        button=findViewById(R.id.button2);




    }

        // 1. each row should be clickable
        // when the row is clicked,
        // the intent is created and send
        public void buildNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                "default");
        builder.setContentTitle("Successfully logged in!");

    }


    }

