package com.mounica.pheramor.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mounica.pheramor.R;

/**
 * End Activity after finishing sign-up
 */
public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        TextView success = findViewById(R.id.text_phera);
        success.setText(getResources().getString(R.string.ready));
        Button continueButton = findViewById(R.id.button_signup);

        continueButton.setVisibility(View.GONE);
    }
}
