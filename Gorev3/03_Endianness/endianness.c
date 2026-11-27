#include <stdio.h>
#include <stdint.h>

int main(void) {
    uint32_t number = 0x12345678; // Dort baytlik ornek sayi.
    // Pointer sayesinde sayinin bellegindeki baytlari tek tek okuruz.
    const uint8_t *ptr = (const uint8_t *)&number;
    for (int i = 0; i < 4; ++i)
        printf("Bayt %d: 0x%02X\n", i, (unsigned)ptr[i]);
    // Bellek adresleri artarken dusuk anlamli bayt once geliyorsa little endian.
    if (ptr[0] == 0x78 && ptr[1] == 0x56 && ptr[2] == 0x34 && ptr[3] == 0x12)
        puts("Little Endian");
    else if (ptr[0] == 0x12 && ptr[1] == 0x34 && ptr[2] == 0x56 && ptr[3] == 0x78)
        puts("Big Endian");
    else puts("Farkli bayt duzeni");
    return 0;
}
