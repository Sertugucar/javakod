package com.sertug.Methodlar;

public class foreach {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] notlar = {70,80,90,100};
		int toplam = 0;
		for(int not : notlar) {
		toplam +=not;
		System.out.println(not);
		
	}
		System.out.println("Toplam:" + toplam);
}
}