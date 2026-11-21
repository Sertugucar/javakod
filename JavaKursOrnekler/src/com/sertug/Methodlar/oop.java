package com.sertug.Methodlar;
class Ogrenci {
	public static Ogrenci ogrenci;
	String isim;
	int yas;
	void bilgileriYazdir() {
		System.out.println(isim + " - " + yas);
		
	}
	public static void setYas(int i) {
		// TODO Auto-generated method stub
		
	}
	
		
	}


public class oop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Ogrenci ogrenci1 = new Ogrenci();
	 ogrenci1.isim = "Ali";
		ogrenci1.yas = 20;
		 ogrenci1.bilgileriYazdir();
	}

}
