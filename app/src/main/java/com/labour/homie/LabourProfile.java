package com.labour.homie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView title,description;
    SharedPreferences sharedPreferences;
    EditText editText;
    Button submitrevie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_profile);
        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        String category = intent.getStringExtra("category");
Log.e("DADS",category);
        final RecyclerView labourcycle = findViewById(R.id.recyclerreview);
        labourcycle.setHasFixedSize(true);
        DatabaseReference myRef = database.getReference("users/reviews/"+userid);
        DatabaseReference myRef2 = database.getReference("users/labour/"+category+"/"+userid);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        labourcycle.setLayoutManager(layoutManager);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                           for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                               CardReview cardReview = new CardReview(dataSnapshot1.child("name").getValue().toString(),dataSnapshot1.child("review").getValue().toString(),dataSnapshot1.getKey().toString(),dataSnapshot1.child("rating").getValue().toString());
                               Log.e("profile",dataSnapshot1.child("name").getValue().toString());
                               arrayList.add(cardReview);
                           }

                ReviewAdapter reviewAdapter = new ReviewAdapter(LabourProfile.this,arrayList);
                labourcycle.setAdapter(reviewAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        editText = (EditText)findViewById(R.id.editText);
        title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              title.setText( dataSnapshot.child("name").getValue().toString());
              description.setText(dataSnapshot.child("details").getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
editText = (EditText)findViewById(R.id.editText);
submitrevie = (Button)findViewById(R.id.button);
submitrevie.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(LabourProfile.this,"the review section should not be empty",Toast.LENGTH_LONG).show();




        }
        else{


            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);

String username = sharedPreferences.getString("username","");
            DatabaseReference myRef3 = database.getReference("users/reviews/"+userid);



        }
    }
});

    }
}
