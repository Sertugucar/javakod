package kodlarlaornek;
class Urun {
String ad;

double fiyat;
Urun(String ad,double fiyat) {
this.ad = ad;
this.fiyat = fiyat;

	}
void bilgilerİYazdir() {
	System.out.println(ad + "-" + fiyat + "tl");
}}	
public class ornek3 {

	public static void main(String[] args) {
Urun urun1 = new Urun("klavye", 1200.0);
Urun urun2 = new Urun( "mouse",750.0);
urun1.bilgilerİYazdir();
urun2.bilgilerİYazdir();
}
}
