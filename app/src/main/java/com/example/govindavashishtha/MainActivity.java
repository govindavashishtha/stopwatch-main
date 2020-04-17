package com.example.govindavashishtha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    TextView time ;
    Vibrator vibrator;
    int lapnumber;
    ImageView startButton , pauseButton;
    boolean istimerunning;
    SimpleDateFormat format;
    String displayTime;
    Button lapbutton;
    ListView list;
    ArrayList<String> arrayList;
    Runnable runnable ;
    long timeMill;
    Button resetbutton;
    final Handler handler = new Handler();

    public void setResetButton (View view){
        lapnumber=1;
        handler.removeCallbacksAndMessages(null);
        time.setText("00:00.00");
        timeMill =0;
        pauseButton.setVisibility(view.INVISIBLE);
        startButton.setVisibility(view.VISIBLE);
        resetbutton.setVisibility(view.INVISIBLE);
        lapbutton.setVisibility(view.INVISIBLE);
        arrayList.clear();
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(itemsAdapter);

    }



    public void setpauseButton (View view){
        handler.removeCallbacksAndMessages(null);
        time.setText(displayTime);
        pauseButton.setVisibility(view.INVISIBLE);
        startButton.setVisibility(view.VISIBLE);
        lapbutton.setVisibility(view.INVISIBLE);
    }

    public void updateTime(long updatedTime) {

        displayTime = format.format(updatedTime);

       time.setText(displayTime);
    }

    public void LapbuttonOnclick(View view){

        arrayList.add(0,"#"+Integer.toString(lapnumber)+"  "+displayTime);
        lapnumber++;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(itemsAdapter);

    }


    public void startButtonOnclick(View view)  {
        lapbutton.setVisibility(view.VISIBLE);
        resetbutton.setVisibility(view.VISIBLE);
        pauseButton.setVisibility(view.VISIBLE);
        startButton.setVisibility(view.INVISIBLE);
//             startButton.setText("PAUSE");
             istimerunning=true;
             format = new SimpleDateFormat("mm:ss.SS");
             format.setTimeZone(TimeZone.getTimeZone("GMT"));

              runnable = new Runnable() {

                  @Override
                  public void run() {
                      timeMill += 18;
                   updateTime(timeMill);
                   handler.postDelayed(this, 10);
                  }
              };
              handler.post(runnable);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lapnumber = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        istimerunning = false;
        startButton = findViewById(R.id.imageView2);

        time = findViewById(R.id.editText);

        lapbutton = findViewById(R.id.button2);
        lapbutton.setText("LAP");
        list = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        timeMill=0;
       pauseButton = findViewById(R.id.imageView3);

        resetbutton = findViewById(R.id.button4);
        resetbutton.setText("RESET");

    }
}
