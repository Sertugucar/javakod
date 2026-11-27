#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>

// BMP280 register bicimini ornek baytlarla inceleyen masaustu uygulamasi.
// 0xF7: ust 8 bit, 0xF8: orta 8 bit, 0xF9: ust 4 bit.
// Sonuc fiziksel basinc degil, 20 bitlik ham veridir.
uint32_t combinePressureBytes(uint8_t msb, uint8_t lsb, uint8_t xlsb) {
    // 20 bit 16 bite sigmaz, bu nedenle uint32_t kullanilir.
    return ((uint32_t)msb << 12) |
           ((uint32_t)lsb << 4) |
           ((uint32_t)xlsb >> 4);
}
int main(void) {
    // Bunlar gercek sensor okumasi degil, ornek degerlerdir.
    uint32_t raw = combinePressureBytes(0x12, 0x34, 0x50);
    printf("Ham veri: 0x%05" PRIX32 "\n", raw);
    return 0;
}
