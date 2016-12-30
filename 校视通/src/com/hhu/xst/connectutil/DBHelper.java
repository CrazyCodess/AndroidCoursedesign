package com.hhu.xst.connectutil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper{
	
	private static final String DB_NAME="stu.db";
	private static final String TBL_NAME="stuTb1";
	private static final String
	CREATE_TBL="CREATE TABLE "+"stuTb1(_id INTEGER DEFAULT '1' NOT NULL " +
			"PRIMARY KEY AUTOINCREMENT ,name TEXT ,password TEXT)";
	private SQLiteDatabase db;
	//构造方法
	public DBHelper(Context context){
		super(context,DB_NAME,null,2);
		
		System.out.printf("创建数据库！");
	}
	//创建表
	@Override
	public void onCreate(SQLiteDatabase db){
		this.db=db;
		db.execSQL(CREATE_TBL);
		System.out.printf("创建表");
	}
	
	//插入方法
	public void insert(ContentValues values){
		SQLiteDatabase db=getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
		System.out.printf("数据库插入");
	}
	//查询方法
	public Cursor  query(){
		System.out.printf("数据库查询方法");
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.query(TBL_NAME,  null, null, null, null, null, null);
		return c;
	}

	public Cursor  query(String username){
		System.out.printf("数据库查询方法");
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.query(true,TBL_NAME,  new String[]{"_id,name,password"}, "name=?", new String[]{username}, null, null, null,null);
		return c;
	}
	
	//删除方法
	public void del(int id){
		System.out.printf("数据库删除方法");
		if(db==null){
			SQLiteDatabase db=getWritableDatabase();
			db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		}
	}
	//关闭方法
	public void  close(){
		System.out.printf("数据库关闭方法");
		if(db!=null){
			db.close();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db,
			int oldVersion,int newVersion){}
	
}