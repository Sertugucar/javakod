#include <stdio.h>

int main(void) {
    const int number = 100; // Bu nesneye yeni deger atanamaz.
    printf("%d\n", number);
    number = 200; // BILINCLI HATA: derleyici bu atamayi reddeder.
    return 0;
}
