package kodlarlaornek;
class ogrenci {
	private int yas;
	public int getYas() {
		return yas;
	}
public void setYas(int yeniYas) {
	if(yeniYas >= 1 && yeniYas <= 120 ) {
		this.yas = yeniY
	} else {
		System.out.println("yas 1-120 arasında olmalı.");
	}
}
} 

public class kodlarlaornek4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Ogrenci ogrenci = new Ogrenci();
ogrenci.setYas(20);
ogrenci.setYas(-5);
System.out.println("Son yaş: " + ogrenci.getYas());
	}

}
