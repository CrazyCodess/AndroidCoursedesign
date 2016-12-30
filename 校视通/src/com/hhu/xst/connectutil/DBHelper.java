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
	//���췽��
	public DBHelper(Context context){
		super(context,DB_NAME,null,2);
		
		System.out.printf("�������ݿ⣡");
	}
	//������
	@Override
	public void onCreate(SQLiteDatabase db){
		this.db=db;
		db.execSQL(CREATE_TBL);
		System.out.printf("������");
	}
	
	//���뷽��
	public void insert(ContentValues values){
		SQLiteDatabase db=getWritableDatabase();
		db.insert(TBL_NAME, null, values);
		db.close();
		System.out.printf("���ݿ����");
	}
	//��ѯ����
	public Cursor  query(){
		System.out.printf("���ݿ��ѯ����");
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.query(TBL_NAME,  null, null, null, null, null, null);
		return c;
	}

	public Cursor  query(String username){
		System.out.printf("���ݿ��ѯ����");
		SQLiteDatabase db=getWritableDatabase();
		Cursor c=db.query(true,TBL_NAME,  new String[]{"_id,name,password"}, "name=?", new String[]{username}, null, null, null,null);
		return c;
	}
	
	//ɾ������
	public void del(int id){
		System.out.printf("���ݿ�ɾ������");
		if(db==null){
			SQLiteDatabase db=getWritableDatabase();
			db.delete(TBL_NAME, "_id=?", new String[]{String.valueOf(id)});
		}
	}
	//�رշ���
	public void  close(){
		System.out.printf("���ݿ�رշ���");
		if(db!=null){
			db.close();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db,
			int oldVersion,int newVersion){}
	
}