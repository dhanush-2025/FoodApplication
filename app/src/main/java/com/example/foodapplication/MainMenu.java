package com.example.foodapplication;

import static android.view.animation.AnimationUtils.*;
import static com.example.foodapplication.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {
    Button signinemail,signup,signinphone;




    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main_menu);

        signinemail = (Button) findViewById(R.id.SigninWithEmail);
        signinphone = (Button) findViewById(R.id.SigninwithPhone);
        signup = (Button) findViewById(R.id.SignUp);
        signinemail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signemail = new Intent(MainMenu.this, ChooseOne.class);
                signemail.putExtra("Home", "Email");
                startActivity(signemail);
                finish();

            }
        });
        signinphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signphone = new Intent(MainMenu.this, ChooseOne.class);
                signphone.putExtra("Home", "Phone");
                startActivity(signphone);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signup = new Intent(MainMenu.this, ChooseOne.class);
                signup.putExtra("Home", "Signup");
                startActivity(signup);
                finish();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        System.gc();

    }

}