package com.example.android_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dao.TaskDAO;
import database.TaskDatabase;
import model.Task;

public class AddTaskActivity extends AppCompatActivity {

    private ImageButton backButton;
    private EditText taskTitle;
    private EditText taskDescription;
    private TextView totalTasksCounter;
    private Button saveButton;
    private TaskDAO taskDAO;
    private TaskDatabase taskDB;


    //  This is a string key that represents the name of your SharedPreferences file. It acts as a container for your stored data.
    public static final String SHARED_PREFS = "sharedPrefs";

    //This is a string key for storing the task title in SharedPreferences.
    public static final String TASK_TITLE = "text";

    //This is a string key for storing the task description in SharedPreferences.
    public static final String TASK_DESCRIPTION = "text";

    private String text;
    private List<Task> tasks = null;


    int x = 0; // should be outside the onCreate method
    // initializing the x variable inside the onClick listener for the saveButton. As a result,
    // every time you click the "Save" button, x is set to 0. To maintain the value of x across
    // multiple button clicks, you should declare it as a member variable of your AddTaskActivity class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initViews();

        taskDB = Room.databaseBuilder(getApplicationContext(),
                        TaskDatabase.class,
                        "Tasks")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        Log.d("error","inside add task room");
        tasks=taskDB.taskDAO().findAll();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                totalTaskCounter();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });
    }

    public void saveData() {
        // calling the getSharedPreferences with the SHARED_PREFS constant, which is the name of the SharedPreferences file.
//        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // obtain an Editor instance by calling edit() on the SharedPreferences instance. The Editor allows you to make changes to the SharedPreferences.
//        SharedPreferences.Editor editor = sharedPref.edit();

        // Using putString method of the Editor to store the task title and task description as key-value pairs in the SharedPreferences.
        // The keys are TASK_TITLE and TASK_DESCRIPTION, and the values are obtained from the EditText fields taskTitle and taskDescription.
//        editor.putString(TASK_TITLE, taskTitle.getText().toString());
//        editor.putString(TASK_DESCRIPTION, taskDescription.getText().toString());

        // to commit these changes and save the data in the SharedPreferences.
//        editor.apply();
        String title= taskTitle.getText().toString();
        String description= taskDescription.getText().toString();
        int image=R.drawable.ic_launcher_background;
        Task newTask= new Task(title,description,image);

        taskDB.taskDAO().addTask(newTask);

        Toast toast = Toast.makeText(getApplicationContext(), "Submitted!!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void initViews() {
        taskTitle = findViewById(R.id.TaskTitleEditText);
        taskDescription = findViewById(R.id.TaskDescriptionEditView);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);


    }

    public void totalTaskCounter() {

        totalTasksCounter = findViewById(R.id.totalTasksCounter);
        totalTasksCounter.setText("Total Tasks: " + String.valueOf(x++));
    }

    private void navigateToHome() {
        Intent backToHomePage = new Intent(AddTaskActivity.this, MainActivity.class);
        startActivity(backToHomePage);
    }

}