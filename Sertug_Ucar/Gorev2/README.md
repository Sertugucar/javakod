# Görev 2 — Git ve Gömülü C'ye Giriş

**Hazırlayan:** Sertuğ Uçar
**Kapsam:** 1. sınıf, Gömülü Yazılım Ekibi - Faz 2 / 2. hafta

Bu klasörde dört çalıştırılabilir C uygulaması ve ortak `SensorMeasurement` veri yapısı bulunur.

| Dosya | İçerik |
| --- | --- |
| 01_veri_tipleri.c | sizeof, stdint.h ve uint8_t taşması |
| 02_pointer.c | Değer ile gönderme ve pointer üzerinden değiştirme |
| sensor.h | Sensör ölçüm yapısı (zaman, basınç, irtifa, ivme) |
| 03_struct.c | Yapı boyutu, alanlar toplamı ve padding |
| 04_apogee.c | Örnek irtifa dizilerinde iki tespit yönteminin karşılaştırılması |

## GCC ile derleme

`gcc -std=c11 -Wall -Wextra -Wpedantic 01_veri_tipleri.c -o veri_tipleri`
`gcc -std=c11 -Wall -Wextra -Wpedantic 02_pointer.c -o pointer`
`gcc -std=c11 -Wall -Wextra -Wpedantic 03_struct.c -o sensor_struct`
`gcc -std=c11 -Wall -Wextra -Wpedantic 04_apogee.c -o apogee`

Ardından programları çalıştırmak için sırasıyla ./veri_tipleri, ./pointer, ./sensor_struct ve ./apogee komutlarını kullanın.

## Sonuç

- uint8_t değeri 255'ten 1 artınca 0'a döner.
- Kopya parametredeki değişiklik, çağıranın değişkenini etkilemez. Pointer ile etkiler.
- Struct boyutu, alan toplamından hizalama nedeniyle büyük olabilir.
- Tek hatalı düşük ölçüm, ilk düşüş yöntemini yanıltır; 3 ardışık düşüş testi örnekte doğru tepeyi bulur.

Apogee kodu sadece çevrimdışı eğitim verisi üzerinde çalışır; gerçek uçuş ve fiziksel sistem kontrolü için tasarlanmamıştır.

Teslim raporu PDF, uygulama açıklamaları ve program çıktısı görselleri ayrıca sağlanır.
