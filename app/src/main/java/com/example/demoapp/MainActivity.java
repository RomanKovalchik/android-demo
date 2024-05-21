package com.example.demoapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import servese.BMI;

public class MainActivity extends AppCompatActivity {

    TextView txtWig;
    Button insert, update, delete, view;

    EditText name, dob;
    EditText contact;

    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
       setContentView(R.layout.activity_main);
       txtWig = (TextView) findViewById(R.id.output);
       insert = findViewById(R.id.insert);
       update = findViewById(R.id.update);
       delete = findViewById(R.id.delete);
       view = findViewById(R.id.view);
       // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         //   Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         //   v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         //   return insets;
       // });

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        db = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //txtWig.setText("Hello from the button");
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nameTXT, contactTXT, dobTXT);
                if(checkinsertdata == true){
                    Toast.makeText(MainActivity.this, "New data is inserted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data is not inserted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //txtWig.setText("Hello from the button");
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata == true){
                    Toast.makeText(MainActivity.this, "The entry is updated",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "The entry is not updated",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //txtWig.setText("Hello from the button");
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = db.deletedata(nameTXT);
                if(checkdeletedata == true){
                    Toast.makeText(MainActivity.this, "The entry is deleted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "The entry is not deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.getdata();
                if(result.getCount() == 0){
                    Toast.makeText(MainActivity.this,"There is no data!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    //Здесь columnindex соответствует определенной колонке в таблице базы данных, т. е. колонки проиндксированы
                    buffer.append("The name: " + result.getString(0) + "\n");
                    buffer.append("Contact: " + result.getString(1) + "\n");
                    buffer.append("The date of birth is: " + result.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}