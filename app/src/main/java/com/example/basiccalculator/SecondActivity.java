package com.example.basiccalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView historyText = findViewById(R.id.history_text);

        // Retrieve the history from the Intent
        Intent intent = getIntent();
        String history = intent.getStringExtra("history");

        // Display the history in the TextView
        if (history != null) {
            historyText.setText(history);
        } else {
            historyText.setText("No history available");
        }
    }
}
