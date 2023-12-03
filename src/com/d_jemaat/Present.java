package com.d_jemaat;

public class Present {

		private int id;
		private String ttanggal;
		private String tbulan;
		private String ttahun;
		private String aid;

	public Present(){
		
	}
	
	public Present(int id, String ttanggal, String tbulan, String ttahun, String aid){
		
		this.id = id;
		this.ttanggal = ttanggal;
		this.tbulan = tbulan;
		this.ttahun = ttahun;
		this.aid = aid;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getTtanggal(){
		return this.ttanggal;
	}
	
	public void setTtanggal(String ttanggal){
		this.ttanggal = ttanggal;
	}

	public String getTbulan(){
		return this.tbulan;
	}
	
	public void setTbulan(String tbulan){
		this.tbulan = tbulan;
	}

	public String getTtahun(){
		return this.ttahun;
	}
	
	public void setTtahun(String ttahun){
		this.ttahun = ttahun;
	}

	public String getAid(){
		return this.aid;
	}
	
	public void setAid(String aid){
		this.aid = aid;
	}

}