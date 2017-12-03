package educs399.nau.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;


//import android.support.v7.app.ActionBar;

public class CategoryActivity extends AppCompatActivity {
    //widgets
    ImageView actionImg;
    ImageView alertImg;
    ImageView avImg;
    ImageView commImg;
    ImageView devicesImg;
    ImageView mapImg;

    //constants
    private static final String CATEGORY = "category";
    private static final String ACTION_CATEGORY = "action";
    private static final String ALERT_CATEGORY = "alert";
    private static final String AV_CATEGORY = "av";
    private static final String COMMUNICATION_CATEGORY = "communication";
    private static final String DEVICES_CATEGORY = "devices";
    private static final String MAPS_CATEGORY = "maps";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);



        //initialize widgets
        actionImg = findViewById(R.id.img_action);
        alertImg = findViewById(R.id.img_alert);
        avImg = findViewById(R.id.img_av);
        commImg = findViewById(R.id.img_comm);
        devicesImg = findViewById(R.id.img_devices);
        mapImg = findViewById(R.id.img_map);

        //setting up event listeners
        actionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY,ACTION_CATEGORY );
                startActivity(intent);
            }
        });

        alertImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY, ALERT_CATEGORY);
                startActivity(intent);
            }
        });

        avImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY,AV_CATEGORY );
                startActivity(intent);
            }
        });

        commImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY,COMMUNICATION_CATEGORY );
                startActivity(intent);
            }
        });

        devicesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY, DEVICES_CATEGORY);
                startActivity(intent);
            }
        });

        mapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                intent.putExtra(CATEGORY, MAPS_CATEGORY);
                startActivity(intent);
            }
        });
    }

}
