#include <stdio.h>
#include <stdint.h>

// volatile, nesneye yapilan erisimlerin korunmasini saglar.
// Tek basina atomiklik veya eszamanlilik guvenligi saglamaz.
static volatile uint8_t buttonPressed = 0;

// Bu fonksiyon sadece olay mantigini temsil eder; gercek kesme degildir.
static void simulatedEvent(void) {
    buttonPressed = 1;
}
int main(void) {
    printf("Once: %u\n", (unsigned)buttonPressed);
    simulatedEvent(); // Masaustu testinde olayi elle olusturuyoruz.
    printf("Sonra: %u\n", (unsigned)buttonPressed);
    return 0;
}
