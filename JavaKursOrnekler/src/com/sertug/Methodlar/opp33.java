package com.sertug.Methodlar;
class Ogrenci {
	String isim;
	int yas; 
	
	Ogrenci(String isim, int yas ) {
		this.isim = isim;
		this.yas = yas;
		
		
	}
	
	void bilgileriYazdir() {
		System.out.println(isim + " - " + yas);
		
	}

	public void setYas(int i) {
		// TODO Auto-generated method stub
		
	}

	public char[] getYas() {
		// TODO Auto-generated method stub
		return null;
	}
}
public class opp33 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Ogrenci ogrenci1 = new Ogrenci("Leclerc", 29);
	ogrenci1.bilgileriYazdir();
	}

}
