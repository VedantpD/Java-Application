package com.example.javaapplication.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapplication.MainActivity;
import com.example.javaapplication.R;

public class Registration extends AppCompatActivity {

    Button btnRegister ;
    EditText etxtUserName,etxtUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegister = findViewById(R.id.btnRegister);
        etxtUserName = findViewById(R.id.etxtUserName);
        etxtUserID = findViewById(R.id.etxtUserID);

        setClicks();
    }
    private void setClicks(){
        btnRegister.setOnClickListener(view ->{
            if(fieldCheck()){
                SharedPreferences.Editor editor = this.getSharedPreferences("Credential", Context.MODE_PRIVATE).edit();
                editor.putString("user_name", etxtUserName.getText().toString().trim());
                editor.putString("user_id", etxtUserID.getText().toString().trim());
                editor.putBoolean("first_time", false);
                editor.apply();

                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private Boolean fieldCheck(){
        if(etxtUserName.getText().toString().length() == 0
                || etxtUserID.getText().toString().length() == 0){
            Toast.makeText(this,"Fields cannot be empty !",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}