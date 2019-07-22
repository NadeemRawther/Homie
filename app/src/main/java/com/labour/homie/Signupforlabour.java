package com.labour.homie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                               UserCreation userCreation = new UserCreation(names,userids,passwords);
                               myRef.child(userids).setValue(userCreation);
            }
        });





    }
}
