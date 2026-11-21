package com.sertug.Methodlar;

		class ogrenci {
		    private int yas;

		    public int getYas() {
		        return yas;
		    }

		    public void setYas(int yeniYas) {
		        if (yeniYas >= 1 && yeniYas <= 120) {
		            this.yas = yeniYas;
		        } else {
		            System.out.println("yas 1-120 arasında olmalı.");
		        }
		    }
		}

		public class t23 {
		    public static void main(String[] args) {
		        Ogrenci ogrenci = new Ogrenci();
		        ogrenci.setYas(20);
		        ogrenci.setYas(-5);   // uyarı mesajı yazdırır, yaş değişmez
		        System.out.println(ogrenci.getYas());   // 20
		    }
		
	}


