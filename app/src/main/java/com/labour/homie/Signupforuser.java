package com.labour.homie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Signupforuser extends AppCompatActivity {
    EditText charge,details, img , name,phone ,paswor,userid,place;
    String ch,detai,imge,nam,phon,pasw,use,pla;
    Button btnuplo,subforlabours;
    ImageView imgforsignup;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    StorageReference storageRef = storage.getReference();
    private Uri filePath;
    String link2 = "";
    Spinner categorylabour;
    private final int PICK_IMAGE_REQUEST = 71;
    String[] SbloodGroup = { "AC mechanic","Carpenter","Driver","Electrician","Gardener","Plumber","Servant","Treecutter"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforuser2);

        charge = (EditText)findViewById(R.id.chargeperhour);
        details =(EditText)findViewById(R.id.detaila);
        name = (EditText)findViewById(R.id.names);
        phone = (EditText)findViewById(R.id.phne);
        paswor = (EditText)findViewById(R.id.psword);
        userid = (EditText)findViewById(R.id.uidsignup);
        place = (EditText)findViewById(R.id.placed);
        btnuplo = (Button)findViewById(R.id.imguplobtn);
        imgforsignup = (ImageView)findViewById(R.id.imgforsignup);
        categorylabour =(Spinner)findViewById(R.id.categspinn);
        btnuplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        ArrayAdapter<String> adapter_category = new ArrayAdapter<String>(this, R.layout.spinner_textview, SbloodGroup);
        adapter_category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorylabour.setAdapter(adapter_category);

subforlabours=(Button)findViewById(R.id.subforlabour);
subforlabours.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

                String link = categorylabour.getSelectedItem().toString();
        ch = charge.getText().toString();
        detai = details.getText().toString();
        nam  = name.getText().toString();
        phon = phone.getText().toString();
        pasw = paswor.getText().toString();
        use = userid.getText().toString();
        pla = place.getText().toString();
        Bitmap image=((BitmapDrawable)imgforsignup.getDrawable()).getBitmap();


        switch (link) {
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
                link2 = "gardner";
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

        submitdatas(use, pasw,link2,nam,phon,pla,ch,detai,image);





    }
});
    }

    public void submitdatas(final String userid, final String password, String link2 , final String name, final String  phone, final String place , final String charge, final String details, final Bitmap img)
    {
        myRef = database.getReference("users/labour/"+link2);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userid)){
                    Toast.makeText(Signupforuser.this,"Userid Already Exists",Toast.LENGTH_LONG).show();




                }
                else{

                    uploadimg(userid,password,myRef,name,phone,place,charge,details,img);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




    public void uploadimg(final String userid, final String password, final DatabaseReference myRef, final String name, final String phone, final String place , final String charge, final String details, Bitmap img){
        final StorageReference imagesRef = storageRef.child("students/"+userid+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = imagesRef.putBytes(data);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return imagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.e("MASTER",downloadUri.toString());
                    LabourModel labourModel = new LabourModel(password,name,details,place,phone,charge,downloadUri.toString(),5.0);
                    myRef.child(userid).setValue(labourModel);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });





    }











    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgforsignup.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
