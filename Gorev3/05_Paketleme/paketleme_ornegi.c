#include <stdio.h>
#include <stdint.h>

// Genel amacli iki baytlik masaustu veri aktarimi ornegi.
void pack(uint16_t value, uint8_t packet[2]) {
    packet[0] = (uint8_t)(value & 0xFFu); // Dusuk anlamli bayt.
    packet[1] = (uint8_t)(value >> 8); // Yuksek anlamli bayt.
}
uint16_t unpack(const uint8_t packet[2]) {
    // Little endian siradaki iki bayti yeniden birlestir.
    return (uint16_t)((uint16_t)packet[0] | ((uint16_t)packet[1] << 8));
}
int main(void) {
    uint16_t original = 0x1234;
    uint8_t packet[2];
    pack(original, packet);
    printf("Paket: %02X %02X\n", (unsigned)packet[0], (unsigned)packet[1]);
    uint16_t decoded = unpack(packet);
    printf("Orijinal: %u, Cozulen: %u\n", (unsigned)original, (unsigned)decoded);
    puts(original == decoded ? "Test basarili" : "Test basarisiz");
    return original != decoded;
}
