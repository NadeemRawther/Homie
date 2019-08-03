package com.labour.homie;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.labour.homie.Adapter.ReviewAdapter;
import com.labour.homie.Entities.CardForLabours;
import com.labour.homie.Entities.CardReview;
import com.labour.homie.Entities.Review;

import java.util.ArrayList;

public class LabourProfile extends BaseActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ArrayList<CardReview> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog;
    TextView title,description;
    SharedPreferences sharedPreferences;
    EditText editText;
    String labour;
    RatingBar ratingBar;

    Button submitrevie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_profile);
        Intent intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        String category = intent.getStringExtra("category");
        this.labour = intent.getStringExtra("labour");
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
       Log.e("DADS",userid);

        final RecyclerView labourcycle = findViewById(R.id.recyclerreview);
        labourcycle.setHasFixedSize(true);
        DatabaseReference myRef = database.getReference("users/reviews/"+userid);
        DatabaseReference myRef2 = database.getReference("users/labour/"+category+"/"+userid);
        submitrevie = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        final ImageView imageView = (ImageView)findViewById(R.id.imglab);
        if (this.labour == "labour" ){
          editText.setVisibility(View.INVISIBLE);
           ratingBar.setVisibility(View.INVISIBLE);
           submitrevie.setVisibility(View.INVISIBLE);
       }
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

        title = (TextView)findViewById(R.id.title);
        description = (TextView)findViewById(R.id.description);
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              title.setText( dataSnapshot.child("name").getValue().toString());
              description.setText(dataSnapshot.child("details").getValue().toString());
                Glide.with(LabourProfile.this).load(dataSnapshot.child("img").getValue()).into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

             submitrevie.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(LabourProfile.this,"the review section should not be empty",Toast.LENGTH_LONG).show();
        }
        else{
            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
            final String username = sharedPreferences.getString("username","");
            DatabaseReference myRef3 = database.getReference("users/reviews/");


            myRef3.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(userid)){
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            Log.e("DADS3",username);
                            if(dataSnapshot1.hasChild(username)){
                                DatabaseReference myRef6 = database.getReference("users/reviews/"+userid+"/"+username);
                                Review review = new Review(sharedPreferences.getString("name",""),editText.getText().toString(),String.valueOf(ratingBar.getRating()));
                                myRef6.setValue(review);
                                  Log.e("DADS4",username);
                                  editText.setVisibility(View.INVISIBLE);
                                  ratingBar.setVisibility(View.INVISIBLE);
                                  submitrevie.setVisibility(View.INVISIBLE);
                                //calculaterating(ratingBar.getRating(),userid);
                            }else{
                                DatabaseReference myRef7 = database.getReference("users/reviews/"+userid);
                                Review review = new Review(sharedPreferences.getString("name",""),editText.getText().toString(),String.valueOf(ratingBar.getRating()));
                                myRef7.child(username).setValue(review);
                                Log.e("DADS5",username);
                                editText.setVisibility(View.INVISIBLE);
                                ratingBar.setVisibility(View.INVISIBLE);
                                submitrevie.setVisibility(View.INVISIBLE);
                            }
                        }
                        //String string = new String(editText.getText().toString());
                    }
                    else{
                        DatabaseReference myRef8 = database.getReference("users/reviews");
                        Review review = new Review(sharedPreferences.getString("name",""),editText.getText().toString(),String.valueOf(ratingBar.getRating()));
                        myRef8.child(userid).child(username).setValue(review);
                        Log.e("DADS6",username);
                        editText.setVisibility(View.INVISIBLE);
                        ratingBar.setVisibility(View.INVISIBLE);
                        submitrevie.setVisibility(View.INVISIBLE);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }





    }
});

    }

public void calculaterating(float fl,String userid){

        float val ;

        String get;

    DatabaseReference myRef7 = database.getReference("users/reviews/"+userid);
    myRef7.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                for (DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()){
                    dataSnapshot2.child("rating").getValue();


                }




            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });





}




    @Override
    public void onBackPressed() {
        if(labour.equals("labour")){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Exit Application?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {




                                    moveTaskToBack(true);

                              /*  Intent intent = new Intent(Users.this, MainActivity.class);
                                intent.putExtra("finish", true);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                                    finish();
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);

                                }

                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else{
            super.onBackPressed();
    }
    }


}
