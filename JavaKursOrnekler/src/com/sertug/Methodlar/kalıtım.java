package com.sertug.Methodlar;
class Hayvan {
	void sesÇikar() {
		System.out.println("Hayvan ses cıkarıyor.");
		
	}
}
class Kedi extends Hayvan {
	void miyavla() {
		System.out.println("Miyav!");
		
	}
}
public class kalıtım {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Kedi kedi = new Kedi();
kedi.sesÇikar();
kedi.miyavla();

	}

}
