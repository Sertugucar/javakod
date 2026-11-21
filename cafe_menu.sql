-- Mola Kafe menusu: java_kursu semasinda tablo ve baslangic urunleri.
USE java_kursu;

CREATE TABLE IF NOT EXISTS menu_urunleri (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ad VARCHAR(100) NOT NULL,
    kategori VARCHAR(50) NOT NULL,
    aciklama VARCHAR(500),
    fiyat DECIMAL(10, 2) NOT NULL,
    olusturulma_zamani TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    guncellenme_zamani TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_menu_urunleri_ad (ad), INDEX idx_menu_urunleri_kategori (kategori),
    CONSTRAINT chk_menu_urunleri_fiyat CHECK (fiyat > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;

INSERT INTO menu_urunleri (ad, kategori, aciklama, fiyat)
SELECT v.ad, v.kategori, v.aciklama, v.fiyat FROM (
 SELECT 'Türk Kahvesi' ad, 'Sıcak Kahve' kategori, 'Geleneksel, lokum eşliğinde' aciklama, 85.00 fiyat UNION ALL
 SELECT 'Espresso','Sıcak Kahve','Tek shot espresso',70.00 UNION ALL
 SELECT 'Americano','Sıcak Kahve','Espresso ve sıcak su',95.00 UNION ALL
 SELECT 'Latte','Sıcak Kahve','Espresso ve süt',115.00 UNION ALL
 SELECT 'Cappuccino','Sıcak Kahve','Espresso, süt ve köpük',115.00 UNION ALL
 SELECT 'Mocha','Sıcak Kahve','Çikolatalı espresso ve süt',125.00 UNION ALL
 SELECT 'Filtre Kahve','Sıcak Kahve','Günlük demlenmiş kahve',90.00 UNION ALL
 SELECT 'Flat White','Sıcak Kahve','Çift espresso ve süt',125.00 UNION ALL
 SELECT 'Ice Latte','Soğuk Kahve','Buzlu espresso ve süt',120.00 UNION ALL
 SELECT 'Ice Americano','Soğuk Kahve','Buzlu espresso',100.00 UNION ALL
 SELECT 'Cold Brew','Soğuk Kahve','Soğuk demlenmiş kahve',125.00 UNION ALL
 SELECT 'Türk Çayı','Çay','İnce belli bardakta',35.00 UNION ALL
 SELECT 'Yeşil Çay','Çay','Demleme yeşil çay',65.00 UNION ALL
 SELECT 'Bitki Çayı','Çay','Mevsim bitki çayı',65.00 UNION ALL
 SELECT 'Ev Yapımı Limonata','Soğuk İçecek','Limon ve nane',90.00 UNION ALL
 SELECT 'Sıkma Portakal Suyu','Soğuk İçecek','Taze sıkılmış',120.00 UNION ALL
 SELECT 'Çilekli Milkshake','Milkshake','Çilek ve süt',130.00 UNION ALL
 SELECT 'Çikolatalı Milkshake','Milkshake','Çikolata ve süt',130.00 UNION ALL
 SELECT 'Mango Smoothie','Smoothie','Mango ve yoğurt',135.00 UNION ALL
 SELECT 'Çilek Smoothie','Smoothie','Çilek ve yoğurt',125.00 UNION ALL
 SELECT 'San Sebastian','Tatlı','Kremamsı cheesecake',165.00 UNION ALL
 SELECT 'Çikolatalı Brownie','Tatlı','Cevizli brownie',115.00 UNION ALL
 SELECT 'Tiramisu','Tatlı','Kahveli İtalyan tatlısı',145.00 UNION ALL
 SELECT 'Havuçlu Kek','Pasta','Tarçınlı ev keki',95.00 UNION ALL
 SELECT 'Çikolatalı Pasta','Pasta','Günlük çikolatalı pasta',150.00 UNION ALL
 SELECT 'Dondurma (2 top)','Dondurma','Günün çeşitleri',80.00 UNION ALL
 SELECT 'Tost','Atıştırmalık','Kaşarlı sıcak tost',95.00
) v WHERE NOT EXISTS (SELECT 1 FROM menu_urunleri m WHERE m.ad = v.ad);
