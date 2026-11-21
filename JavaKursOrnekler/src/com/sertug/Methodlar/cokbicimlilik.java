package com.sertug.Methodlar;
class Hayvan{
	void sesCikar() {
		System.out.println("Bir hayvan sesi");
		
	}
}
class Kedi extends Hayvan {
	@ Override
	void sesCikar() {System.out.println("Miyav");}
	
}
class Kopek extends Hayvan {
	@Override
	void sesCikar() {System.out.println("Hav Hav");}
}

public class cokbicimlilik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Hayvan hayvan1 = new Kedi();
Hayvan hayvan2 = new Kopek();

hayvan1.sesCikar();
hayvan2.sesCikar();
	}

}
