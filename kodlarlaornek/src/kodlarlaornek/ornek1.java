package kodlarlaornek;

public class ornek1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] notlar = {
				 {70, 80, 90},
				 {60, 75, 85},
				 {90, 95, 100}
				 };
				 
				 for (int satir = 0; satir < notlar.length; satir++) {
				 int toplam = 0;
				 for (int sutun = 0; sutun < notlar[satir].length; sutun++) {
				 toplam += notlar[satir][sutun];
				 }
				 double ortalama = (double) toplam / notlar[satir].length;
				 System.out.println((satir + 1) + ". öğrenci ortalaması: " + ortalama);
	}

}}
