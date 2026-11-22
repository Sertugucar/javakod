#include <stdio.h>

static void degeri_kopyayla_degistir(int sayi) {
    sayi = 42; /* Sadece fonksiyonun yerel kopyasi degisir. */
    printf("Fonksiyon icindeki kopya: %d\n", sayi);
}

static void degeri_pointerla_degistir(int *sayi_adresi) {
    if (sayi_adresi != NULL) {
        *sayi_adresi = 42; /* Cagiran taraftaki asil degisken degisir. */
    }
}

int main(void) {
    int deger = 10;
    printf("Baslangic degeri: %d\n", deger);
    degeri_kopyayla_degistir(deger);
    printf("Kopyayla cagridan sonra: %d\n", deger);
    degeri_pointerla_degistir(&deger);
    printf("Pointer ile cagridan sonra: %d\n", deger);
    printf("&deger, degiskenin adresini verir; *pointer adresindeki veriye erisir.\n");
    return 0;
}
