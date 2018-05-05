package com.example.shivnath.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView user_name = findViewById(R.id.user_name);
    Button user_name_submited = findViewById(R.id.user_name_btn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name_submited.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        String user  = user_name.getText().toString();
        Intent intent1 = new Intent(MainActivity.this, Home.class);
        intent1.putExtra("add", user);
        startActivity(intent1);


    }
}
