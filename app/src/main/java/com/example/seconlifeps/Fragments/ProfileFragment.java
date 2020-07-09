package com.example.seconlifeps.Fragments;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.seconlifeps.LoginActivity;
import com.example.seconlifeps.R;
import com.example.seconlifeps.RegisterActivity;
import com.example.seconlifeps.model.SQLiteHelper;

public class ProfileFragment extends Fragment{

    public static SQLiteHelper mySqliteHelper;

    String userId;

    EditText etemail, etfname,etlname, etphone, etdob, etpwd, etcpwd;
    ImageView ivPhoto;
    Button  btnupdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment,container,false);

        etemail = view.findViewById(R.id.pro_edtMail);
        etfname = view.findViewById(R.id.pro_edtFname);
        etlname = view.findViewById(R.id.pro_edtLname);
        etphone = view.findViewById(R.id.pro_edtPhone);
        etdob   = view.findViewById(R.id.pro_edtDOB);
        etpwd   = view.findViewById(R.id.pro_edtPassword);
        etcpwd  = view.findViewById(R.id.pro_edtConfirmPassword);

        btnupdate = view.findViewById(R.id.pro_btnUpdate);

        userId="0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId","0");
        }
        Log.d("Profile Fragment",userId.toString());


        // Search user by id.
        //creating db
        mySqliteHelper = new SQLiteHelper(this.getContext(),"RECORDDB1.sqlite",null,1);

        String sql = " CREATE TABLE IF NOT EXISTS User (us_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "us_email VARCHAR, us_password VARCHAR, us_firstName VARCHAR, us_lastName VARCHAR, us_dob DATE , \n" +
                "us_image BLOB, us_latitude VARCHAR, us_longitude VARCHAR, us_lastLogin DATE)";
        mySqliteHelper.queryData(sql);

        String sqlstr = "SELECT us_id,us_email,us_password, us_firstName, us_lastName, us_dob " +
                        "FROM User WHERE us_id = " + userId;
        Cursor cursor = mySqliteHelper.getData(sqlstr);

        int c = cursor.getCount();
        if (c == 0)
        {
            Log.d("User:","NOT FOUND");
            System.out.println("Users: "+c);
            Toast.makeText(getActivity(),"No User found", Toast.LENGTH_SHORT).show();
            // show error ;
        }
        // Valid
        else
        {
            Log.d("User:","FOUND");
            System.out.println("Users: "+c);
        }
        while(cursor.moveToNext())
        {
            int id       = cursor.getInt(0);
            String email = cursor.getString(1);
            String pwd  = cursor.getString(2);
            String fname  = cursor.getString(3);
            String lname  = cursor.getString(4);
            String dob  = cursor.getString(5);
            //byte[] image = cursor.getBlob(4);
            //String audio   = cursor.getString(6);
            // Display record to controls
            etemail.setText(cursor.getString(1));
            etpwd.setText(cursor.getString(2));
            etcpwd.setText(cursor.getString(2));
            etfname.setText(cursor.getString(3));
            etlname.setText(cursor.getString(4));
            etphone.setText("647-434-4323");
            etdob.setText(cursor.getString(5));

            Log.d("User:",email);
        }

        btnupdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email,pwd,cpwd,fname,lname,phone,dob;
                int id;

                id = Integer.parseInt(ProfileFragment.this.userId);

                email = etemail.getText().toString().trim();
                pwd   = etpwd.getText().toString().trim();
                cpwd  = etcpwd.getText().toString().trim();
                fname = etfname.getText().toString().trim();
                lname = etlname.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                dob   = etdob.getText().toString().trim();

                //VALIDATIONS
                if (email.isEmpty())
                {
                    Log.d("Mail Empty:", etemail.toString());
                    Toast.makeText(getActivity(),"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                { Log.d("Mail wrong format:", etemail.toString());
                    Toast.makeText(getActivity(),"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                if(pwd.isEmpty())
                {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(getActivity(),"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                if (pwd.length()<= 7)
                {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(getActivity(),"Password should be 7 charcters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(cpwd.isEmpty())
                {
                    Log.d("Password Empty:", etcpwd.toString());
                    Toast.makeText(getActivity(),"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!cpwd.equals(pwd))
                {
                    Log.d("Password not match:", etcpwd.toString());
                    Toast.makeText(getActivity(),"Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                //fname

                //lname

                //dob

                //phone



                // VALID
                // Update record

                try {
                    mySqliteHelper.updateProfile(email,pwd,fname,lname,phone,dob,id);
                    Log.d("Record Updated", email.toString());


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(getActivity(),"Profile Updated!", Toast.LENGTH_SHORT).show();


            }
        });







        return view;
    }
}
