package com.example.raihanapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText Username;
    EditText Password;
    Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("login", MODE_PRIVATE);
        Username = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Button = (Button)findViewById(R.id.Button);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Username.getText().toString().equalsIgnoreCase("admin")
                && Password.getText().toString().equalsIgnoreCase("admin"));
                editor = pref.edit();
                editor.putString("username", Username.getText().toString());
                editor.putString("status", "login");
                editor.apply();
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                finish();
            }
        });
    }
}