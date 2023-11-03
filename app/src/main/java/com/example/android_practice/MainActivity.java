package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private Button allTasksButton;
    private ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAddTask();
            }
        });

        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAllTasks();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSettings();
            }
        });
    }

    private void initViews() {
        addTaskButton = findViewById(R.id.addTaskButton);
        allTasksButton = findViewById(R.id.allTaskButton);
        settings = findViewById(R.id.settingsImageButton);
    }

    private void navigateToAddTask() {
        Intent addTaskPage = new Intent(MainActivity.this, AddTaskActivity.class);
        startActivity(addTaskPage);
    }

    private void navigateToAllTasks() {
        Intent allTaskPage = new Intent(MainActivity.this, AllTasksActivity.class);
        startActivity(allTaskPage);
    }

    private void navigateToSettings() {
        Intent settingsPage = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(settingsPage);
    }
}
