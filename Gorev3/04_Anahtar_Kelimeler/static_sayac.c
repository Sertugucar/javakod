#include <stdio.h>

// static fonksiyonun ismi bu derleme birimine ozeldir.
static void normalCounter(void) {
    int count = 0; // Her cagri sirasinda yeniden sifirlanir.
    printf("Normal: %d\n", ++count);
}
static void staticCounter(void) {
    static int count = 0; // Onceki cagridaki degeri korur.
    printf("Static: %d\n", ++count);
}
int main(void) {
    for (int i = 0; i < 5; ++i) {
        normalCounter();
        staticCounter();
    }
    return 0;
}
