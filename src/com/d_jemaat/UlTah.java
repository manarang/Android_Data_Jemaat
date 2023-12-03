package com.d_jemaat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;

import com.d_jemaat.Edit.C00002;

public class UlTah extends Activity {
    ListView LV;
    String bultah;
    Cursor cursor;
    private ImageView Bc;   
    
    class C00002 implements OnClickListener {
        C00002() {
        }

        public void onClick(View v) {
            UlTah.this.startActivity(new Intent(UlTah.this, MainActivity.class));
        }
    }      
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ultah);
        int bultah = Calendar.getInstance().get(2) + 1;
        ArrayList<String> datjem = new ArrayList<String>();
        LV = (ListView) findViewById(R.id.list);
        Bc = (ImageView) findViewById(R.id.i_back);
        Bc.setOnClickListener(new C00002());
        Cursor result = new DBH(this).getUlTah(bultah);
        result.moveToFirst();
        if (result.getCount() > 0) {
            do {
                datjem.add(result.getString(2) + " / " + result.getString(5) + "-" + result.getString(6) + "-" + result.getString(7));
            } while (result.moveToNext());
        }
        result.close();
        ArrayAdapter<String> arAd = new ArrayAdapter<String>(this, 17367043, datjem);        
        LV.setAdapter(arAd);
    }
}
