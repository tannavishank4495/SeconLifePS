package com.example.seconlifeps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seconlifeps.model.SQLiteHelper;
import com.example.seconlifeps.model.User;

public class LoginActivity extends AppCompatActivity {

    public static SQLiteHelper mySqliteHelper;

    String userId;

    EditText etempemail, etpwd;
    Button btnlogin, btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating db
        mySqliteHelper = new SQLiteHelper(this,"RECORDDB1.sqlite",null,1);

        //dropping db
        //mySqliteHelper.queryData("DROP TABLE Notes");

        String sql = " CREATE TABLE IF NOT EXISTS User (us_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "us_email VARCHAR, us_password VARCHAR, us_firstName VARCHAR, us_lastName VARCHAR, us_dob DATE , \n" +
                "us_image BLOB, us_latitude VARCHAR, us_longitude VARCHAR, us_lastLogin DATE)";

        mySqliteHelper.queryData(sql);

        etempemail = findViewById(R.id.login_edtUserEmail);
        etpwd = findViewById(R.id.login_edtPassword);

        btnlogin = findViewById(R.id.login_btnLogin);
        btnreg = findViewById(R.id.login_btnRegistration);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //VALIDATIONS
                String mail,pwd;
                mail = etempemail.getText().toString().trim();
                pwd   = etpwd.getText().toString().trim();

                // Validate

                if (mail.isEmpty())
                {
                    Log.d("Mail Empty:", mail.toString());
                    Toast.makeText(LoginActivity.this,"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
                { Log.d("Mail Empty:", etempemail.toString());
                    Toast.makeText(LoginActivity.this,"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pwd.isEmpty())
                {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(LoginActivity.this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Not valid cases
               // String sqlstr = "SELECT * FROM Notes WHERE category = '"+ catName.trim()+"'" + sqlOrder;
                String sqlstr = "SELECT us_id,us_email FROM User WHERE us_email = '" + mail + "'";

                Cursor cursor = mySqliteHelper.getData(sqlstr);

                //myListNote.clear();
                Log.d("User:","0");
                int c = cursor.getCount();
                if (c == 0)
                {
                    Log.d("User:","NOT FOUND");
                    System.out.println("Users: "+c);
                    Toast.makeText(LoginActivity.this,"No User found", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Valid
                else
                {
                    Log.d("User:","FOUND");
                    System.out.println("Users: "+c);
                }
                while(cursor.moveToNext())
                {
                    Integer id       = cursor.getInt(0);
                    String email = cursor.getString(1);
                    //String fname  = cursor.getString(2);
                    //String lname  = cursor.getString(3);
                    //byte[] image = cursor.getBlob(4);
                    //String category = cursor.getString(5);
                    //String audio   = cursor.getString(6);
                    //// add to list
                    //myListNote.add(new User(id,email,"123",fname,lname,"11/11/11",image,"11.11","12.2","11/11/11"));
                    Log.d("User:",email);
                    userId = id.toString();
                }

                Toast.makeText(LoginActivity.this,"Welcome!", Toast.LENGTH_SHORT).show();
                // Create global/session User object


                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("USER_ID",userId);
                startActivity(i);
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                //Intent i = new Intent(LoginActivity.this, PaymentActivity.class);
                startActivity(i);
            }
        });
    }
}
