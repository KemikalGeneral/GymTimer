package com.endorphinapps.kemikal.gymtimer;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CounterTimer extends AppCompatActivity {

    private TextView tv_counter_timer_info_box_work;
    private TextView tv_counter_timer_info_box_rest;
    private TextView tv_counter_timer_info_box_repeat;
    private TextView tv_counter_timer;

    private long workTime = 0;
    private long restTime = 0;
    private int repeatAmount = 0;

    private MediaPlayer mp_three_two_one;
    private MediaPlayer mp_gong;
    private MediaPlayer mp_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_timer);

        /**
         * Initialise all views
         */
        findViews();

        /**
         * Get extras from intent
         * Use extras to set text
         */
        Intent intent = getIntent();
        if (intent.hasExtra("WORK")) {
            workTime = intent.getLongExtra("WORK", 100000);
            tv_counter_timer_info_box_work.setText("" + workTime / 1000);
        } if (intent.hasExtra("REST")) {
            restTime = intent.getLongExtra("REST", 100000);
            tv_counter_timer_info_box_rest.setText("" + restTime / 1000);
        } if (intent.hasExtra("REPEAT")) {
            repeatAmount = intent.getIntExtra("REPEAT", 100000);
            tv_counter_timer_info_box_repeat.setText("" + repeatAmount);
        } else {
            Toast.makeText(getApplicationContext(), "Sorry, something went wrong", Toast.LENGTH_SHORT).show();
        }

        introCounter();
    }

    /**
     * Initialise all views
     */
    public void findViews() {
        tv_counter_timer_info_box_work = (TextView) findViewById(R.id.tv_counter_timer_info_box_work);
        tv_counter_timer_info_box_rest = (TextView) findViewById(R.id.tv_counter_timer_info_box_rest);
        tv_counter_timer_info_box_repeat = (TextView) findViewById(R.id.tv_counter_timer_info_box_repeat);
        tv_counter_timer = (TextView) findViewById(R.id.tv_counter_timer);

        mp_three_two_one = new MediaPlayer().create(this, R.raw.three_two_one);
        mp_gong = new MediaPlayer().create(this, R.raw.gong);
        mp_alarm = new MediaPlayer().create(this, R.raw.alarm);
    }

    /**
     * START INTRO COUNTER 3 2 1
     */
    /**
     * ...set text color to green,
     * ...set counter text 3 2 1,
     * ...play 3 2 1 audio countdown
     */
    public void introCounter() {
        new CountDownTimer(3000, 500) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_counter_timer.setTextColor(Color.GREEN);
                tv_counter_timer.setText("" + millisUntilFinished / 1000);
                mp_three_two_one.start();
            }

            @Override
            public void onFinish() {
                countDownTimer();
            }
        }.start();
    }

    /**
     * START COUNT DOWN TIMER
     */
    /**
     * OnTick()...
     * ...set text colour to red,
     * ...set counter text,
     * ...set text color to white when there's 3 seconds remaining,
     * OnFinish()
     * ...if the counter is on the last cycle, don't start the restCounter,
     * ...if it isn't, play alarm and start the restTimer
     */
    public void countDownTimer() {

        new CountDownTimer(workTime, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_counter_timer.setTextColor(Color.RED);
                tv_counter_timer.setText("" + millisUntilFinished / 1000);

                if (millisUntilFinished < 4000) {
                    tv_counter_timer.setTextColor(Color.WHITE);
                }
            }
            @Override
            public void onFinish() {

                if (repeatAmount == 1) {
                    endCounter();
                } else {
                    mp_alarm.start();
                    restTimer();
                }
            }
        }.start();
    }

    /**
     * START THE REST TIMER
     */
    /**
     * ...decrease the repeat cycle by one,
     * Ontick()
     * ...set text colour to green,
     * ...set counter text,
     * ...play 3 2 1 countdown audio when there's 3 seconds remaining,
     * OnFinish()
     * ...start the countDownTimer
     */
    public void restTimer() {

        repeatAmount--;

        new CountDownTimer(restTime + 200, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                tv_counter_timer.setTextColor(Color.GREEN);
                tv_counter_timer.setText("" + millisUntilFinished / 1000);

                if (millisUntilFinished < 4000) {
                    mp_three_two_one.start();
                }
            }
            @Override
            public void onFinish() {
                    countDownTimer();
            }
        }.start();
    }

    /**
     * END THE COUNTER
     */
    /**
     * ...set text color to green,
     * ...set text size to accommodate the wording,
     * ...set text to DONE,
     * ...play the finishing gong audio
     */
    public void endCounter() {
        tv_counter_timer.setTextColor(Color.GREEN);
        tv_counter_timer.setTextSize(100);
        tv_counter_timer.setText(getResources().getString(R.string.done));
        mp_gong.start();
    }
}
//android studio test commit
