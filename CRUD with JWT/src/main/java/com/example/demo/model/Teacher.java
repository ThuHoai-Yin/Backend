package com.example.demo.model;

import java.sql.Date;

/**
 * @author 84337
 *
 */
/**
 * @author 84337
 *
 */
public class Teacher {

	/** MaGV */
	private String MaGV;

	/** ho ten */
	private String hoTen;

	/** hoc vi */
	private String hocVi;

	/** hoc ham */
	private String hocHam;

	/** gioi tinh */
	private String gioiTinh;

	/** ngay sinh */
	private Date ngaySinh;

	/** ngay nhan luong */
	private Date ngVL;

	/** he so */
	private int heSo;

	/** muc luong */
	private double mucLuong;

	/** ma khoa */
	private String maKhoa;

	
	/** Constructor */
	public Teacher() {
		// TODO Auto-generated constructor stub
	}

	public Teacher(String maGV, String hoTen, String hocVi, String hocHam, String gioiTinh, Date ngaySinh, Date ngVL,
			int heSo, double mucLuong, String maKhoa) {
		MaGV = maGV;
		this.hoTen = hoTen;
		this.hocVi = hocVi;
		this.hocHam = hocHam;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.ngVL = ngVL;
		this.heSo = heSo;
		this.mucLuong = mucLuong;
		this.maKhoa = maKhoa;
	}

	public String getMaGV() {
		return MaGV;
	}

	public void setMaGV(String maGV) {
		MaGV = maGV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getHocVi() {
		return hocVi;
	}

	public void setHocVi(String hocVi) {
		this.hocVi = hocVi;
	}

	public String getHocHam() {
		return hocHam;
	}

	public void setHocHam(String hocHam) {
		this.hocHam = hocHam;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public Date getNgVL() {
		return ngVL;
	}

	public void setNgVL(Date ngVL) {
		this.ngVL = ngVL;
	}

	public int getHeSo() {
		return heSo;
	}

	public void setHeSo(int heSo) {
		this.heSo = heSo;
	}

	public double getMucLuong() {
		return mucLuong;
	}

	public void setMucLuong(double mucLuong) {
		this.mucLuong = mucLuong;
	}

	public String getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}

	
	/** 
	 * @return teacher model into String
	 */
	@Override
	public String toString() {
		return "Teacher [MaGV=" + MaGV + ", hoTen=" + hoTen + ", hocVi=" + hocVi + ", hocHam=" + hocHam + ", gioiTinh="
				+ gioiTinh + ", ngaySinh=" + ngaySinh + ", ngVL=" + ngVL + ", heSo=" + heSo + ", mucLuong=" + mucLuong
				+ ", maKhoa=" + maKhoa + "]";
	}

}
