package com.capstone490.nitesh.datadashboard.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.capstone490.nitesh.datadashboard.R;

public class UserProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        TextView username = (TextView) findViewById(R.id.existing_username);
        username.setText("Username: " + getIntent().getExtras().getString("Username"));
        TextView rotating_power = (TextView) findViewById(R.id.total_power_rotational);
        rotating_power.setText("Total Power Rotating Panel: " +"1050 "+"Watts");
        TextView fixed_power = (TextView) findViewById(R.id.total_power_fixed);
        fixed_power.setText("Total Power Fixed Panel: 659 " +"Watts");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
