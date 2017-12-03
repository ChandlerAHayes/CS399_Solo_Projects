package educs399.nau.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    //widgets
    private ImageView iconPic;
    private EditText answerTxt;
    private Button submitButton;
    private TextView titleTxt;
    private PopupWindow popupWindow;

    //popup window widgets
    TextView correctTxt;
    TextView showAnswerTxt;
    TextView descTxt;
    Button nextBttn;

    //class variables
    private ArrayList<Icon> iconList;
    private Icon currIcon;
    private int currPosition;
    private int score;
    private Boolean correct;    //tells if the user got the current question correct or not
    private int originalListSize;
    private String category;

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
        setContentView(R.layout.activity_quiz);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

        //initialize widgets
        iconPic = (ImageView) findViewById(R.id.img_image);
        answerTxt = (EditText) findViewById(R.id.txt_answer);
        submitButton = (Button) findViewById(R.id.bttn_submit);
        titleTxt = (TextView) findViewById(R.id.txt_title);

        //initializing class variables
        setCategory();
        score = 0;
        iconList = fillList();
        originalListSize = iconList.size();

        //finding random icon to start with
        Random rand = new Random();
        int listSize = iconList.size();
        currPosition = rand.nextInt(listSize);

        //set ImageView to current icon's picture
        currIcon = iconList.get(currPosition);
        iconPic.setImageDrawable(currIcon.getIconPic());

        //event listeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make sure there is an answer
                String userAnswer = answerTxt.getText().toString();
                if(userAnswer.equals("")){
                    Toast.makeText(getApplicationContext(), "Must enter an answer", Toast.LENGTH_LONG).show();
//                    return;
                }
                else{
                    currIcon = iconList.get(currPosition);
                    userAnswer = answerTxt.getText().toString();
                    checkAnswer(currIcon, userAnswer);
                    initPopupWindow();
                }
            }
        });

    }

    //checks if the user's answer is correct
    private void checkAnswer(Icon icon, String userAnswer){
        String answer = icon.getName();
        //making sure userAnswer is in the correct format
        userAnswer = userAnswer.trim();
        userAnswer = userAnswer.toLowerCase();

        if(answer.equals(userAnswer)){
//            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
            correct = true;
            score += 1;
        }
        else{
            correct = false;
//            Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
        }
    }

    private void nextIcon(){
        if(iconList.size() == 1){
            //need to check current answer before going to next screen

            Intent i = new Intent(QuizActivity.this, ScoreActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("score", score);
            bundle.putInt("total", originalListSize);

            i.putExtras(bundle);
            startActivity(i);
        }
        else{
            iconList.remove(currPosition);

            int listSize = iconList.size();
            Random rand = new Random();
            currPosition = rand.nextInt(listSize);
            Icon currIcon = iconList.get(currPosition);

            iconPic.setImageDrawable(currIcon.getIconPic());
            answerTxt.setText("");
        }
    }

    //getting intent extras from CategoryActivity, to know which category
    private void setCategory(){
        Bundle extras = getIntent().getExtras();
        category = extras.getString(CATEGORY);

        switch(category){
            case ACTION_CATEGORY:
                titleTxt.setText("Action");
                break;
            case ALERT_CATEGORY:
                titleTxt.setText("Alert");
                break;
            case AV_CATEGORY:
                titleTxt.setText("AV");
                break;
            case COMMUNICATION_CATEGORY:
                titleTxt.setText("Communication");
                break;
            case DEVICES_CATEGORY:
                titleTxt.setText("Devices");
                break;
            case MAPS_CATEGORY:
                titleTxt.setText("Maps");
                break;
        }
    }

    //http://www.androidhub4you.com/2012/07/how-to-create-popup-window-in-android.html
    private void initPopupWindow(){
        try{
            LayoutInflater inflater = (LayoutInflater) QuizActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_window,
                    (ViewGroup) findViewById(R.id.popup));
            popupWindow = new PopupWindow(layout, 800, 1000, true);
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            //initialize widgets from popup
            correctTxt = (TextView) layout.findViewById(R.id.txt_correct);
            showAnswerTxt = (TextView) layout.findViewById(R.id.txt_solution);
            descTxt = (TextView) layout.findViewById(R.id.txt_desc);
            nextBttn = (Button) layout.findViewById(R.id.bttn_next);

            if(correct == true){
                correctTxt.setText("Correct!");
                descTxt.setVisibility(View.INVISIBLE);
            }
            else{
                correctTxt.setText("Incorrect!");
                String solution = iconList.get(currPosition).getName();
                showAnswerTxt.setText(solution);
                descTxt.setVisibility(View.VISIBLE);
            }

            nextBttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nextIcon();
                    popupWindow.dismiss();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Icon> fillList(){
        Resources res = getResources();
        ArrayList<Icon> list = new ArrayList<Icon>();

        switch (category){
            case "action":
                list.add(new Icon("accessible", res.getDrawable(R.drawable.ic_accessible)));
                list.add(new Icon("account box", res.getDrawable(R.drawable.ic_add_alert)));
                list.add(new Icon("alarm", res.getDrawable(R.drawable.ic_alarm)));
                list.add(new Icon("bookmark", res.getDrawable(R.drawable.ic_bookmark)));
                list.add(new Icon("setting brightness", res.getDrawable(R.drawable.ic_settings_brightness)));
                list.add(new Icon("code", res.getDrawable(R.drawable.ic_code)));
                list.add(new Icon("credit card", res.getDrawable(R.drawable.ic_credit_card)));
                list.add(new Icon("extension", res.getDrawable(R.drawable.ic_extension)));
                list.add(new Icon("shopping cart", res.getDrawable(R.drawable.ic_shopping_cart)));
                break;

            case "alert":
                list.add(new Icon("add alert", res.getDrawable(R.drawable.ic_add_alert)));
                list.add(new Icon("warning", res.getDrawable(R.drawable.ic_warning)));
                break;

            case "av":
                list.add(new Icon("closed caption", res.getDrawable(R.drawable.ic_closed_caption)));
                list.add(new Icon("equilizer", res.getDrawable(R.drawable.ic_equalizer)));
                list.add(new Icon("fast forward", res.getDrawable(R.drawable.ic_fast_forward)));
                list.add(new Icon("forward 10", res.getDrawable(R.drawable.ic_forward_10)));
                list.add(new Icon("movie", res.getDrawable(R.drawable.ic_movie)));
                list.add(new Icon("playlist add", res.getDrawable(R.drawable.ic_playlist_add)));
                list.add(new Icon("video camera", res.getDrawable(R.drawable.ic_videocam)));
                list.add(new Icon("volume down", res.getDrawable(R.drawable.ic_volume_down)));
                list.add(new Icon("volume mute", res.getDrawable(R.drawable.ic_volume_mute)));
                break;

            case "communication":
                list.add(new Icon("dialpad", res.getDrawable(R.drawable.ic_dialpad)));
                list.add(new Icon("email", res.getDrawable(R.drawable.ic_email)));
                list.add(new Icon("location on", res.getDrawable(R.drawable.ic_location_on)));
                list.add(new Icon("mail outline", res.getDrawable(R.drawable.ic_mail_outline)));
                list.add(new Icon("message", res.getDrawable(R.drawable.ic_message)));
                list.add(new Icon("voicemail", res.getDrawable(R.drawable.ic_voicemail)));
                break;

            case "devices":
                list.add(new Icon("battery charging", res.getDrawable(R.drawable.ic_battery_charging)));
                list.add(new Icon("bluetooth", res.getDrawable(R.drawable.ic_bluetooth)));
                list.add(new Icon("sd storage", res.getDrawable(R.drawable.ic_sd_storage)));
                list.add(new Icon("usb", res.getDrawable(R.drawable.ic_usb)));
                break;

            case "maps":
                list.add(new Icon("local airport", res.getDrawable(R.drawable.ic_local_airport)));
                list.add(new Icon("local dining", res.getDrawable(R.drawable.ic_local_dining)));
                list.add(new Icon("local grocery store", res.getDrawable(R.drawable.ic_local_grocery_store)));
                list.add(new Icon("local parking", res.getDrawable(R.drawable.ic_local_parking)));
                list.add(new Icon("map", res.getDrawable(R.drawable.ic_map)));
                list.add(new Icon("near me", res.getDrawable(R.drawable.ic_near_me)));
                break;
        }



        return list;
    }

}
