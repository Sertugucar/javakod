package com.sertug.Methodlar;

public class soru12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int[] notlar = {70,80,90,100};
int toplam = 0;
for  (int i = 0; i < notlar.length; i++) {
toplam += notlar[i];
System.out.println(i + " indeks: " + notlar[i]);

	
}
	System.out.println("Toplam: " + toplam);
	}

}
