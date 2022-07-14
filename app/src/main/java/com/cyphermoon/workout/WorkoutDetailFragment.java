package com.cyphermoon.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)  workoutId = savedInstanceState.getLong("workoutId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        TextView title = (TextView) view.findViewById(R.id.workout_title);
        TextView description = (TextView) view.findViewById(R.id.workout_description);
        Workout workout = Workout.workouts[(int) workoutId];

        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
    }

    public void setWorkoutId(Long workoutId){
        this.workoutId = workoutId;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("workoutId", workoutId);
    }
}