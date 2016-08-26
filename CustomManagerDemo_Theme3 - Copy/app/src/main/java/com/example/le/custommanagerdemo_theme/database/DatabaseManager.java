package com.example.le.custommanagerdemo_theme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hinh1 on 7/23/2016.
 */
public class DatabaseManager {
    //dinh nghia thong tin database
    private static final String DB_NAME="manager";
    private static final String TB_NAME="account";
    private static final int DB_VERSION=1;
    private SQLiteDatabase database;
    //B4: xay dung class SQLiteOpenHelper de giup cho viec
    //upgrade, downgrade DB
    //B6: Xadung databese
    public DatabaseManager(){

    }
    public DatabaseManager(Context context){
        OpenHelper helper=new OpenHelper(context);
        database=helper.getWritableDatabase();
    }
    public class OpenHelper extends SQLiteOpenHelper{

        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String creadTable="CREATE TABLE IF NOT EXISTS account(_id INTEGER)";
            sqLiteDatabase.execSQL(creadTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //thuc hien viec upgrade
            sqLiteDatabase.execSQL("DROP TABLE IH EXISTS account");
            onCreate(sqLiteDatabase);
        }
    }
    //B5 xay dung phung thuc lam viec voi db
    public void insert(int id){
        ContentValues contentValues=new ContentValues();
        contentValues.put("_id",id);
        database.insertOrThrow(TB_NAME,null,contentValues);

    }
    public void update(int id){
        ContentValues values=new ContentValues();
        values.put("_id",id);
        database.update(TB_NAME,values,null,null);
    }
    public void delete(){
        database.delete(TB_NAME,null,null);
    }
    public Cursor getList(){
        return database.query(TB_NAME,null,null,null,null,null,null);
    }
    public String getID(){
        Cursor cursor=database.query(TB_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        return String.valueOf(cursor.getInt(0));
    }
}
