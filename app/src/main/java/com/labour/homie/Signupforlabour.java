package com.labour.homie;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signupforlabour extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/user");



EditText userid,name,password;
String names,passwords,userids;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforlabour);
        userid = (EditText)findViewById(R.id.useriduser);
        name = (EditText)findViewById(R.id.nameuser);
        password = (EditText)findViewById(R.id.passuser);
        submit = (Button)findViewById(R.id.submitforuser);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               names = name.getText().toString();
               passwords = password.getText().toString();
               userids = userid.getText().toString();
               myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.hasChild(userids)) {
                           Toast.makeText(Signupforlabour.this,"User Already exist",Toast.LENGTH_LONG).show();
                           return;
                       }
                       else {
                           UserCreation userCreation = new UserCreation(names, passwords);
                           myRef.child(userids).setValue(userCreation);
                           Intent intent = new Intent(Signupforlabour.this,MainActivity.class);
                           intent.putExtra("finish", true);
                           intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                   Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                   Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);

                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

            }
        });





    }
}
