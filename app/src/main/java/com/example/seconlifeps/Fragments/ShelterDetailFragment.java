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
import com.example.seconlifeps.model.SQLiteHelper;

public class ShelterDetailFragment extends Fragment{

    public static SQLiteHelper mySqliteHelper;
    String userId;
    Boolean paymentRecord;

    EditText etname, etcnumber, edtCcv,edtdate;
    Switch swDefa;
    Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shelterdetail_fragment,container,false);

      //  etname    = view.findViewById(R.id.reg_edtHname);
       // etcnumber = view.findViewById(R.id.reg_edtCNumber);
        //edtCcv    = view.findViewById(R.id.reg_edtCcv);
        //edtdate   = view.findViewById(R.id.reg_edtExpire);
        //swDefa = view.findViewById(R.id.simpleSwitch);
        //btnUpdate = view.findViewById(R.id.reg_btnRegister);

        paymentRecord = false;

        userId="0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId","0");
        }
        Log.d("Payment Fragment",userId.toString());




        //VALIDATIONS

        return view;
    }
}
