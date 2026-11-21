package com.sertug.Methodlar;


class Ogrenci {
	String isim;
	int yas;
	void bilgileriYazdir () {
		System.out.println(isim + "-" + yas);
	}
}


public class oop3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Ogrenci ogrenci2 = new Ogrenci();
ogrenci2.isim = "Max";
ogrenci2.yas = 29;
ogrenci2.bilgileriYazdir();

	}

}
