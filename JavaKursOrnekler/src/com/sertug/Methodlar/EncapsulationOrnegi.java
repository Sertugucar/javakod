package com.sertug.Methodlar;
class ogrenci {
	private int yas;
	public int Getyas() {
		return yas;		
	}
public void setYas(int yeniYas) {
	if (yeniYas >= 1 && yeniYas <= 120) {
		this.yas = yeniYas;
	} else {
		System.out.println("Yas 1-120 arasında olmalı.");
		
		
	}
}


}
public class EncapsulationOrnegi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Ogrenci ogrenci = new Ogrenci();
ogrenci.setYas(20);
ogrenci.setYas(-5);
System.out.println(ogrenci.getYas());

		
	}

}
