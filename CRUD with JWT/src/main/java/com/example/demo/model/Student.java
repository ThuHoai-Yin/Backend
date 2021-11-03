package com.example.demo.model;

import java.time.LocalDate;

public class Student {
	private String MAHV;
	private String HO;
	private String TEN;
	private LocalDate NGSINH;
	private String GIOITINH;
	private String noiSinh;
	private String maLop;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String mAHV, String hO, String tEN, LocalDate nGSINH, String gIOITINH, String noiSinh,
			String maLop) {
		super();
		MAHV = mAHV;
		HO = hO;
		TEN = tEN;
		NGSINH = nGSINH;
		GIOITINH = gIOITINH;
		this.noiSinh = noiSinh;
		this.maLop = maLop;
	}

	public String getMAHV() {
		return MAHV;
	}

	public void setMAHV(String mAHV) {
		MAHV = mAHV;
	}

	public String getHO() {
		return HO;
	}

	public void setHO(String hO) {
		HO = hO;
	}

	public String getTEN() {
		return TEN;
	}

	public void setTEN(String tEN) {
		TEN = tEN;
	}

	public LocalDate getNGSINH() {
		return NGSINH;
	}

	public void setNGSINH(LocalDate nGSINH) {
		NGSINH = nGSINH;
	}

	public String getGIOITINH() {
		return GIOITINH;
	}

	public void setGIOITINH(String gIOITINH) {
		GIOITINH = gIOITINH;
	}

	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	@Override
	public String toString() {
		return "Student [MAHV=" + MAHV + ", HO=" + HO + ", TEN=" + TEN + ", NGSINH=" + NGSINH + ", GIOITINH=" + GIOITINH
				+ ", noiSinh=" + noiSinh + ", maLop=" + maLop + "]";
	}

	
	

	

}
