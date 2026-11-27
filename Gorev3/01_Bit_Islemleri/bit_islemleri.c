#include <stdio.h> // printf ile ekrana yazi yazdiririz.
#include <stdint.h> // uint8_t tam 8 bitlik isaretsiz sayidir.

// Pointer kullanarak asil degiskeni degistiriyoruz.
void setBit(uint8_t *reg, uint8_t bit) {
    // Sadece 0-7 arasindaki bitler gecerlidir.
    if (bit < 8) *reg |= (uint8_t)(1u << bit); // OR hedef biti 1 yapar.
}
void clearBit(uint8_t *reg, uint8_t bit) {
    if (bit < 8) *reg &= (uint8_t)~(1u << bit); // Ters maske ve AND hedef biti siler.
}
void toggleBit(uint8_t *reg, uint8_t bit) {
    if (bit < 8) *reg ^= (uint8_t)(1u << bit); // XOR hedef biti tersine cevirir.
}
uint8_t readBit(uint8_t reg, uint8_t bit) {
    if (bit >= 8) return 0; // Gecersiz indeks.
    return (uint8_t)((reg >> bit) & 1u); // Hedef biti en saga kaydir ve ayir.
}
void printBinary(uint8_t reg) {
    // En anlamli bitten en dusuk anlamli bite ilerle.
    for (int i = 7; i >= 0; --i)
        printf("%u", (unsigned)((reg >> i) & 1u));
    puts("");
}
int main(void) {
    uint8_t reg = 0; // Sekiz bitin tamami baslangicta sifir.
    printf("Baslangic: "); printBinary(reg);
    setBit(&reg, 3); printf("Set 3: "); printBinary(reg);
    setBit(&reg, 5); printf("Set 5: "); printBinary(reg);
    clearBit(&reg, 3); printf("Clear 3: "); printBinary(reg);
    toggleBit(&reg, 0); printf("Toggle 0: "); printBinary(reg);
    printf("Read 5: %u\n", (unsigned)readBit(reg, 5));
    printf("Read 3: %u\n", (unsigned)readBit(reg, 3));
    printf("Son durum: "); printBinary(reg);
    return 0; // Program basariyla bitti.
}
