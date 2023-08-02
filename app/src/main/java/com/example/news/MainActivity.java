package com.example.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editTextText,editTextText2;
    ImageView imageView;
    FirebaseAuth firebaseAuth;
    DatabaseReference rootref,demoref;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextText2=findViewById(R.id.editTextText2);
        editTextText=findViewById(R.id.editTextText);
        imageView=findViewById(R.id.imageView6);
        textView=findViewById(R.id.textView2);
        rootref = FirebaseDatabase.getInstance().getReference();
        demoref = rootref.child("demo");

        firebaseAuth=FirebaseAuth.getInstance();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
 startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }


    public void register() {
        String gmail = editTextText2.getText().toString().trim();
        String password = editTextText.getText().toString().trim();
        if(gmail.isEmpty()){
            Toast.makeText(this, "Give proper gmail", Toast.LENGTH_SHORT).show();
        }
        if(password.length()<8){
            Toast.makeText(this, "Give proper password", Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(gmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Reg comp", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), android.R.menu.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "Try again...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}