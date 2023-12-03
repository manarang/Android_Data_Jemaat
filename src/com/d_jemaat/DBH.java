package com.d_jemaat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBH extends SQLiteOpenHelper implements Crudo {
    private static final String DATABASE_NAME = "/mnt/sdcard/citra.db";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ALAMAT = "Alamat";
    private static final String KEY_BS = "B_S";
    private static final String KEY_TANGGAL = "Tanggal";
    private static final String KEY_TTANGGAL = "Ttanggal";
    private static final String KEY_BULAN = "Bulan";
    private static final String KEY_TBULAN = "Tbulan";
    private static final String KEY_TAHUN = "Tahun";
    private static final String KEY_TTAHUN = "Ttahun";
    private static final String KEY_GENDER = "Gender";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "Nama";
    private static final String KEY_NOANG = "No_ang";
    private static final String KEY_PEKERJAAN = "Pekerjaan";
    private static final String KEY_PENDIDIKAN = "Pendidikan";
    private static final String KEY_PHONE = "Phone";
    private static final String TABLE_NAME = "jemaat";
    private static final String TABLE_HADIR = "tabel_hadir";
    private static final String KEY_AID = "aid";
    
    public DBH(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE jemaat(id INTEGER PRIMARY KEY,No_ang TEXT,Nama TEXT,Alamat TEXT,Gender TEXT,Tanggal TEXT,Bulan TEXT,Tahun TEXT,Phone TEXT,B_S TEXT,Pendidikan TEXT,Pekerjaan TEXT)");
        db.execSQL("CREATE TABLE tabel_hadir(id INTEGER PRIMARY KEY,Ttanggal TEXT,Tbulan TEXT,Ttahun TEXT,aid TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jemaat");
        db.execSQL("DROP TABLE IF EXISTS tabel_hadir");
        onCreate(db);
    }

    public void addJemaat(Jemaat jemaat) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOANG, jemaat.getNo_ang());
        values.put(KEY_NAMA, jemaat.getNama());
        values.put(KEY_ALAMAT, jemaat.getAlamat());
        values.put(KEY_GENDER, jemaat.getGender());
        values.put(KEY_TANGGAL, jemaat.getTanggal());
        values.put(KEY_BULAN, jemaat.getBulan());
        values.put(KEY_TAHUN, jemaat.getTahun());
        values.put(KEY_PHONE, jemaat.getPhone());
        values.put(KEY_BS, jemaat.getB_S());
        values.put(KEY_PENDIDIKAN, jemaat.getPendidikan());
        values.put(KEY_PEKERJAAN, jemaat.getPekerjaan());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addHadir(Present present) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TTANGGAL, present.getTtanggal());
        values.put(KEY_TBULAN, present.getTbulan());
        values.put(KEY_TTAHUN, present.getTtahun());
        values.put(KEY_AID, present.getAid());
        db.insert(TABLE_HADIR, null, values);
        db.close();
    }
    
    public Jemaat getJemaat(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String str = TABLE_NAME;
        String[] strArr = new String[]{KEY_ID, KEY_NOANG, KEY_NAMA, KEY_ALAMAT, KEY_GENDER, KEY_TANGGAL, KEY_BULAN, KEY_TAHUN, KEY_PHONE, KEY_BS, KEY_PENDIDIKAN, KEY_PEKERJAAN};
        String[] strArr2 = new String[DATABASE_VERSION];
        strArr2[0] = String.valueOf(id);
        Cursor cursor = db.query(str, strArr, "id=?", strArr2, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new Jemaat(Integer.parseInt(cursor.getString(0)), cursor.getString(DATABASE_VERSION), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));
    }

    public Cursor getNamJemaat(String nama) {
        return getWritableDatabase().rawQuery("SELECT  * FROM jemaat WHERE Nama like '%" + nama + "%' ORDER BY " + KEY_NAMA, null);
    }

    public Cursor getUlTah(int bultah) {
        return getWritableDatabase().rawQuery("SELECT  * FROM jemaat WHERE Bulan = " + bultah + " ORDER BY " + KEY_NAMA, null);
    }

    public Cursor getAll() {
        return getReadableDatabase().rawQuery("SELECT  * FROM jemaat ORDER BY Nama", null);
    }

    public Cursor getHadir() {
        return getReadableDatabase().rawQuery("SELECT  * FROM tabel_hadir", null);
    }

    public void updateJemaat(Jemaat jemaat, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues upval = new ContentValues();
        upval.put(KEY_NOANG, jemaat.getNo_ang());
        upval.put(KEY_NAMA, jemaat.getNama());
        upval.put(KEY_ALAMAT, jemaat.getAlamat());
        upval.put(KEY_GENDER, jemaat.getGender());
        upval.put(KEY_TANGGAL, Integer.valueOf(jemaat.getTanggal()));
        upval.put(KEY_BULAN, Integer.valueOf(jemaat.getBulan()));
        upval.put(KEY_TAHUN, Integer.valueOf(jemaat.getTahun()));
        upval.put(KEY_PHONE, jemaat.getPhone());
        upval.put(KEY_BS, jemaat.getB_S());
        upval.put(KEY_PENDIDIKAN, jemaat.getPendidikan());
        upval.put(KEY_PEKERJAAN, jemaat.getPekerjaan());
        db.update(TABLE_NAME, upval, "id = " + id, null);
        db.close();
    }
    
    public int getCountHadir(){
    	String countQuery = "SELECT  * FROM " + TABLE_HADIR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
    
}
