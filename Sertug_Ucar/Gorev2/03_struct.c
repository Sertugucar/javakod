#include <stddef.h>
#include <stdio.h>
#include "sensor.h"

int main(void) {
    const size_t alanlar_toplami = sizeof(uint32_t) + sizeof(double) + 4U * sizeof(float);
    printf("=== SensorMeasurement ===\n");
    printf("sizeof(struct) = %zu byte\n", sizeof(SensorMeasurement));
    printf("Alanlar toplami= %zu byte\n", alanlar_toplami);
    printf("Padding         = %zu byte\n", sizeof(SensorMeasurement) - alanlar_toplami);
    printf("timestamp_ms offset = %zu\n", offsetof(SensorMeasurement, timestamp_ms));
    printf("pressure_pa offset  = %zu\n", offsetof(SensorMeasurement, pressure_pa));
    printf("altitude_m offset   = %zu\n", offsetof(SensorMeasurement, altitude_m));
    printf("Not: Sonuc islemci/derleyici hizalamasina gore degisebilir.\n");
    return 0;
}
