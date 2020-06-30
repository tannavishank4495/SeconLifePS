package com.example.seconlifeps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

//        "CREATE TABLE IF NOT EXISTS Notes(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "title VARCHAR, desc VARCHAR, date DATE , image BLOB, category VARCHAR, audio VARCHAR)"


        mySqliteHelper.queryData(sql);




        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //VALIDATIONS
                String email,pwd;
                email = etemail.getText().toString().trim();
                pwd   = etpwd.getText().toString().trim();




                //NOT VALID

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
                    Toast.makeText(RegisterActivity.this,"User Added Successfully",Toast.LENGTH_SHORT).show();
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

