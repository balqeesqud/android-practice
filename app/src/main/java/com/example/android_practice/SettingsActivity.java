package com.example.android_practice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private TextView currentUsernameTextView;
    private EditText usernameEditText;
    private String text;
    public static final String USERNAME = "text";
    public static final String SHARED_PREFS = "sharedPrefs";
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        loadData();

        Button saveChanges = findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });
    }

    private void initViews() {
        currentUsernameTextView = findViewById(R.id.currentUsernameTextView);
        usernameEditText = findViewById(R.id.usernameEditText);
        backButton=findViewById(R.id.backButton);

    }

    private void saveData() {
        SharedPreferences sharedPref2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        text = usernameEditText.getText().toString();

        editor2.putString(USERNAME, text);
        editor2.apply();
        currentUsernameTextView.setText("Current Username: " + text);

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        SharedPreferences sharedPrefLoad = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPrefLoad.getString(USERNAME, " ");
        currentUsernameTextView.setText("Current Username: " + text);
        usernameEditText.setText(text);
    }

    private void navigateToHome() {
        Intent backToHomePage = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(backToHomePage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
