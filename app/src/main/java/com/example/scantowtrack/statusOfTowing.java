package com.example.scantowtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class statusOfTowing extends AppCompatActivity {

    TextView name,veh,fine;
    DatabaseReference reff;
    String No;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_towing);

        name =(TextView)findViewById(R.id.owname);
        veh = (TextView)findViewById(R.id.vehno);
        fine = (TextView)findViewById(R.id.fine);
        No = getIntent().getStringExtra("numberPlate");

        onStart();
    }

    @Override
    public void onStart() {
        super.onStart();
        reff = FirebaseDatabase.getInstance().getReference().child(No);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String oname = snapshot.child("Name").getValue().toString();
                String fn = snapshot.child("Fine").getValue().toString();
                String vn = snapshot.child("vehno").getValue().toString();
                name.setText(oname);
                fine.setText(fn);
                veh.setText(vn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
