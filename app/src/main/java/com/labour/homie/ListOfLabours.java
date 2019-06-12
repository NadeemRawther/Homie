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
import com.labour.homie.Adapter.LabourAdapter;
import com.labour.homie.Entities.CardForLabours;

import java.util.ArrayList;

public class ListOfLabours extends AppCompatActivity {
    String link2 ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CardForLabours> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_labours);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        switch (link){
            case "AC mechanic":
                link2 = "acmechanic";
                break;
            case "Carpenter":
                link2 = "carpenter";
                break;
            case "Driver":
                link2 = "driver";
                break;
            case "Electrician":
                link2 = "electrician";
                break;
            case "Gardener":
                link2 = "gardener";
                break;
            case "Plumber":
                link2 = "plumber";
                break;
            case "Servant":
                link2 = "servant";
                break;
            case "Treecutter":
                link2 = "treecutter";
                break;
        }
        progressDialog = new ProgressDialog(ListOfLabours.this);
        progressDialog.setMessage("please Wait while loading");
        progressDialog.show();
        final RecyclerView labourcycle = findViewById(R.id.recyclerforlabour);
        labourcycle.setHasFixedSize(true);

        Log.e("Nadeem2",link2);
        DatabaseReference myRef = database.getReference("users/labour/"+link2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        labourcycle.setLayoutManager(layoutManager);
myRef.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

            Log.e("Nadeem",dataSnapshot1.getKey().toString());
           CardForLabours cardForLabours = new CardForLabours(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("phone").getValue().toString(),dataSnapshot1.child("place").getValue().toString(),dataSnapshot1.child("charge").getValue().toString(),dataSnapshot1.getKey().toString(),dataSnapshot1.child("rating").getValue().toString());
           arrayList.add(cardForLabours);

        }
        LabourAdapter labourAdapter = new LabourAdapter(ListOfLabours.this,arrayList);
        labourcycle.setAdapter(labourAdapter);
        Log.e("VASU",arrayList.toString());
        progressDialog.dismiss();
    }
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
    }
}
