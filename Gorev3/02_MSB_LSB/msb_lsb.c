#include <stdio.h>
#include <stdint.h>
#include <inttypes.h> // Sabit genislikli tipler icin printf bicimleri.

// Iki ayri bayti tek bir 16 bitlik sayida birlestir.
uint16_t combineBytes(uint8_t msb, uint8_t lsb) {
    // MSB sekiz bit sola gider; OR alt sekiz bite LSB'yi yerlestirir.
    return (uint16_t)(((uint16_t)msb << 8) | lsb);
}
// Ikiye tumleyen bicimindeki veriyi isaretli sayi olarak yorumla.
int16_t interpretSigned(uint16_t value) {
    if (value <= INT16_MAX) return (int16_t)value;
    // En ust bit 1 ise 65536 cikararak negatif degeri bul.
    return (int16_t)((int32_t)value - 65536);
}
int main(void) {
    uint16_t first = combineBytes(0x12, 0x34);
    uint16_t second = combineBytes(0xFF, 0x38);
    printf("0x1234: %" PRIu16 "\n", first); // 4660
    printf("0xFF38 unsigned: %" PRIu16 "\n", second); // 65336
    printf("0xFF38 signed: %" PRId16 "\n", interpretSigned(second)); // -200
    return 0;
}
