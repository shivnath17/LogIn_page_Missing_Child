package com.example.shivnath.notification;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity implements View.OnClickListener {
    NotificationCompat.Builder notification;
    private static final int uniqId = 1;
    String phone_number = "8447909387";
    public static final String EMPLOYE_KEY  = "employe_name";
    public static final String POTENTIAL_KEY = "potential_text";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        Button notifi = findViewById(R.id.notifi);
        notifi.setOnClickListener(this);
        final FirebaseFirestore mDocRef1  =FirebaseFirestore.getInstance();





        Button potential_name_submission = findViewById(R.id.poetntial_name_submission);

        potential_name_submission.setOnClickListener(new View.OnClickListener() {

            private Task<Void> documentReference;

            @Override
            public void onClick(View v) {
                TextView potential_name = findViewById(R.id.potential_name);
                String potential_text = potential_name.getText().toString();
                final Map<String, Object> dataToSave  =new HashMap<String, Object>();
                dataToSave.put(POTENTIAL_KEY, potential_text);
                Bundle bun = getIntent().getExtras();
                String doc = bun.getString("add");

                documentReference = mDocRef1.collection("Employes").document("Shivnath").set(dataToSave);
            }
        });


    }

    @Override
    public void onClick(View v) {
        notification.setSmallIcon(R.drawable.ic_launcher_background);
        notification.setTicker("Heloo");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(phone_number);
        notification.setContentText("Tap! to Call Protential");

        Intent intent = new Intent(Intent.ACTION_CALL);

        if (ContextCompat.checkSelfPermission(Home.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Home.this,
                    new String[]{Manifest.permission.CALL_PHONE},uniqId);

        }
        else {
            String dail = "tel:" + phone_number;
            intent.setData(Uri.parse(dail));

        }



        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);


        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqId,notification.build());

        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.so);
        notification.setSound(sound);


    }
}
