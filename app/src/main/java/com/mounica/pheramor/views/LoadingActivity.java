package com.mounica.pheramor.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mounica.pheramor.R;

/**
 * Initial sign-up page
 */
public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Button signUp = findViewById(R.id.button_signup);
        signUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
