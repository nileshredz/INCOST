package com.example.incost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InitScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_screen);
        ImageView start,git,link;

        ///////////////////
        // Find view by Id
        ///////////////////

        start = findViewById(R.id.button);
        git = findViewById(R.id.github);
        link = findViewById(R.id.linkedIn);

        /////////////////////////////////////////
        // Redirect to the  MainActivity layout
        ////////////////////////////////////////

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ///////////////////////
        // Open GitHub Account
        ///////////////////////

        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://github.com/nileshredz");
            }
        });

        ///////////////////////
        // Open LinkedIn Account
        ///////////////////////

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.linkedin.com/in/nilesh-ohol/");
            }
        });


    }
    /////////////////////////////////////////////////////////////////
    // Function to open the URL using desired Browser or Application.
    /////////////////////////////////////////////////////////////////

    private void clicked_btn(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
