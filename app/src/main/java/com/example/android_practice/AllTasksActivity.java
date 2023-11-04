package com.example.android_practice;

import static com.example.android_practice.MainActivity.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import adapter.Adapter;
import database.TaskDatabase;
import model.Task;

public class AllTasksActivity extends AppCompatActivity {

    private ImageButton backButton;
    private RecyclerView recyclerView;
    private TextView userTasksTextView;

    public static final String USERNAME = "text";
    public static final String SHARED_PREFS = "sharedPrefs";
    private String text;
    private List<Task> tasks = null;

    TaskDatabase taskDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        initViews();
        loadData();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

        taskDB = Room.databaseBuilder(
                        getApplicationContext(),
                        TaskDatabase.class,
                        DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        tasks=taskDB.taskDAO().findAll();

//        tasks.add(new Task("Balqess", "b@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Reneh", "R@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Farah", "F@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Balqess", "b@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Reneh", "R@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Farah", "F@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Balqess", "b@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Reneh", "R@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Farah", "F@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Balqess", "b@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Reneh", "R@gmail.com", R.drawable.ic_launcher_background));
//        tasks.add(new Task("Farah", "F@gmail.com", R.drawable.ic_launcher_background));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(tasks,getApplicationContext()));



    }

    public void initViews(){
        backButton=findViewById(R.id.backButton);
        recyclerView=findViewById(R.id.recyclerview);
        userTasksTextView=findViewById(R.id.userTasksTextView);
    }
    private void navigateToHome() {
        Intent backToHomePage = new Intent(AllTasksActivity.this, MainActivity.class);
        startActivity(backToHomePage);
    }

    private void loadData(){
        SharedPreferences sharedPref= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text= sharedPref.getString(USERNAME,"");
        userTasksTextView.setText(text + "'s Tasks");
    }
}