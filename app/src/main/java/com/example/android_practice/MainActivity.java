package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import adapter.Adapter;
import database.TaskDatabase;
import model.Task;

public class MainActivity extends AppCompatActivity {

    private Button addTaskButton;
    private Button allTasksButton;
    private ImageButton settings;

    private RecyclerView recyclerView;
    private TaskDatabase taskMasterDB;
    public final static String DATABASE_NAME="Tasks";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        List<Task> tasks = new ArrayList<>();

        taskMasterDB = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskDatabase.class,
                        DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        tasks=taskMasterDB.taskDAO().findAll();

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


//        tasks.add(new Task("Balqess", "b@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Reneh", "R@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Farah", "F@gmail.com", R.drawable.ic_launcher_background));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(tasks,getApplicationContext()));
    }

    private void initViews() {
        addTaskButton = findViewById(R.id.addTaskButton);
        allTasksButton = findViewById(R.id.allTaskButton);
        settings = findViewById(R.id.settingsImageButton);
        recyclerView= findViewById(R.id.recyclerview);

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
