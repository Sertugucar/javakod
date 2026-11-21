package tr.kafemenu.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** MySQL bağlantısını dış yapılandırma dosyasından veya ortam değişkenlerinden kurar. */
public final class DatabaseConfig {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/java_kursu"
            + "?useSSL=false&serverTimezone=Europe/Istanbul&allowPublicKeyRetrieval=true";

    private DatabaseConfig() {
    }

    public static Connection openConnection() throws SQLException {
        Properties properties = new Properties();
        Path configPath = Paths.get("config", "db.properties");

        if (Files.exists(configPath)) {
            try (InputStream input = Files.newInputStream(configPath)) {
                properties.load(input);
            } catch (IOException exception) {
                throw new SQLException("config/db.properties dosyası okunamadı.", exception);
            }
        }

        String url = setting("KAFE_DB_URL", "db.url", DEFAULT_URL, properties);
        String user = setting("KAFE_DB_USER", "db.user", "", properties);
        String password = setting("KAFE_DB_PASSWORD", "db.password", "", properties);

        if (user.isEmpty()) {
            throw new SQLException("MySQL hesabı belirtilmedi. config/db.properties dosyasına db.user alanını yazın.");
        }
        if ("MYSQL_SIFRENIZI_BURAYA_YAZIN".equals(password)) {
            throw new SQLException("config/db.properties içindeki db.password alanına MySQL şifrenizi yazın.");
        }

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            throw new SQLException("MySQL bağlantısı kurulamadı. Sunucunun açık ve bağlantı bilgilerinin doğru "
                    + "olduğunu kontrol edin. Ayrıntı: " + exception.getMessage(), exception);
        }
    }

    private static String setting(String environmentName, String propertyName, String defaultValue,
            Properties properties) {
        String environmentValue = System.getenv(environmentName);
        if (environmentValue != null && !environmentValue.trim().isEmpty()) {
            return environmentValue.trim();
        }

        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue != null && !propertyValue.trim().isEmpty()) {
            return propertyValue.trim();
        }

        return defaultValue;
    }
}
