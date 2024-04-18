package com.example.fifthmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ngbutton = (Button)findViewById(R.id.newgame);
        startActivity(new Intent(getApplicationContext(),LevelActivity.class));

    }
}
