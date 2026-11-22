# Görev 2 — Derleme ve Çalıştırma Komutları

Hazırlayan: Sertuğ Uçar

Bu klasörde Görev 2 için yazılmış C kaynakları bulunur.

## Windows (PowerShell) — GCC yüklüyse

```powershell
gcc -std=c11 -Wall -Wextra -Wpedantic 01_veri_tipleri.c -o veri_tipleri.exe
gcc -std=c11 -Wall -Wextra -Wpedantic 02_pointer.c -o pointer.exe
gcc -std=c11 -Wall -Wextra -Wpedantic 03_struct.c -o sensor_struct.exe
gcc -std=c11 -Wall -Wextra -Wpedantic 04_apogee.c -o apogee.exe

.\veri_tipleri.exe
.\pointer.exe
.\sensor_struct.exe
.\apogee.exe
```

## Linux / macOS — GCC yüklüyse

```bash
gcc -std=c11 -Wall -Wextra -Wpedantic 01_veri_tipleri.c -o veri_tipleri
gcc -std=c11 -Wall -Wextra -Wpedantic 02_pointer.c -o pointer
gcc -std=c11 -Wall -Wextra -Wpedantic 03_struct.c -o sensor_struct
gcc -std=c11 -Wall -Wextra -Wpedantic 04_apogee.c -o apogee

./veri_tipleri
./pointer
./sensor_struct
./apogee
```

## Git komutları

```bash
git clone https://github.com/Sertugucar/javakod.git
cd javakod
git switch Sertug-Ucar
cd Sertug_Ucar/Gorev2
```

Bu örnekler eğitim amaçlıdır. Apogee uygulaması donanım kontrol etmez; yalnızca örnek ölçüm verilerini analiz eder.

Pull Request: https://github.com/Sertugucar/javakod/pull/1
