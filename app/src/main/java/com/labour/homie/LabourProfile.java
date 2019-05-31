package com.labour.homie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.labour.homie.Adapter.ReviewAdapter;
import com.labour.homie.Entities.CardForLabours;
import com.labour.homie.Entities.CardReview;

import java.util.ArrayList;

public class LabourProfile extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CardReview> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_profile);
        Intent intent = getIntent();
        String userid = intent.getStringExtra("userid");
Log.e("MADS",userid);
        final RecyclerView labourcycle = findViewById(R.id.recyclerreview);
        labourcycle.setHasFixedSize(true);
        DatabaseReference myRef = database.getReference("reviews/"+userid);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        labourcycle.setLayoutManager(layoutManager);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                           Log.e("nads",dataSnapshot.getKey());
                           for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                           }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //CardReview cardReview = new CardReview("dsfsd","sdfsd");
        //arrayList.add(cardReview);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this,arrayList);
        labourcycle.setAdapter(reviewAdapter);
    }
}
