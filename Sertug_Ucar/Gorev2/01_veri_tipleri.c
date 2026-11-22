#include <stdint.h>
#include <stdio.h>

int main(void) {
    printf("=== Temel C tiplerinin boyutlari ===\n");
    printf("sizeof(char)     = %zu byte\n", sizeof(char));
    printf("sizeof(short)    = %zu byte\n", sizeof(short));
    printf("sizeof(int)      = %zu byte\n", sizeof(int));
    printf("sizeof(long)     = %zu byte\n", sizeof(long));
    printf("sizeof(long long)= %zu byte\n", sizeof(long long));
    printf("sizeof(float)    = %zu byte\n", sizeof(float));
    printf("sizeof(double)   = %zu byte\n", sizeof(double));
    printf("sizeof(uint8_t)  = %zu byte\n", sizeof(uint8_t));
    printf("sizeof(int16_t)  = %zu byte\n", sizeof(int16_t));
    printf("sizeof(uint32_t) = %zu byte\n", sizeof(uint32_t));

    uint8_t sayac = UINT8_MAX;
    printf("\nTasmasindan once uint8_t: %u\n", (unsigned)sayac);
    sayac = (uint8_t)(sayac + 1U);
    printf("1 eklendikten sonra: %u\n", (unsigned)sayac);
    printf("Neden: 8 bitlik isaretsiz tip 0..255 araligindadir.\n");
    return 0;
}
