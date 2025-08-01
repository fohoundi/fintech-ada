package ci.ada.dao;


import ci.ada.common.ConnectionSingleton;
import ci.ada.model.Wallet;

import java.sql.*;

public class WalletDao {

    private static final String INSERT = "INSERT INTO Wallet (balance) VALUES(?)";
    private static final String UPDATE = "UPDATE Wallet SET balance=? WHERE id=?";
    private static final String DELETE = "DELETE FROM Wallet WHERE id=?";
    private static final String READ = "SELECT * FROM Wallet WHERE id=?";
    private static final Connection connection = ConnectionSingleton.getInstance().getConnection();

    public Wallet createWallet(Wallet wallet) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1, wallet.getBalance());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    wallet.setId(generatedKeys.getLong(1));

                } else {
                    throw new SQLException("Creating wallet failed, no ID obtained.");
                }
            }
            return wallet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Wallet updateWallet(Wallet wallet) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setBigDecimal(1, wallet.getBalance());
            statement.setLong(2, wallet.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

            return wallet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Wallet readWallet(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(READ);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Wallet newWallet = new Wallet();
                newWallet.setId(result.getLong("id"));
                newWallet.setBalance(result.getBigDecimal("balance"));
                return newWallet;
            }else {

                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteWallet(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
