package com.wpa.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initDatabase() {
        String dropTable = "DROP TABLE IF EXISTS business_knowledge";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS business_knowledge "
                + "(id INT AUTO_INCREMENT PRIMARY KEY, "
                + " type VARCHAR(100), "
                + " title VARCHAR(255), "
                + " short_desc VARCHAR(255), "
                + " content CLOB)";

        try (Connection conn = DatabaseManager.getConnection();
             Statement statement = conn.createStatement()) {
//            statement.execute(dropTable);
            statement.execute(createTableSQL);
//            insertTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertTestData() throws SQLException {
        String sql = "INSERT INTO business_knowledge (type,title, short_desc,content) VALUES (?,?, ?,?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for(int i=0;i<5;i++) {
                pstmt.setString(1, "BR");
                pstmt.setString(2, "Title-BR-" + System.currentTimeMillis());
                pstmt.setString(3, "short_desc-" + System.currentTimeMillis());
                pstmt.setString(4, "content details-" + System.currentTimeMillis());
                pstmt.executeUpdate();
            }
        }
    }
}
