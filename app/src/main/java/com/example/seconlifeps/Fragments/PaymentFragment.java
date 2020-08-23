package com.example.seconlifeps.Fragments;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.seconlifeps.R;
import com.example.seconlifeps.RegisterActivity;
import com.example.seconlifeps.model.SQLiteHelper;

public class PaymentFragment extends Fragment{

    public static SQLiteHelper mySqliteHelper;
    String userId;
    Boolean paymentRecord;

    EditText etname, etcnumber, edtCcv,edtdate;
    Switch swDefa;
    Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payment_fragment,container,false);

        etname    = view.findViewById(R.id.reg_edtHname);
        etcnumber = view.findViewById(R.id.reg_edtCNumber);
        edtCcv    = view.findViewById(R.id.reg_edtCcv);
        edtdate   = view.findViewById(R.id.reg_edtExpire);
        swDefa = view.findViewById(R.id.simpleSwitch);
        btnUpdate = view.findViewById(R.id.reg_btnRegister);

        paymentRecord = false;

        userId="0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId","0");
        }
        Log.d("Payment Fragment",userId.toString());


        //Verifying db
        mySqliteHelper = new SQLiteHelper(this.getContext(),"RECORDDB1.sqlite",null,1);

    //    String sql = " DROP TABLE UserPayment";
        String sql = " CREATE TABLE IF NOT EXISTS UserPayment (up_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "us_id INTEGER, up_holderName VARCHAR, up_cardNumber VARCHAR, up_ccv VARCHAR, up_expire VARCHAR, up_default INTEGER)";
        mySqliteHelper.queryData(sql);

        ///*
        // Search user by id.
        String sqlstr = "SELECT up_id, us_id, up_holderName, up_cardNumber, up_ccv, up_expire, up_default " +
                "FROM UserPayment WHERE us_id = " + userId;
        Cursor cursor = mySqliteHelper.getData(sqlstr);

        int c = cursor.getCount();
        if (c == 0)
        {
            paymentRecord = false;
            Log.d("User:","NOT FOUND");
            System.out.println("Users: "+c);
            Toast.makeText(getActivity(),"No User found", Toast.LENGTH_SHORT).show();

            // Create record
            try {
                mySqliteHelper.insertUserPayment(Integer.parseInt(userId));
                Toast.makeText(getActivity(),"Payment record created Successfully",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            //
        }
        else
        {
            // User found
            paymentRecord = true;
            Log.d("User:","FOUND");
            System.out.println("UserPayment: "+c);
        }

        if (paymentRecord)
        {
            Log.d("PaymentRecord","found");
        }
        else
        {
            Log.d("PaymentRecord","not found and created");
        }

        while(cursor.moveToNext() && paymentRecord)
        {
        //    up_id, us_id, up_holderName, up_cardNumber, up_ccv, up_expire, up_default
            int uid       = cursor.getInt(0);
            int pid       = cursor.getInt(1);
            String name = cursor.getString(2);
            String number = cursor.getString(3);
            String ccv  = cursor.getString(4);
            String exipry  = cursor.getString(5);
            Integer defa  = cursor.getInt(6);

            etname.setText(name);
            etcnumber.setText(number);
            edtCcv.setText(ccv);
            edtdate.setText(exipry);
            if (defa == 1) {
                swDefa.setTextOn("ON");
                swDefa.setChecked(true);
            }
            else
            {
                swDefa.setTextOff("OFF");
            }


            Log.d("User:",String.valueOf(defa));
        }


//*/

        //VALIDATIONS
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //VALIDATIONS
                String ename, cnum, ccv,cdate;
                Integer defa;
                ename = etname.getText().toString().trim();
                cnum = etcnumber.getText().toString().trim();
                ccv = edtCcv.getText().toString().trim();
                cdate = edtdate.getText().toString().trim();


                if (swDefa.isChecked())
                    defa = 1;
                else
                    defa = 0;

                if (ename.isEmpty())
                {
                    Log.d("Name Empty:", etname.toString());
                    Toast.makeText(getActivity(),"Please Enter the holder card Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cnum.length()!=16)
                {
                    Log.d("Number Empty:", etname.toString());
                    Toast.makeText(getActivity(),"Please Enter valid card number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ccv.length()!=3)
                {
                    Log.d("CCV Empty:", etname.toString());
                    Toast.makeText(getActivity(),"Please Enter valid CCV", Toast.LENGTH_SHORT).show();
                    return;
                }
               // if (cdate == null || !cdate.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
               // {
               //     Log.d("Date Empty:", etname.toString());
               //     Toast.makeText(getActivity(),"Please Enter valid Date", Toast.LENGTH_SHORT).show();
               //     return;
               // }

                try {
                    mySqliteHelper.updatePayment(ename,cnum,ccv,cdate,defa,Integer.valueOf(userId));
                    Log.d("Payment Updated", userId);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(getActivity(),"Payment Data Updated!", Toast.LENGTH_SHORT).show();


            }
            //NOT VALID
            // VALID
        });
        return view;
    }
}
