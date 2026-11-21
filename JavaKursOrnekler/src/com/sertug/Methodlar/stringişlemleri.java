package com.sertug.Methodlar;

import java.util.Scanner;

public class stringişlemleri {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner scanner = new Scanner(System.in);
System.out.println("Kullanıcı adınız: ");
String isim = scanner.nextLine();

System.out.println("Uzunluk: " + isim.length());
System.out.println("Büyük harf: " + isim.toUpperCase());
System.out.println("a iceriyor mu? " + isim.contains("a"));


if (isim.equalsIgnoreCase("admin")) {
	System.out.println("YÖnetici girişi");
	
}
scanner.close();


	}

}
