package com.capstone490.nitesh.datadashboard.Views;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone490.nitesh.datadashboard.R;

import java.sql.SQLData;
import java.util.Objects;

import Models.StorePowerData;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Create_user extends AppCompatActivity {
    private EditText username;
    private EditText new_password;
    private EditText confirm_password;
    private StorePowerData store_user;

    @Override
    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_create_user);
        createNewUser();
    }
    public void createNewUser(){
        store_user = new StorePowerData(this);
        username = (EditText) findViewById(R.id.new_username);
        new_password = (EditText) findViewById(R.id.new_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        Button create_user = (Button) findViewById(R.id.create_new_user);
        create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate_user() && validate_password()){

                    // Gets the data repository in write mode
                    SQLiteDatabase db = store_user.getWritableDatabase();

                    // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    values.put(StorePowerData.Attributes.USERNAME, username.getText().toString());
                    values.put(StorePowerData.Attributes.PASSWORD, new_password.getText().toString());

                    // Insert the new row, returning the primary key value of the new row
                    db.insert(StorePowerData.Attributes.TABLE_NAME, null, values);

                    Toast.makeText(getApplicationContext(),"Creating User, Wait!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"User saved", Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(1000);
                        finish();
                        Intent intent = new Intent(Create_user.this, LoginActivity.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Enter Again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validate_user(){
        return username.length() > 4;
    }
    private boolean validate_password(){
        return Objects.equals(new_password.getText().toString(), confirm_password.getText().toString());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            Intent intent = new Intent(Create_user.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
