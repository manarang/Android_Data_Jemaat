package com.d_jemaat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;

import com.d_jemaat.Edit.C00002;

public class Hadir extends Activity {

    ImageView pLihat, Bc;
    ListView LV;
    private Spinner Tg, Bl, Th;
    public String pr, tgl, bln, thn, pid;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {

	    class C00002 implements OnClickListener {
	        C00002() {
	        }

	        public void onClick(View v) {
	            Hadir.this.startActivity(new Intent(Hadir.this, MainActivity.class));
	        }
	    }     		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hadir);
        LV = (ListView) findViewById(R.id.list);
        Tg = (Spinner) findViewById(R.id.tanggal);        
        Bl = (Spinner) findViewById(R.id.bulan);
        Th = (Spinner) findViewById(R.id.tahun);
        Bc = (ImageView) findViewById(R.id.i_back);
        pLihat = (ImageView) findViewById(R.id.lihat);
        String[] tg=getResources().getStringArray(R.array.tg);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tg.setAdapter(adapter);
        String[] bl=getResources().getStringArray(R.array.bl);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bl);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bl.setAdapter(adapter1);
        String[] th=getResources().getStringArray(R.array.thh);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, th);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Th.setAdapter(adapter2);
        Bc.setOnClickListener(new C00002());  
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
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    
            	pr = (String) parent.getItemAtPosition(position);
            	ShowDialog();
         }
        });
        
        this.pLihat.setOnClickListener(new View.OnClickListener()
        {
    	    @Override
            public void onClick(View v) {
    	      tgl = Tg.getSelectedItem().toString();
    	      bln = Bl.getSelectedItem().toString();
    	      thn = Th.getSelectedItem().toString();
    	      Lihat();
        }});
        
	}
	
    private void ShowDialog(){
        AlertDialog.Builder present = new AlertDialog.Builder(this);
        int Ind = -1;
        do {
            Ind++;
        } while (this.pr.charAt(Ind) != '/');
        pid = pr.substring(0, Ind);
        present.setTitle(pr.substring(Ind + 1));
        present.setMessage("Present?");
        present.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        	
        	@Override
        	public void onClick(DialogInterface dialog, int which) {
        	Calendar cal = Calendar.getInstance();
        	tgl = Integer.toString(cal.get(Calendar.DAY_OF_MONTH)); 
        	bln = Integer.toString(cal.get(Calendar.MONTH) + 1);
            thn = Integer.toString(cal.get(Calendar.YEAR));
            insertData();
            Sukses();
            }
        });
        present.setNegativeButton("No", null);
        present.create();
        present.show();
    }        

    private void insertData(){
    	DBH hd = new DBH(this);
    	Present present = new Present();
    	present.setTtanggal(tgl);
    	present.setTbulan(bln);
    	present.setTtahun(thn);
    	present.setAid(pid);
    	hd.addHadir(present);
    }

    private void Lihat(){
    	this.LV.setAdapter(null);
    	DBH hd = new DBH(this);
    	int cnt = hd.getCountHadir();
    	setTitle("Presence : " + Integer.toString(cnt));
    	ArrayList<String> datjem = new ArrayList<String>();
        Cursor cursor = hd.getAll();
    	Cursor crr = hd.getHadir();
    	int jid;
    	crr.moveToFirst();
    	if (crr.getCount() > 0) {
    		do {
    			  if(Integer.parseInt(tgl) == Integer.parseInt(crr.getString(1))&&Integer.parseInt(bln) == Integer.parseInt(crr.getString(2))&&Integer.parseInt(thn) == Integer.parseInt(crr.getString(3))){
    				  jid = Integer.parseInt(crr.getString(4));
    				  cursor.moveToFirst();
    				  if (cursor.getCount() > 0) {
    					  do {
    						   if(Integer.parseInt(cursor.getString(0))==jid){
    							   datjem.add(cursor.getString(2));
    							   break;
    						   }
    					  } while (cursor.moveToNext());
    				  }
    			  }
    		} while (crr.moveToNext());
    	}
        crr.close();

        ArrayAdapter<String> arDa = new ArrayAdapter<String>(this, 17367043, datjem);        
        this.LV.setAdapter(arDa); 
    }
    
    private void Sukses(){
        AlertDialog.Builder sukses = new AlertDialog.Builder(this);
        sukses.setTitle("Success");
        sukses.setMessage("Has saved!");
        sukses.setPositiveButton("Close", null);
        sukses.show();
    }
    
}
