package com.hhu.xst.function;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hhu.xst.connectutil.DBHelper;
import com.hhu.xst.connectutil.LandServer;
import com.hhu.xst.main.MainActivity;
import com.jereh.slidingdemo.R;

/**
 * 注册界面，主要逻辑尚未实现
 * 
 * @author lenovo
 * 
 */
public class LoginActivity extends Activity {

	private Button btlogin;
	private EditText userNameText;
	private EditText passwdText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// tsj:该activity应该是登录activity，暂时作为连接后端服务器的测试部分
		//start
		System.out.println("come on !!!!!!");
		btlogin = (Button) findViewById(R.id.bnLogin);
		userNameText = (EditText) findViewById(R.id.userNameText);
		passwdText = (EditText) findViewById(R.id.passwdText);
		btlogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("点击事件！！！");
				String username = userNameText.getText().toString();
				String password = passwdText.getText().toString();
				if (username.isEmpty()) {
					Toast.makeText(getApplicationContext(), "用户名不能为空！", 8000);
				} else if (password.isEmpty()) {
					Toast.makeText(getApplicationContext(), "密码不能为空！", 8000);
				} else {
					
						DBHelper helper=new DBHelper(getApplicationContext());
						Cursor c=helper.query();
						//必须先把Cursor定位到第一行
						c.moveToFirst();
						int f=0;
						while(c.moveToNext()) 
						{ 
							Log.e("table", c.getString(1));
							if(c.getString(2).trim().equals(password.trim())){
								
								Toast.makeText(getApplicationContext(), "登陆成功！",
				    				     Toast.LENGTH_SHORT).show();
								
								 Bundle bundle=new Bundle();
					                bundle.putInt("status",1);
					                bundle.putString("name",username);
					                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					                intent.putExtras(bundle);
					                startActivity(intent);
					                f=1;
					                break;
					                
					                
					                
							}
							
						}
						if(f==0)
						Toast.makeText(getApplicationContext(), "登陆失败！",
		    				     Toast.LENGTH_SHORT).show();
						
						
				/*		Log.e("column", c.getString(c.getColumnIndexOrThrow("name")));
						if(password.equals(c.getString(c.getColumnIndexOrThrow("password")))){
							Toast.makeText(getApplicationContext(), "登陆成功！", Toast.LENGTH_LONG);
							}
						else{
								Toast.makeText(getApplicationContext(), "登陆失败！", Toast.LENGTH_LONG);
							}*/
						
					}}});}}
					/*new Thread(new Runnable() {
						@Override
						public void run() {
							LandServer server = new LandServer();
							String result = server.doPost(userNameText
									.getText().toString(), passwdText.getText()
									.toString());
							Message msg = new Message();
							msg.obj = result;
							handler.sendMessage(msg);

						}
					}).start();
				}
			}
		});*/


	//end
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			Intent myIntent = new Intent();
//			myIntent = new Intent(LoginActivity.this, MainActivity.class);
//			startActivity(myIntent);
//			this.finish();
//		}
//		return super.onKeyDown(keyCode, event);
//	}

