package com.d_jemaat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class Edit extends Activity {
    private EditText Ag, Al,Nm, No, Ph;
    private Spinner Tg, Bl, Th, Bs, Dd, Gd, Kj;
    private ImageView Bc;    
    
    private Button pUbah;
    int p_Id;
    String pindah; 

    class C00002 implements OnClickListener {
        C00002() {
        }

        public void onClick(View v) {
            Edit.this.startActivity(new Intent(Edit.this, MainActivity.class));
        }
    }        
    
   class C00021 implements OnClickListener {
        C00021() {
        }

        public void onClick(View v) {
            Edit.this.editData();
            Edit.this.startActivity(new Intent(Edit.this, MainActivity.class));
        }
    } 

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        pindah = getIntent().getExtras().getString("Pindah");
        No = (EditText) findViewById(R.id.t_no);
        Ag = (EditText) findViewById(R.id.t_noang);
        Nm = (EditText) findViewById(R.id.t_nama);
        Al = (EditText) findViewById(R.id.t_alamat);
        Gd = (Spinner) findViewById(R.id.t_gender);
        Tg = (Spinner) findViewById(R.id.i_tanggal);
        Bl = (Spinner) findViewById(R.id.i_bulan);
        Th = (Spinner) findViewById(R.id.i_tahun);
        Ph = (EditText) findViewById(R.id.t_phone);
        Bs = (Spinner) findViewById(R.id.t_bs);
        Dd = (Spinner) findViewById(R.id.t_pendidikan);
        Kj = (Spinner) findViewById(R.id.t_pekerjaan);
        Bc = (ImageView) findViewById(R.id.i_back);
        String[] gender=getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gender);
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
        String[] tg=getResources().getStringArray(R.array.tg);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tg);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tg.setAdapter(adapter6);
        String[] bl=getResources().getStringArray(R.array.bl);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bl);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bl.setAdapter(adapter7);
        String[] th=getResources().getStringArray(R.array.th);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, th);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Th.setAdapter(adapter8);
        
        pUbah = (Button) findViewById(R.id.b_ubah);
        ekstrak();
        pUbah.setOnClickListener(new C00021());
        Bc.setOnClickListener(new C00002());        
    }

    public void editData() {
    	DBH handler = new DBH(this);
    	Jemaat jemaat = new Jemaat();
        jemaat.setNo_ang(this.Ag.getText().toString());
        jemaat.setNama(this.Nm.getText().toString());
        jemaat.setAlamat(this.Al.getText().toString());
        jemaat.setGender(this.Gd.getSelectedItem().toString());
        jemaat.setTanggal(this.Tg.getSelectedItem().toString());
        jemaat.setBulan(this.Bl.getSelectedItem().toString());
        jemaat.setTahun(this.Th.getSelectedItem().toString());
        jemaat.setPhone(this.Ph.getText().toString());
        jemaat.setB_S(this.Bs.getSelectedItem().toString());
        jemaat.setPendidikan(this.Dd.getSelectedItem().toString());
        jemaat.setPekerjaan(this.Kj.getSelectedItem().toString());
        handler.updateJemaat(jemaat, this.p_Id);
    }

    public void ekstrak() {
        int i;
        int Ind = -1;
        do {
            Ind++;
        } while (pindah.charAt(Ind) != '/');
        p_Id = Integer.valueOf(pindah.substring(0, Ind)).intValue();
        Jemaat jemaat = new DBH(this).getJemaat(p_Id);
        No.setText(String.valueOf(jemaat.getId()));
        Ag.setText(jemaat.getNo_ang());
        Nm.setText(jemaat.getNama());
        Al.setText(jemaat.getAlamat());
        String[] gd=getResources().getStringArray(R.array.gender);
        for (i = 0; i < gd.length; i++) {
            if (gd[i].contains(jemaat.getGender())) {
                Gd.setSelection(i);
            }
        }
        String[] tg=getResources().getStringArray(R.array.tg);
        for (i = 0; i < tg.length; i++) {
            if (tg[i].contains(jemaat.getTanggal())) {
                Tg.setSelection(i);
                break;
            }
        }
        String[] bl=getResources().getStringArray(R.array.bl);
        for (i = 0; i < bl.length; i++) {
            if (bl[i].contains(jemaat.getBulan())) {
                Bl.setSelection(i);
                break;
            }
        }
        String[] th=getResources().getStringArray(R.array.th);
        for (i = 0; i < th.length; i++) {
            if (th[i].contains(jemaat.getTahun())) {
                Th.setSelection(i);
            }
        } 
        Ph.setText(jemaat.getPhone());
        String[] bs=getResources().getStringArray(R.array.bs);
        for (i = 0; i < bs.length; i++) {
            if (bs[i].contains(jemaat.getB_S())) {
                Bs.setSelection(i);
            }
        }
        String[] dd=getResources().getStringArray(R.array.dd);
        for (i = 0; i < dd.length; i++) {
            if (dd[i].contains(jemaat.getPendidikan())) {
                Dd.setSelection(i);
            }
        }
        String[] kj=getResources().getStringArray(R.array.kj);
        for (i = 0; i < kj.length; i++) {
            if (kj[i].contains(jemaat.getPekerjaan())) {
                Kj.setSelection(i);
            }
        } 
    } 
}
