package com.example.seconlifeps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etcmpname, etemail, etpwd, etcpwd, etph;
    Button btnlogin, btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



    etpwd = findViewById(R.id.reg_edtCmpPassword);


    etcpwd = findViewById(R.id.reg_edtconfirmPassword);

    btnlogin = findViewById(R.id.reg_btnLogin);
    btnreg = findViewById(R.id.reg_btnRegister);

    btnreg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);

        }
    });

        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });
}
}

