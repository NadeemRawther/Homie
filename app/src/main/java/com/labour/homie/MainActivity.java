package com.labour.homie;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Button login, signup;
    EditText userid, password;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/user");
    DatabaseReference myRef2 = database.getReference("users/labour");
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        userid = (EditText) findViewById(R.id.userid);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user;
                String pass;
                user = userid.getText().toString();
                pass = password.getText().toString();
                logMethod(user, pass);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertBox();
            }
        });
    }
    public void showAlertBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View mView = View.inflate(MainActivity.this, R.layout.pageforsignup, null);
        /*editText = (EditText)mView.findViewById(R.id.editText36);*/
        builder.setView(mView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        final Button signupfor = (Button) mView.findViewById(R.id.signlabour);
        final Button signupforsocial = (Button) mView.findViewById(R.id.signuser);
        signupfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Signupforlabour.class);
                startActivity(intent2);
                alertDialog.cancel();
            }
        });
        signupforsocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, Signupforuser.class);
                startActivity(intent3);
                alertDialog.cancel();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String us = sharedPreferences.getString("username", "");
        String ps = sharedPreferences.getString("password", "");
        logMethod(us, ps);
    }
    public void logMethod(final String user, final String pass) {
        if (user.isEmpty() && pass.isEmpty()) {
            Toast.makeText(MainActivity.this, "userid and password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user)) {
                    if (dataSnapshot.child(user).child("password").getValue().equals(pass)) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("username", user);
                        editor.putString("password", pass);
                        editor.putString("name",dataSnapshot.child(user).child("name").getValue().toString());
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Enter as user", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, UserPage.class);
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
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.hasChild(user)) {
                        if (dataSnapshot1.child(user).child("password").getValue().equals(pass)) {
                            sharedPreferences = getApplicationContext().getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                            editor = sharedPreferences.edit();
                            editor.putString("username", user);
                            editor.putString("password", pass);
                            editor.apply();
                            Log.e("MASTER", dataSnapshot1.getKey().toString());
                            Toast.makeText(MainActivity.this, "Enter as labour", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LabourProfile.class);
                            intent.putExtra("userid", user);
                            intent.putExtra("category", dataSnapshot1.getKey());
                            intent.putExtra("labour", "labour");
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
