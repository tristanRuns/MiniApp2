package com.example.tristan.miniapp2;

/**
 * Created by Tristan on 3/24/18.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.app.NotificationManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by celiachen on 2/7/18.
 */

// adapter is needed when you want to do any sort of list or table view
// gets data and decides where to display in the activity

public class RecipeAdapter extends BaseAdapter {

    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Recipe> mRecipeList;
    private LayoutInflater mInflater;

    // constructor
    public RecipeAdapter(Context mContext, ArrayList<Recipe> mRecipeList) {

        // initialize instances variables
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods
    // a list of methods we need to override

    // gives you the number of recipes in the data source
    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position) {
        return mRecipeList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        //LayoutInflater inflater = getLayoutInflater();

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.recipe_list_title);
            holder.servingTextView = convertView.findViewById(R.id.recipe_list_serving);
            holder.thumbnailImageView = convertView.findViewById(R.id.recipe_list_thumbnail);
            holder.prepTimeTextView=convertView.findViewById(R.id.recipe_list_preptime);
            holder.button=convertView.findViewById(R.id.button2);



            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        } else {
            // get the view holder from converview
            holder = (ViewHolder) convertView.getTag();
        }

        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView servingTextView = holder.servingTextView;
        TextView prepTimeTextView=holder.prepTimeTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;
        Button button=holder.button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }


        });

        // get corresonpinding recipe for each row
        Recipe recipe = (Recipe) getItem(position);


        // update the row view's textviews and imageview to display the information

        // titleTextView
        titleTextView.setText(recipe.title);
        titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorAccent));
        titleTextView.setTextSize(18);

        // servingTextView
        servingTextView.setText(recipe.servings + " servings");
        servingTextView.setTextSize(14);
        servingTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));

        //prepTimeTextView
        prepTimeTextView.setText(recipe.prepTime);
        prepTimeTextView.setTextSize(14);
        prepTimeTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));



        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(recipe.image).into(thumbnailImageView);
        return convertView;
    }


    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView servingTextView;
        public TextView prepTimeTextView;
        public ImageView thumbnailImageView;
        public Button button;
    }
    private void showNotification(){

    }

}