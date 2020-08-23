package com.example.seconlifeps;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBName = "SheltersDB";
    final static String TBName_User = "Users";
    final static String TBName_UserPayment = "UserPayment";
    final static String TBName_Business = "Business";
    final static String TBName_Reviews = "Reviews";

    public DBHelper(Context context) {
        super(context, DBName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = " CREATE TABLE " + TBName_User + "(us_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "us_email VARCHAR, us_password VARCHAR, us_firstName VARCHAR, us_lastName VARCHAR, us_dob DATE , \n" +
                    "us_image BLOB, us_latitude VARCHAR, us_longitude VARCHAR, us_lastLogin DATE)";
            db.execSQL(sql);

            sql = " CREATE TABLE " + TBName_UserPayment + "(up_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "us_id INTEGER, up_holderName VARCHAR, up_cardNumber VARCHAR, up_ccv VARCHAR, up_expire VARCHAR, up_default BOOLEAN )";
            db.execSQL(sql);

            sql = " CREATE TABLE " + TBName_Business + "(bu_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "bu_name VARCHAR, bu_address VARCHAR, bu_lastName VARCHAR, bu_latitude VARCHAR, bu_longitude VARCHAR, bu_contactNo VARCHAR,\n" +
                    "bu_email VARCHAR,\n" + "bu_description,\n" + "bu_price DECIMAL , \n" + "bu_visitDays VARCHAR,\n" + "bu_visitHours VARCHAR)";
            db.execSQL(sql);

            sql = " CREATE TABLE " + TBName_Business + "(rv_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "bu_id INTEGER, us_id INTEGER, rv_date DATE, rv_description VARCHAR, rv_stars INTEGER )";
            db.execSQL(sql);
        }
        catch (Exception e)
        {
            Log.e("DBHelper" , e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TBName_User);

            onCreate(db);
        }
        catch (Exception e)
        {
            Log.e("DBHelper" , e.getMessage());
        }
    }
}



