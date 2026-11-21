# Mola Kafe — Menü Yönetim Sistemi

Java Swing, MySQL ve JDBC ile hazırlanmıştır. Eclipse'teki mevcut `CafeMenu` çalıştırma yapılandırması uygulamayı başlatmaya devam eder.

## Bu projede

- Ürün ekleme, listeleme, düzenleme, fiyat güncelleme ve silme
- Ürün adı, kategori, açıklama ve TL fiyatı
- Anlık arama, kategori/ürün sayaçları ve Türkçe fiyat gösterimi
- JDBC bağlantı katmanı (`DatabaseConfig`), ürün DAO'su, model ve arayüz paketleri
- Girdi doğrulama, parametreli SQL sorguları ve bağlantı kaynaklarının güvenli kapatılması

## Çalıştırma

1. MySQL Workbench'te `cafe_menu.sql` dosyasını açıp çalıştırın. Tablo `java_kursu` şemasında oluşturulur; var olan tablolar korunur.
2. Örnek ürünler de eklenir. Script tekrar çalıştırıldığında aynı örnek ürünleri çoğaltmaz.
3. `config/db.properties.example` dosyasını `config/db.properties` olarak kopyalayıp kendi MySQL kullanıcı adı ve parolanızı girin. Parolanızı kaynak koda yazmayın veya paylaşmayın; `db.properties` Git'e eklenmez.
4. Eclipse'te projeye sağ tıklayıp **Refresh** seçin; ardından `swingmenukontroller.CafeMenu` sınıfını çalıştırın.

Varsayılan sunucu adresi `localhost:3306/java_kursu` şeklindedir. MySQL kullanıcı adı ve parolası yerel ayar dosyasından veya `KAFE_DB_URL`, `KAFE_DB_USER` ve `KAFE_DB_PASSWORD` ortam değişkenlerinden alınır.

## Tablo tasarımı

| Sütun | Tip | Amaç |
| --- | --- | --- |
| `id` | `INT AUTO_INCREMENT` | Birincil anahtar |
| `ad` | `VARCHAR(100)` | Ürün adı |
| `kategori` | `VARCHAR(50)` | Menü kategorisi |
| `aciklama` | `VARCHAR(500)` | Ürün açıklaması |
| `fiyat` | `DECIMAL(10,2)` | TL fiyatı |
| `olusturulma_zamani` | `TIMESTAMP` | Kayıt zamanı |
| `guncellenme_zamani` | `TIMESTAMP` | Son güncelleme zamanı |

## Elle kontrol

Örnek ürünlerin yüklendiğini görün; yeni ürün ekleyin, fiyatını güncelleyin ve arama alanında bulun. Ardından bir ürünü silip uygulamayı kapatıp açarak verilerin MySQL'de kalıcı olduğunu doğrulayın.
