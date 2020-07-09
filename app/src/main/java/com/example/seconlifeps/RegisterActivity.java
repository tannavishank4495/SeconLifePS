package com.example.seconlifeps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seconlifeps.model.SQLiteHelper;

public class RegisterActivity extends AppCompatActivity {

    public static SQLiteHelper mySqliteHelper;

    EditText etemail, etpwd, etcpwd;
    ImageView ivPhoto;
    Button btnlogin, btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ivPhoto = findViewById(R.id.reg_imageView2);
        etemail = findViewById(R.id.reg_edtEmail);
        etpwd = findViewById(R.id.reg_edtCmpPassword);
        etcpwd = findViewById(R.id.reg_edtconfirmPassword);
        btnlogin = findViewById(R.id.reg_btnLogin);
        btnreg = findViewById(R.id.reg_btnRegister);


        // verifying db table
        mySqliteHelper = new  SQLiteHelper(this,"RECORDDB1.sqlite",null,1);

        String sql = " CREATE TABLE IF NOT EXISTS User (us_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "us_email VARCHAR, us_password VARCHAR, us_firstName VARCHAR, us_lastName VARCHAR, us_dob DATE , \n" +
                "us_image BLOB, us_latitude VARCHAR, us_longitude VARCHAR, us_lastLogin DATE)";

        mySqliteHelper.queryData(sql);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //VALIDATIONS
                String email,pwd,cpwd;
                email = etemail.getText().toString().trim();
                pwd   = etpwd.getText().toString().trim();
                cpwd = etcpwd.getText().toString().trim();


                //NOT VALID
                if (email.isEmpty())
                {
                    Log.d("Mail Empty:", etemail.toString());
                    Toast.makeText(RegisterActivity.this,"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                { Log.d("Mail Empty:", etemail.toString());
                    Toast.makeText(RegisterActivity.this,"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pwd.isEmpty())
                {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(RegisterActivity.this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.length()<= 7)
                {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(RegisterActivity.this,"Password should be 7 charcters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cpwd.isEmpty())
                {
                    Log.d("Password Empty:", etcpwd.toString());
                    Toast.makeText(RegisterActivity.this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!cpwd.equals(pwd))
                {
                    Log.d("Password Wrong:", etcpwd.toString());
                    Toast.makeText(RegisterActivity.this,"Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }



                // VALID

                //DB user check
                String sqlstr = "SELECT us_id,us_email FROM User WHERE us_email = '" + email + "'";
                Cursor cursor = mySqliteHelper.getData(sqlstr);
                int c = cursor.getCount();
                if (c != 0)
                {
                    Log.d("User:","already exist in db");
                    System.out.println("Users: "+c);
                    Toast.makeText(RegisterActivity.this,"User already exist, choose another email.", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Create user

                try {
                    mySqliteHelper.insertUser(etemail.getText().toString().trim(), etpwd.getText().toString().trim());
                    Toast.makeText(RegisterActivity.this,"User created Successfully",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


                //Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                //startActivity(i);

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

