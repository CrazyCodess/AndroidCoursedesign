package com.hhu.xst.function;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhu.xst.main.MainActivity;
import com.jereh.slidingdemo.R;
import com.hhu.xst.connectutil.*;
/**
 * 实现注册功能，逻辑尚未实现
 * @author lenovo
 *
 */
public class RegisterActivity extends Activity {

	
	private Button b;
	private EditText et1,et2,et3;
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		et1=(EditText)findViewById(R.id.rguserNameText);
		et2=(EditText)findViewById(R.id.rgpasswdText1);
		et3=(EditText)findViewById(R.id.rgpasswdText);
		b=(Button)findViewById(R.id.bnRegister);
		b.setOnClickListener(new DatabaseListener());
		//验证2次输入密码一致密码
		
		
	}
	
	class DatabaseListener implements View.OnClickListener {
        public void onClick(View v) {
        	
        	if(et2.getText().toString().trim().equals(et3.getText().toString().trim())){
    			Log.e("data","*"+et2.getText().toString().trim()+"*"+et3.getText().toString().trim()+"*");
    			Log.e("database", "一致");
    			Toast.makeText(getApplicationContext(), "注册成功！",
   				     Toast.LENGTH_SHORT).show();
    			String name = et1.getText().toString();
                String password = et2.getText().toString();
                ContentValues values = new ContentValues();
                
                values.put("name", name);
                values.put("password", password);
                DBHelper helper = new DBHelper(getApplicationContext());
                helper.insert(values);
                Bundle bundle=new Bundle();
                bundle.putInt("status",1);
                bundle.putString("name",name);
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
    		}
    		else{
    			Log.e("database", "不一致");
    			Toast.makeText(getApplicationContext(), "两次输入密码不一致！！",
    				     Toast.LENGTH_SHORT).show();
    		}
            
        }
    };
    
	
/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent myIntent = new Intent();
            myIntent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(myIntent);
            this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}*/

}
