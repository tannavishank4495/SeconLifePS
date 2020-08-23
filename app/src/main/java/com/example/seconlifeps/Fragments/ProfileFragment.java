package com.example.seconlifeps.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
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

import com.example.seconlifeps.R;
import com.example.seconlifeps.model.SQLiteHelper;
import com.example.seconlifeps.util.CircleTransform;
import com.example.seconlifeps.util.Preferences;
import com.example.seconlifeps.util.Utils;
import com.mindorks.paracamera.Camera;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ProfileFragment extends Fragment {

    public static SQLiteHelper mySqliteHelper;

    final int REQUEST_CODE_GALLERY = 999;

    String userId;

    EditText etemail, etfname, etlname, etphone, etdob, etpwd, etcpwd;
    ImageView ivPhoto;
    Button btnupdate;
    Camera camera;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        ivPhoto = (ImageView) view.findViewById(R.id.pro_image);
        etemail = view.findViewById(R.id.pro_edtMail);
        etfname = view.findViewById(R.id.pro_edtFname);
        etlname = view.findViewById(R.id.pro_edtLname);
        etphone = view.findViewById(R.id.pro_edtPhone);
        etdob = view.findViewById(R.id.pro_edtDOB);
        etpwd = view.findViewById(R.id.pro_edtPassword);
        etcpwd = view.findViewById(R.id.pro_edtConfirmPassword);

        btnupdate = view.findViewById(R.id.pro_btnUpdate);

        camera = new Camera.Builder()
                .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("ali_" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .setImageHeight(1000)// it will try to achieve this height as close as possible maintaining the aspect ratio;
                .build(getActivity());

        userId = "0";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userId = bundle.getString("userId", "0");
        }
        Log.d("Profile Fragment", userId.toString());

        Preferences preferences = new Preferences();

        if (!TextUtils.isEmpty(preferences.getFile(getActivity()))) {
            File file = new File(preferences.getFile(getActivity()));
            Picasso.with(getActivity())
                    .load(file)
                    .transform(new CircleTransform())
                    .into(ivPhoto);
        }

        // Search user by id.
        //creating db
        mySqliteHelper = new SQLiteHelper(this.getContext(), "RECORDDB1.sqlite", null, 1);

        String sql = " CREATE TABLE IF NOT EXISTS User (us_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "us_email VARCHAR, us_password VARCHAR, us_firstName VARCHAR, us_lastName VARCHAR, us_dob DATE , \n" +
                "us_image BLOB, us_latitude VARCHAR, us_longitude VARCHAR, us_lastLogin DATE)";
        mySqliteHelper.queryData(sql);

        String sqlstr = "SELECT us_id,us_email,us_password, us_firstName, us_lastName, us_dob " +
                "FROM User WHERE us_id = " + userId;
        Cursor cursor = mySqliteHelper.getData(sqlstr);

        int c = cursor.getCount();
        if (c == 0) {
            Log.d("User:", "NOT FOUND");
            System.out.println("Users: " + c);
            Toast.makeText(getActivity(), "No User found", Toast.LENGTH_SHORT).show();
            // show error ;
        }
        // Valid
        else {
            Log.d("User:", "FOUND");
            System.out.println("Users: " + c);
        }
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String email = cursor.getString(1);
            String pwd = cursor.getString(2);
            String fname = cursor.getString(3);
            String lname = cursor.getString(4);
            String dob = cursor.getString(5);
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

            Log.d("User:", email);
        }
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCameraPermission();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, pwd, cpwd, fname, lname, phone, dob;
                int id;

                id = Integer.parseInt(ProfileFragment.this.userId);

                email = etemail.getText().toString().trim();
                pwd = etpwd.getText().toString().trim();
                cpwd = etcpwd.getText().toString().trim();
                fname = etfname.getText().toString().trim();
                lname = etlname.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                dob = etdob.getText().toString().trim();

                //VALIDATIONS
                if (email.isEmpty()) {
                    Log.d("Mail Empty:", etemail.toString());
                    Toast.makeText(getActivity(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Log.d("Mail wrong format:", etemail.toString());
                    Toast.makeText(getActivity(), "Please enter valid email address", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pwd.isEmpty()) {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(getActivity(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pwd.length() <= 7) {
                    Log.d("Password Empty:", etpwd.toString());
                    Toast.makeText(getActivity(), "Password should be 7 charcters long", Toast.LENGTH_SHORT).show();
                    return;
                } else if (cpwd.isEmpty()) {
                    Log.d("Password Empty:", etcpwd.toString());
                    Toast.makeText(getActivity(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!cpwd.equals(pwd)) {
                    Log.d("Password not match:", etcpwd.toString());
                    Toast.makeText(getActivity(), "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                //fname

                //lname

                //dob

                //phone


                // VALID
                // Update record

                try {
                    mySqliteHelper.updateProfile(email, pwd, fname, lname, phone, dob, id);
                    Log.d("Record Updated", email.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getActivity(), "Profile Updated!", Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

    private void getCameraPermission() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(getActivity(), permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {

                Utils.showSelectDialog(getActivity(), new Utils.DialogClickEvent() {
                    @Override
                    public void positiveClick() {
                        try {
                            camera.takePicture();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void negativeClick() {
//                        Gallery
                        try {
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, 202);
                        /*    ImagePicker.Companion.with(getActivity())
                                    .galleryOnly()    //User can only select image from Gallery
                                    .start();*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Camera.REQUEST_TAKE_PHOTO) {
            Bitmap bitmap = camera.getCameraBitmap();
            if (bitmap != null) {
                ivPhoto.setImageBitmap(bitmap);
                File image = persistImage(bitmap, "profile");
                if (image != null) {
                    Preferences preferences = new Preferences();
                    preferences.saveFile(getActivity(), image.getAbsolutePath());
                    Picasso.with(getActivity())
                            .load(image)
                            .transform(new CircleTransform())
                            .into(ivPhoto);
                }


            } else {
                Toast.makeText(getActivity(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }


        } else if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 202) {
                Uri selectedImage = data.getData();
                File imageFile = new File(getPath(selectedImage));
                Preferences preferences = new Preferences();
                preferences.saveFile(getActivity(), imageFile.getAbsolutePath());
                Picasso.with(getActivity())
                        .load(imageFile)
                        .transform(new CircleTransform())
                        .into(ivPhoto);

            }
            //Image Uri will not be null for RESULT_OK
            //You can get File object from intent
               /* File file = ImagePicker.Companion.getFile(data);
                if (file != null) {
                    Preferences preferences = new Preferences();
                    preferences.saveFile(getActivity(), file.getAbsolutePath());
                    Picasso.with(getActivity())
                            .load(file)
                            .transform(new CircleTransform())
                            .into(ivPhoto);
                }*/

        } else {
            Toast.makeText(getActivity(), "Picture not taken!", Toast.LENGTH_SHORT).show();
        }
    }

    private File persistImage(Bitmap bitmap, String name) {
        File filesDir = getActivity().getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
        return null;
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }
}
