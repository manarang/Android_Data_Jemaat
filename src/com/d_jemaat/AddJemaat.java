package com.d_jemaat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddJemaat extends Activity {
    private EditText Ag, Ala;
    private Spinner Bs, Dd, Gd, Kj, Tg, Bl, Th;
    private EditText Nm, Ph;
    private ImageView Bc;
    public String Sekarang = "NONE";
    private Button pReset, pSimpan;
    
    class C00001 implements OnClickListener {
        C00001() {
        }

        public void onClick(View v) {
            if (AddJemaat.this.Nm.getText().toString().trim().length() < 1 || AddJemaat.this.Ala.getText().toString().trim().length() < 1|| AddJemaat.this.Ph.getText().toString().trim().length()<1) {
                AddJemaat.this.peringatan();
                return;
            }
            AddJemaat.this.insertData(); 
            AddJemaat.this.startActivity(new Intent(AddJemaat.this, MainActivity.class));
        }
    }

    class C00002 implements OnClickListener {
        C00002() {
        }

        public void onClick(View v) {
            AddJemaat.this.startActivity(new Intent(AddJemaat.this, MainActivity.class));
        }
    }    
    
    class C00012 implements OnClickListener {
        C00012() {
        }

        public void onClick(View v) {
            AddJemaat.this.clearform();
        }
    } 

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Ag = (EditText) findViewById(R.id.i_no_anggota);
        Nm = (EditText) findViewById(R.id.i_nama);
        Ala = (EditText) findViewById(R.id.i_alamat);
        Bc = (ImageView) findViewById(R.id.i_back);
        Gd = (Spinner) findViewById(R.id.spinner1);
        Tg = (Spinner) findViewById(R.id.i_tanggal);
        Bl = (Spinner) findViewById(R.id.i_bulan);
        Th = (Spinner) findViewById(R.id.i_tahun);
        Ph = (EditText) findViewById(R.id.i_phone);
        Bs = (Spinner) findViewById(R.id.i_bs);
        Dd = (Spinner) findViewById(R.id.i_pendidikan);
        Kj = (Spinner) findViewById(R.id.i_pekerjaan);
        pSimpan = (Button) findViewById(R.id.b_insert);
        pReset = (Button) findViewById(R.id.b_reset);
        String[] gender=getResources().getStringArray(R.array.gender);		   
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Gd.setAdapter(adapter);
        String[] bs=getResources().getStringArray(R.array.bs);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bs.setAdapter(adapter3);
        String[] dd=getResources().getStringArray(R.array.dd);        
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dd);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dd.setAdapter(adapter4);
        String[] kj=getResources().getStringArray(R.array.kj);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kj);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Kj.setAdapter(adapter5);
        String[] item0=getResources().getStringArray(R.array.tg);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item0);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tg.setAdapter(adapter6);
        String[] item1=getResources().getStringArray(R.array.bl);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bl.setAdapter(adapter7);
        String[] item2=getResources().getStringArray(R.array.th);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Th.setAdapter(adapter8);        
        pSimpan.setOnClickListener(new C00001());
        pReset.setOnClickListener(new C00012());
        Bc.setOnClickListener(new C00002());
    }

    public void insertData() {
    	DBH handler = new DBH(this);
    	Jemaat jemaat = new Jemaat();
        jemaat.setNo_ang(Ag.getText().toString().trim());
        jemaat.setNama(Nm.getText().toString().trim());
        jemaat.setAlamat(Ala.getText().toString().trim());
        if(Gd.getSelectedItemPosition()!=0){
        	jemaat.setGender(Gd.getSelectedItem().toString());}else{
        	jemaat.setGender("-");
        }
        if(Tg.getSelectedItemPosition()!=0){
        	jemaat.setTanggal(Tg.getSelectedItem().toString());}else{
        		jemaat.setTanggal("tg");
        	}
        if(Bl.getSelectedItemPosition()!=0){
        	jemaat.setBulan(Bl.getSelectedItem().toString());}else{
        		jemaat.setBulan("bl");
        	}
        if(Th.getSelectedItemPosition()!=0){
        	jemaat.setTahun(Th.getSelectedItem().toString());}else{
        		jemaat.setTahun("tahun");
        	}
        jemaat.setPhone(Ph.getText().toString().trim());
        if(Bs.getSelectedItemPosition()!=0){
        	jemaat.setB_S(Bs.getSelectedItem().toString());}else{
        		jemaat.setB_S("-");
        	}
        if(Dd.getSelectedItemPosition()!=0){
        	jemaat.setPendidikan(Dd.getSelectedItem().toString());}else{
        		jemaat.setPendidikan("-");
        	}
        if(Kj.getSelectedItemPosition()!=0){
        	jemaat.setPekerjaan(Kj.getSelectedItem().toString());}else{
        		jemaat.setPekerjaan("-");
        	}
        handler.addJemaat(jemaat); 
    }

   public void clearform() {
        Ag.setText("");
        Nm.setText("");
        Ala.setText("");
        Gd.setSelection(0);
        Tg.setSelection(0);
        Bl.setSelection(0);
        Th.setSelection(0);
        Ph.setText("");
        Bs.setSelection(0);
        Dd.setSelection(0);
        Kj.setSelection(0);
    }
   
   private void peringatan(){
       AlertDialog.Builder prt = new AlertDialog.Builder(this);
       prt.setTitle("Ada yang kosong!");
       prt.setMessage("No/Nama/Alamat/Phone jangan kosong!");
       prt.setPositiveButton("Tutup", null);
       prt.create();
       prt.show();	   
   }
}
