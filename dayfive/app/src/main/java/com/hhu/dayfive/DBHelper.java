package com.hhu.dayfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CrazyCodess on 2016/12/27.
 */
public class DBHelper extends SQLiteOpenHelper {
    //数据库名，表名
    private static final String DB_NAME="stu.db";
    private static final String TBL_NAME="stutbl";
    //创建表语句
    private static  final String CREATE_TBL="CREATE TABLE "+"stuTbl(_id INTEGER DEFAULT '1' NOT"
    +  " NULL PRIMARY KEY AUTOINCREMENT,name TEXT,hobby TEXT)";
    //SQLiteDatabase实例
    private SQLiteDatabase db;

    //构造方法
    public DBHelper(Context context) {
        super(context,DB_NAME,null,2);
        System.out.printf("创建数据库");
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        db.execSQL(CREATE_TBL);
        System.out.printf("创建表");
    }
    //插入方法
    public void insert(ContentValues values){
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TBL_NAME,null,values);
        db.close();
        System.out.printf("数据库插入操作");
    }

    //查询方法
    public Cursor query(){
        SQLiteDatabase db=getWritableDatabase();
        return db.query(TBL_NAME,null,null,null,null,null,null);
    }
    //删除方法
    public void del(int id){
        if(db==null){
            SQLiteDatabase db=getWritableDatabase();
            db.delete(TBL_NAME,"_id=?",new String[]{String.valueOf(id)});

        }
    }
    //关闭数据库
    public void close(){
        if(db!=null){
            db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
