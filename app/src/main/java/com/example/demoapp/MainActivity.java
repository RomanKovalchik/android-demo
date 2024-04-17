package com.example.demoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtWig;
    Button insert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
       setContentView(R.layout.activity_main);
       txtWig = (TextView) findViewById(R.id.output);
       insert = findViewById(R.id.insert);
       // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         //   Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         //   v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         //   return insets;
       // });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtWig.setText("Hello from the button");
            }
        });

    }
}