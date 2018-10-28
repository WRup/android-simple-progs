package com.example.bgolab.lab02;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "myPreferences";
    SharedPreferences prefs;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.editText);
        prefs = getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE);
        String tempPref = prefs.getString("myPreferences","");
        editText.setText(tempPref);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("myPreferences",editText.getText().toString());
                editor.apply();
                Toast.makeText(getBaseContext(),"Data saved", Toast.LENGTH_LONG).show();
                System.out.println(editText.getText());
            }
        });
    }
}
