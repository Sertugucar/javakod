package com.sertug.Methodlar;
import java.util.Scanner;

public class soru2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner scanner = new Scanner(System.in);
System.out.print("Kullanıcı adı: ");
String kullanıcıAdi = scanner.nextLine();
System.out.println("Uzunluk: " + kullanıcıAdi.length());
System.out.println("Büyük harf: " + kullanıcıAdi.toUpperCase());
boolean javaVarmi = kullanıcıAdi.toLowerCase().contains("java");
System.out.println("java içeriyor mu? " + javaVarmi);
if (kullanıcıAdi.equalsIgnoreCase("admin")) {
	System.out.println("Yönetiçi girişi");
}
scanner.close();

	}

}
