package com.d_jemaat;

public class Jemaat {
    private String Alamat;
    private String B_S;
    private String Bulan;
    private String Gender;
    private String Nama;
    private String No_ang;
    private String Pekerjaan;
    private String Pendidikan;
    private String Phone;
    private String Tahun;
    private String Tanggal;
    private int id;

    public Jemaat(){
    	
    }
    
    public Jemaat(int id, String No_ang, String Nama, String Alamat, String Gender, String Tanggal, String Bulan, String Tahun, String Phone, String B_S, String Pendidikan, String Pekerjaan) {
        this.id = id;
        this.No_ang = No_ang;
        this.Nama = Nama;
        this.Alamat = Alamat;
        this.Gender = Gender;
        this.Tanggal = Tanggal;
        this.Bulan = Bulan;
        this.Tahun = Tahun;
        this.Phone = Phone;
        this.B_S = B_S;
        this.Pendidikan = Pendidikan;
        this.Pekerjaan = Pekerjaan;
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo_ang() {
        return this.No_ang;
    }

    public void setNo_ang(String No_ang) {
        this.No_ang = No_ang;
    }

    public String getNama() {
        return this.Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getAlamat() {
        return this.Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getGender() {
        return this.Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getTanggal() {
        return this.Tanggal;
    }

    public void setTanggal(String Tanggal) {
        this.Tanggal = Tanggal;
    }

    public String getBulan() {
        return this.Bulan;
    }

    public void setBulan(String Bulan) {
        this.Bulan = Bulan;
    }

    public String getTahun() {
        return this.Tahun;
    }

    public void setTahun(String Tahun) {
        this.Tahun = Tahun;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getB_S() {
        return this.B_S;
    }

    public void setB_S(String B_S) {
        this.B_S = B_S;
    }

    public String getPendidikan() {
        return this.Pendidikan;
    }

    public void setPendidikan(String Pendidikan) {
        this.Pendidikan = Pendidikan;
    }

    public String getPekerjaan() {
        return this.Pekerjaan;
    }

    public void setPekerjaan(String Pekerjaan) {
        this.Pekerjaan = Pekerjaan;
    }
}
