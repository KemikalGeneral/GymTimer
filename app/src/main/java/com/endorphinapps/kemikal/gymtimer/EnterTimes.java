package com.endorphinapps.kemikal.gymtimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterTimes extends AppCompatActivity {

    private EditText et_work;
    private EditText et_rest;
    private EditText et_repeat;
    private TextView tv_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_timer);

        /** Initialise all views **/
        findViews();

        /**
         * Validate input fields for not null
         * Convert input to long/int
         * Send to CounterTimer()
         **/
        tv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long workTime;
                long restTime;
                int repeatAmount;

                //Check if inputs are null,
                //...if so, Toast an error,
                if (et_work.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "No WORK time!", Toast.LENGTH_SHORT).show();
                } else if (et_rest.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "No REST time!", Toast.LENGTH_SHORT).show();
                } else if (et_repeat.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "No REPEAT amount!", Toast.LENGTH_SHORT).show();
                } else {
                    //...if not, convert to long/int,
                    workTime = Long.parseLong(et_work.getText().toString()) * 1000;
                    restTime = Long.parseLong(et_rest.getText().toString()) * 1000;
                    repeatAmount = Integer.parseInt(et_repeat.getText().toString());

                    //Add inputs to Intent and send.
                    Intent intent = new Intent(EnterTimes.this, CounterTimer.class);
                    intent.putExtra("EXTRAS_WORK", workTime);
                    intent.putExtra("EXTRAS_REST", restTime);
                    intent.putExtra("EXTRAS_REPEAT", repeatAmount);
                    startActivity(intent);
                }
            }
        });
    }

    /** Initialise all views **/
    public void findViews() {
        et_work = (EditText) findViewById(R.id.et_work);
        et_rest = (EditText) findViewById(R.id.et_rest);
        et_repeat = (EditText) findViewById(R.id.et_repeat);
        tv_play = (TextView) findViewById(R.id.tv_play);
    }
}
