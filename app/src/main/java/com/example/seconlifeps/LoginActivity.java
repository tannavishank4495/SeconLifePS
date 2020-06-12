package com.example.seconlifeps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etempemail, etpwd;
    Button btnlogin, btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etempemail = findViewById(R.id.login_edtUserEmail);
        etpwd = findViewById(R.id.login_edtPassword);
        btnlogin = findViewById(R.id.login_btnLogin);
        btnreg = findViewById(R.id.login_btnRegistration);
        btnlogin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            // Validate
                                            // Not valid cases




                                            // Valid
                                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(i);
                                        }
                                    });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
             //   Intent i = new Intent(LoginActivity.this, PaymentActivity.class);
               // startActivity(i);
            }
        });
    }
}
