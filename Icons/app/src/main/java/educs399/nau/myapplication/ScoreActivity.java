package educs399.nau.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    //widgets
    TextView scoreTxt;
    TextView totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        //Initialize widgets
        scoreTxt = (TextView) findViewById(R.id.txt_score);
        totalTxt = (TextView) findViewById(R.id.txt_total);

        //getting extras
        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");
        scoreTxt.setText(String.valueOf(score));
        int total = extras.getInt("total");
        totalTxt.setText("/" + String.valueOf(total));
    }

}
