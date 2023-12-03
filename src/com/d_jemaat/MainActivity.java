package com.d_jemaat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView LV;
    EditText carin;
    Cursor cursor;
    ImageView pCari, pTambah, uBday, present;	
	
    class GoEdit implements OnItemClickListener {
    	GoEdit() {
        }

        public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
            try {
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("Pindah", (String) adapter.getItemAtPosition(position));
                MainActivity.this.startActivity(intent);
            } catch (Exception e) {
            }
        }
    }


    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        this.carin = (EditText) findViewById(R.id.pcari);
        this.pCari = (ImageView) findViewById(R.id.cari);
        this.LV = (ListView) findViewById(R.id.list);
        this.pTambah = (ImageView) findViewById(R.id.tambah);
        this.uBday = (ImageView) findViewById(R.id.ulTah);
        this.present = (ImageView) findViewById(R.id.hadir);
        DBH handler = new DBH(this);
        ArrayList<String> datjem = new ArrayList<String>();
        Cursor cursor = handler.getAll();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                datjem.add(cursor.getString(0) + "/" + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        ArrayAdapter<String> arDa = new ArrayAdapter<String>(this, 17367043, datjem);        
        this.LV.setAdapter(arDa);        
        this.LV.setOnItemClickListener(new GoEdit());
   
        this.pCari.setOnClickListener(new View.OnClickListener()
        {
        	@Override
            public void onClick(View v) {
              MainActivity.this.cari(MainActivity.this.LV);
        	}});
        
        this.pTambah.setOnClickListener(new View.OnClickListener()
        {
    	    @Override
            public void onClick(View v) {
              MainActivity.this.startActivity(new Intent(MainActivity.this, AddJemaat.class));
    	}});
        
        this.uBday.setOnClickListener(new View.OnClickListener()
        {
    	    @Override
            public void onClick(View v) {
              MainActivity.this.startActivity(new Intent(MainActivity.this, UlTah.class));	
        }});
        
        this.present.setOnClickListener(new View.OnClickListener()
        {
    	    @Override
            public void onClick(View v) {
              MainActivity.this.startActivity(new Intent(MainActivity.this, Hadir.class));	
        }});
		
	}

    public void cari(ListView list) {
        String YgDicari = this.carin.getText().toString().trim();
        this.LV.setAdapter(null);
        ArrayList<String> datas = new ArrayList<String>();
        Cursor result = new DBH(this).getNamJemaat(YgDicari);
        result.moveToFirst();
        if (result.getCount() > 0) {
            do {
                datas.add(result.getString(0) + "/" + result.getString(2));
            } while (result.moveToNext());
        }
        result.close();
        ArrayAdapter<String> arAd = new ArrayAdapter<String>(this, 17367043, datas);
        list.setAdapter(arAd);
    }
	
}

