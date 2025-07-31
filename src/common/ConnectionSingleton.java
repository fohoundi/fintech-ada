package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private Connection connection;

    private ConnectionSingleton() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fintechAda", "root", "root"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    // Point d’accès au singleton
    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    // Getter pour exposer la connexion
    public Connection getConnection() {
        return connection;
    }
}
