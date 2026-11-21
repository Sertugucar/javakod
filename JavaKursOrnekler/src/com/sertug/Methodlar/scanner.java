package com.sertug.Methodlar;

import java.util.Scanner;

public class scanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Scanner scanner = new Scanner(System.in);
    System.out.println("Adınızı girin");
    String isim = scanner.nextLine();
    
    System.out.println("Yaşınızı girin");
    System.out.println("Not ortalamnızı girin");
    double ortalama = scanner.nextDouble();
    
    System.out.println(isim + "," +" yas+" );
    System.out.println("ortalama:" + ortalama);
    scanner.close();
  
    
   
		
		
		
		
	}

	public static int nextInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	
		
	}


