package tr.kafemenu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tr.kafemenu.config.DatabaseConfig;
import tr.kafemenu.model.Product;

/** Menü ürünlerine ait veritabanı işlemleri. */
public class ProductDao {
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT id, ad, kategori, aciklama, fiyat FROM menu_urunleri ORDER BY kategori, ad";
        List<Product> products = new ArrayList<Product>();

        try (Connection connection = DatabaseConfig.openConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("ad"),
                        resultSet.getString("kategori"),
                        resultSet.getString("aciklama"),
                        resultSet.getBigDecimal("fiyat")));
            }
        }

        return products;
    }

    public void insert(Product product) throws SQLException {
        String sql = "INSERT INTO menu_urunleri (ad, kategori, aciklama, fiyat) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.openConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            bindProduct(statement, product);
            statement.executeUpdate();
        }
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE menu_urunleri SET ad = ?, kategori = ?, aciklama = ?, fiyat = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.openConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            bindProduct(statement, product);
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM menu_urunleri WHERE id = ?";
        try (Connection connection = DatabaseConfig.openConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private void bindProduct(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getName());
        statement.setString(2, product.getCategory());
        statement.setString(3, product.getDescription());
        statement.setBigDecimal(4, product.getPrice());
    }
}
