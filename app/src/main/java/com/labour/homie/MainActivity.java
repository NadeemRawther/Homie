package com.labour.homie;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    EditText userid,password;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/user");
    DatabaseReference myRef2 = database.getReference("users/labour");

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        userid =(EditText)findViewById(R.id.userid);
        password = (EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user;
                String pass;
                user = userid.getText().toString();
                pass = password.getText().toString();
                logMethod(user,pass);

            }
        });
    }


    public void logMethod(final String user, final String pass){
        if(user.isEmpty() && pass.isEmpty()){
            Toast.makeText(MainActivity.this,"userid and password cannot be empty",Toast.LENGTH_LONG).show();
            return;
        }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user)){
                    if(dataSnapshot.child(user).child("password").getValue().equals(pass)) {
                        Toast.makeText(MainActivity.this, "Enter as user", Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(MainActivity.this,UserPage.class);
                       startActivity(intent);
                          return;
                             }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    if(dataSnapshot1.hasChild(user)){

                        if(dataSnapshot1.child(user).child("password").getValue().equals(pass)){

                            Toast.makeText(MainActivity.this, "Enter as labour", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,UserPage.class);
                            startActivity(intent);

                            return;


                        }

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
