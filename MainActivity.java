package com.example.tristan.miniapp2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    Button button;
    private ImageView mImageView;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);
        //Picasso.with(mContext).load("http://www.uppercrustindia.com/dynamic/uploads/image/UC_43/Mala/Food_Table.jpg).into(mImageView").into(mImageView);
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.main_button);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SearchActivity.class);
                startActivity(myIntent);
            }
        });
        mImageView=findViewById(R.id.imageView);
    }

}
