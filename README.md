# Java Kodları ve Örnek Projeler

Java öğrenme sürecinde geliştirilen örnek kodları, alıştırmaları ve uygulama projelerini bir araya getiren bir GitHub deposu.

## 📌 Proje hakkında

Bu depo, Java ile farklı konuları çalışmak ve masaüstü uygulamaları geliştirmek amacıyla oluşturulmuştur. Depoda Java alıştırmalarının yanı sıra **Mola Kafe Menü Yönetim Sistemi** de yer alır.

## 🛠️ Kullanılan teknolojiler

- **Java** — Uygulama geliştirme
- **Java Swing** — Masaüstü kullanıcı arayüzü
- **MySQL** — Veritabanı
- **JDBC** — Java ve MySQL bağlantısı
- **Eclipse IDE** — Java proje geliştirme ortamı
- **SQL** — Tablo oluşturma ve veri işlemleri

## 📁 Depo yapısı

```text
javakod/
├── .settings/             # Eclipse ayarları
├── JavaKursOrnekler/      # Java kurs örnekleri
├── config/                # Veritabanı yapılandırma örneği
├── kafemenu/              # Kafe menüsü ile ilgili dosyalar
├── kodlarlaornek/         # Java kod örnekleri
├── lib/                   # Proje kütüphaneleri
├── src/                   # Java kaynak kodları
├── .classpath             # Eclipse sınıf yolu ayarları
├── .project               # Eclipse proje tanımı
├── cafe_menu.sql          # Kafe menüsü veritabanı tablosu ve örnek veriler
└── README_KAFE_MENU.md    # Kafe uygulamasının ayrıntılı belgeleri
```

## ☕ Mola Kafe Menü Yönetim Sistemi

Java Swing, JDBC ve MySQL ile geliştirilen masaüstü menü yönetimi uygulamasıdır.

### Özellikler

- Menüye ürün ekleme, ürünleri listeleme, düzenleme ve silme
- Ürün fiyatlarını güncelleme
- Ürün adı, kategori, açıklama ve fiyat bilgilerini yönetme
- Anlık ürün arama
- Ürün ve kategori sayaçları
- Türkçe biçimlendirilmiş TL fiyatları
- Girdi doğrulama ve parametreli SQL sorguları
- Verileri MySQL veritabanında saklama

### Gereksinimler

- Java Development Kit (JDK)
- Eclipse IDE veya uygun bir Java geliştirme ortamı
- MySQL Server
- MySQL JDBC sürücüsü (Connector/J)

### Kurulum ve çalıştırma

**1. Depoyu klonlayın.**

```bash
git clone https://github.com/Sertugucar/javakod.git
cd javakod
```

**2. Projeyi Eclipse'te açın.**

Eclipse'te **File → Import → Existing Projects into Workspace** yolunu izleyerek depoyu seçin.

**3. Veritabanını hazırlayın.**

MySQL sunucunuzda `java_kursu` adlı veritabanını oluşturun (henüz yoksa):

```sql
CREATE DATABASE IF NOT EXISTS java_kursu
CHARACTER SET utf8mb4
COLLATE utf8mb4_turkish_ci;
```

Ardından `cafe_menu.sql` dosyasını MySQL Workbench üzerinden çalıştırın. Bu dosya `menu_urunleri` tablosunu ve başlangıç ürünlerini oluşturur.

**4. Veritabanı bağlantısını yapılandırın.**

`config/db.properties.example` dosyasını `config/db.properties` adıyla kopyalayın ve yerel MySQL bilgilerinizi girin:

```properties
db.url=jdbc:mysql://localhost:3306/java_kursu?useSSL=false&serverTimezone=Europe/Istanbul&allowPublicKeyRetrieval=true
db.user=root
db.password=KENDI_MYSQL_SIFRENIZ
```

**Önemli:** Gerçek veritabanı şifrenizi GitHub'a yüklemeyin. Alternatif olarak `KAFE_DB_URL`, `KAFE_DB_USER` ve `KAFE_DB_PASSWORD` ortam değişkenlerini kullanabilirsiniz.

**5. Uygulamayı başlatın.**

Eclipse'te projeyi yenileyin (**Refresh**) ve `swingmenukontroller.CafeMenu` sınıfını çalıştırın.

### Veritabanı tablosu

| Alan | Veri tipi | Açıklama |
| --- | --- | --- |
| `id` | `INT AUTO_INCREMENT` | Ürün kimliği |
| `ad` | `VARCHAR(100)` | Ürün adı |
| `kategori` | `VARCHAR(50)` | Ürün kategorisi |
| `aciklama` | `VARCHAR(500)` | Ürün açıklaması |
| `fiyat` | `DECIMAL(10,2)` | Ürün fiyatı |
| `olusturulma_zamani` | `TIMESTAMP` | Oluşturulma zamanı |
| `guncellenme_zamani` | `TIMESTAMP` | Güncellenme zamanı |

## 🧪 Kontrol

Kafe uygulamasını başlattıktan sonra örnek ürünlerin görüntülendiğini kontrol edin. Yeni ürün eklemeyi, fiyat güncellemeyi, aramayı ve silmeyi deneyin. Uygulamayı yeniden açarak değişikliklerin veritabanında korunduğunu doğrulayın.

## 📚 Diğer Java örnekleri

Depodaki `JavaKursOrnekler/`, `kodlarlaornek/` ve `src/` klasörleri Java çalışma dosyalarını içerir. Bu klasörlerdeki her örneğin çalıştırma yöntemi ilgili sınıfın yapısına göre değişebilir.

## 👤 Geliştirici

**Sertugucar** — [GitHub profili](https://github.com/Sertugucar)

---

Bu depo, Java öğrenme ve uygulama geliştirme çalışmalarını bir arada tutmak amacıyla hazırlanmıştır. Kafe uygulamasıyla ilgili ayrıntılar için [README_KAFE_MENU.md](README_KAFE_MENU.md) dosyasını inceleyebilirsiniz.
