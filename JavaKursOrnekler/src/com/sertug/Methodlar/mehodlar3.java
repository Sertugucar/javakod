package com.sertug.Methodlar;

import java.util.Random;
import java.util.Scanner;

public class mehodlar3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


 
	
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int gizliSayi = random.nextInt();
		System.out.println("1-10 arası tahminiz: ");
		int tahmin = scanner.nextInt();
		if (tahmin == gizliSayi) {
			System.out.println("Tebrikler!");
		} else { 
			System.out.println("Sayı:" + gizliSayi);
			
		
		}
		scanner.close();
	}

		
		
		
		
		
		
		
		
		
		
		
		
	}


