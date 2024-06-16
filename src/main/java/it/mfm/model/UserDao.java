package it.mfm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDao {

    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/storage");

        } catch (NamingException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static final String TABLE_NAME = "utente";

    public synchronized void doSave(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (username, nome, cognome, email, password, indirizzo, telefono, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getNome());
            preparedStatement.setString(3, user.getCognome());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getIndirizzo());
            preparedStatement.setString(7, user.getTelefono());
            preparedStatement.setBoolean(8, user.isAdmin());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
                if (connection != null) connection.close();
            }
        }
    }
}
