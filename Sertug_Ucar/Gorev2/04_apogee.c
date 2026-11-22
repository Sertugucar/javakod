#include <stddef.h>
#include <stdio.h>
#include "sensor.h"

#define ARRAY_LEN(a) (sizeof(a) / sizeof((a)[0]))

/* Yalnizca dizi uzerinde egitim amacli analiz; fiziksel donanim kontrol etmez. */
static int ilk_dususte_apogee(const SensorMeasurement *olcumler, size_t n) {
    if (olcumler == NULL || n < 2U) return -1;
    for (size_t i = 1U; i < n; ++i) {
        if (olcumler[i].altitude_m < olcumler[i - 1U].altitude_m) {
            return (int)(i - 1U);
        }
    }
    return -1;
}

/* Ardisik 3 azalma gordugunde azalmanin basladigi indeksi dondurur. */
static int uc_ardisik_dususte_apogee(const SensorMeasurement *olcumler, size_t n) {
    if (olcumler == NULL || n < 4U) return -1;
    unsigned ardisik_dusus = 0U;
    for (size_t i = 1U; i < n; ++i) {
        if (olcumler[i].altitude_m < olcumler[i - 1U].altitude_m) {
            ++ardisik_dusus;
            if (ardisik_dusus == 3U) return (int)(i - 3U);
        } else {
            ardisik_dusus = 0U;
        }
    }
    return -1;
}

static void sonucu_yaz(const char *isim, const SensorMeasurement *dizi, size_t n) {
    int naif = ilk_dususte_apogee(dizi, n);
    int filtreli = uc_ardisik_dususte_apogee(dizi, n);
    printf("%s: olcum sayisi=%zu\n", isim, n);
    printf("  Ilk dususte: indeks=%d", naif);
    if (naif >= 0) printf(" (%.1f m)", (double)dizi[naif].altitude_m);
    printf("\n  3 ardisik dususte: indeks=%d", filtreli);
    if (filtreli >= 0) printf(" (%.1f m)", (double)dizi[filtreli].altitude_m);
    printf("\n");
}

int main(void) {
    /* Zaman (ms), basinc (Pa), irtifa (m), 3 eksende ivme (m/s^2). */
    const SensorMeasurement temiz[] = {
        {   0,101325,   0,0,0,9.8f}, { 100,100000, 120,0,0,9.8f},
        { 200, 98000, 250,0,0,9.8f}, { 300, 96600, 390,0,0,9.8f},
        { 400, 95000, 520,0,0,9.8f}, { 500, 93500, 640,0,0,9.8f},
        { 600, 92500, 710,0,0,9.8f}, { 700, 91000, 820,0,0,9.8f},
        { 800, 90000, 930,0,0,9.8f}, { 900, 89400, 990,0,0,9.8f},
        {1000, 89200,1010,0,0,9.8f}, {1100, 89250,1005,0,0,9.8f},
        {1200, 89350, 997,0,0,9.8f}, {1300, 89500, 983,0,0,9.8f},
        {1400, 89700, 960,0,0,9.8f}
    };
    const SensorMeasurement gurultulu[] = {
        {   0,101325,   0,0,0,9.8f}, { 100,100000, 120,0,0,9.8f},
        { 200, 98000, 250,0,0,9.8f}, { 300, 96600, 390,0,0,9.8f},
        { 400, 95000, 520,0,0,9.8f}, { 500, 93500, 640,0,0,9.8f},
        { 600, 92500, 710,0,0,9.8f}, { 700, 94000, 580,0,0,9.8f}, /* tek hatali dusus */
        { 800, 91000, 820,0,0,9.8f}, { 900, 90000, 930,0,0,9.8f},
        {1000, 89400, 990,0,0,9.8f}, {1100, 89200,1010,0,0,9.8f},
        {1200, 89250,1005,0,0,9.8f}, {1300, 89350, 997,0,0,9.8f},
        {1400, 89500, 983,0,0,9.8f}, {1500, 89700, 960,0,0,9.8f}
    };
    sonucu_yaz("Temiz veri", temiz, ARRAY_LEN(temiz));
    sonucu_yaz("Gurultulu veri", gurultulu, ARRAY_LEN(gurultulu));

    /* Basit otomatik kontroller: temiz 10, gurultulu naif 6 ve filtreli 11. */
    if (ilk_dususte_apogee(temiz, ARRAY_LEN(temiz)) != 10 ||
        uc_ardisik_dususte_apogee(temiz, ARRAY_LEN(temiz)) != 10 ||
        ilk_dususte_apogee(gurultulu, ARRAY_LEN(gurultulu)) != 6 ||
        uc_ardisik_dususte_apogee(gurultulu, ARRAY_LEN(gurultulu)) != 11 ||
        uc_ardisik_dususte_apogee(NULL, 0) != -1) {
        fprintf(stderr, "TEST BASARISIZ\n");
        return 1;
    }
    printf("Tum ornek testleri basarili.\n");
    return 0;
}
