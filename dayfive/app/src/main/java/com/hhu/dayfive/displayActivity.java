package com.hhu.dayfive;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class displayActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_display);
        final DBHelper helper = new DBHelper(this);
        Cursor c=helper.query();
        String[] from ={"_id","name","hobby"};
        int[] to={R.id.id,R.id.name,R.id.like};
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.display,c,from,to);
        ListView listView=getListView();
        listView.setAdapter(adapter);
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final long temp=id;
                builder.setMessage("是否删除记录？").setPositiveButton("是",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                helper.del((int)temp);
                                Cursor c=helper.query();
                                String[] from ={"_id","name","hobby"};
                                int[] to={R.id.id,R.id.name,R.id.like};
                                SimpleCursorAdapter adapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.display,c,from,to);
                                ListView listView=getListView();
                                listView.setAdapter(adapter);
                            }
                        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                AlertDialog ad=builder.create();
                ad.show();
            }
        });
        helper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
