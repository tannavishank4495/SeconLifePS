package com.example.seconlifeps.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);

    }

    public void queryData(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    //insert
    public void insertData(String title, String desc, String date, byte[] photo, String category, String audio)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Notes VALUES(NULL,?,?,?,?,?,?)";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,title);
        st.bindString(2,desc);
        st.bindString(3,date.toString());

        st.bindBlob(4,photo);
        st.bindString(5,category);
        st.bindString(6,audio);

        st.executeInsert();
    }

    public void insertUser(String email, String password)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO User(us_email,us_password) VALUES(?,?)";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,email);
        st.bindString(2,password);
        //st.bindString(3,date.toString());
        //st.bindBlob(4,photo);
        //st.bindString(5,category);
        //st.bindString(6,audio);

        st.executeInsert();
    }

    public void insertUserPayment(int userId)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO UserPayment(us_id) VALUES(?)";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindDouble(1,userId);

        st.executeInsert();
    }


    //insert Category
    public void insertDataCat(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO Category VALUES(NULL,?)";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,name);
        st.executeInsert();
    }
    //update
    public void updateData(String title, String desc , String date , byte[] photo , String category, String audio, int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE Notes SET title=?, desc=?, image=? WHERE id=?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,title);
        st.bindString(2,desc);
        st.bindBlob(3,photo);
        st.bindDouble(4,(double)id);
        st.execute();

        db.close();

    }

    public void updateProfile(String email, String pwd , String fname , String lname, String phone, String dob, int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE User SET us_email=?, us_password=?, us_firstName=?, us_lastName=?, us_dob=? WHERE us_id=?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,email);
        st.bindString(2,pwd);
        st.bindString(3,fname);
        st.bindString(4,lname);
        st.bindString(5,dob);
        st.bindDouble(6,(double)id);
        st.execute();

        db.close();
    }

    public void updatePayment(String name , String number, String ccv, String expire, int defa, int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE UserPayment SET up_holderName=?, up_cardNumber=?, up_ccv=?, up_expire=?, up_default=? WHERE us_id=?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,name);
        st.bindString(2,number);
        st.bindString(3,ccv);
        st.bindString(4,expire);
        st.bindDouble(5,defa);
        st.bindDouble(6,(double)id);
        st.execute();

        db.close();
    }

    public void updateUserLocation(double lat, double lon, int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE User SET us_latitude=?, us_longitude=? WHERE us_id=?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindString(1,String.valueOf(lat));
        st.bindString(2,String.valueOf(lon));
        st.bindDouble(3,(double)id);
        st.execute();
        db.close();
    }

    //public void insertUserPayment(int userId, String holderName, String numberCard, String ccv, String expire, Boolean def)
    //{

    //delete
    public void deleteData(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Notes WHERE id = ?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindDouble(1,(double)id);
        st.execute();
        db.close();

    }

    //delete category
    public void deleteDataCat(int ids)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Category WHERE id = ?";
        SQLiteStatement st = db.compileStatement(sql);
        st.clearBindings();
        st.bindDouble(1,(double)ids);
        st.execute();
        db.close();

    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
