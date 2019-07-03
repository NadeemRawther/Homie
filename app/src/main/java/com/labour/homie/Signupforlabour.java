package com.labour.homie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Signupforlabour extends AppCompatActivity {
EditText userid,name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforlabour);
        userid = (EditText)findViewById(R.id.useriduser);
        name = (EditText)findViewById(R.id.nameuser);
        password = (EditText)findViewById(R.id.passuser);






    }
}
