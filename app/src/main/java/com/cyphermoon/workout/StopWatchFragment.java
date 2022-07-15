package com.cyphermoon.workout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;


public class StopWatchFragment extends Fragment {

    private int seconds;
    private boolean running;
    private boolean wasRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(wasRunning) running = true;
    }

    public void startWatch(View view){
        running = true;
    }

    public void stopWatch(View view){
        running = false;
    }

    public void resetWatch(View view){
        running = false;
        seconds = 0;
    }

    public void runTimer(View view){
        final TextView timeView = view.findViewById(R.id.time_view);

        Handler handler = new Handler();
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        int hours = seconds / 3600 ;
                        int minutes =(seconds % 3600) / 60 ;
                        int sec = seconds % 60;

                        String timeDisplay = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec);
                        timeView.setText(timeDisplay);

                        if(running) seconds++;

                        handler.postDelayed(this, 1000);
                    }
                }
        );

    }


}