# Görev 3 – Bit İşlemleri ve Gömülü C

Başlangıç seviyesine uygun, Türkçe açıklama satırları içeren bağımsız C uygulamaları.

## Derleme

Her C dosyasını ayrı derleyin: `gcc -std=c11 -Wall -Wextra dosya.c -o program`.
Windows üzerinde `.\program.exe` ile çalıştırabilirsiniz.

- 01: Set, clear, toggle, read ve binary gösterim.
- 02: MSB/LSB, signed/unsigned ve BMP280 ham veri biçimi.
- 03: Bellekte bayt sırası.
- 04: static, const ve volatile.
- 05: Genel amaçlı iki baytlık paketleme örneği.

**Not:** const_hatasi.c bilinçli olarak derleme hatası içerir.
Volatile örneği gerçek donanım kesmesi değil masaüstü simülasyonudur.
Paketleme örneği tam uçuş telemetrisi değil, genel amaçlı bir eğitim örneğidir.
Kodların gerçek derleme sonuçları, STM32CubeIDE kurulumu ve ekran görüntüleri ayrıca doğrulanmalıdır.

## Kaynaklar

- [BMP280 datasheet](https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bmp280-ds001.pdf)
- [C dil referansı](https://en.cppreference.com/c/language)
- [STM32CubeIDE](https://www.st.com/en/development-tools/stm32cubeide.html)
