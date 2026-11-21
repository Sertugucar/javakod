package com.sertug.Methodlar;
import java.util.Random;
public class soyutlamq {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


Random random = new Random();
int sayı = random.nextInt(10) + 1;
double kare = Math.pow(sayı, 2);
double karekök = Math.sqrt(sayı);
System.out.println("Rastgele sayı: " + sayı);
System.out.println("Karesi: " + kare);

System.out.println("Karekökü: " + karekök);


	}

}
