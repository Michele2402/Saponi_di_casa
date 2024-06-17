package it.mfm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public synchronized void doSave(UserBean userBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + " (username, nome, cognome, email, password, indirizzo, telefono, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, userBean.getUsername());
            preparedStatement.setString(2, userBean.getNome());
            preparedStatement.setString(3, userBean.getCognome());
            preparedStatement.setString(4, userBean.getEmail());
            preparedStatement.setString(5, userBean.getPassword());
            preparedStatement.setString(6, userBean.getIndirizzo());
            preparedStatement.setString(7, userBean.getTelefono());
            preparedStatement.setBoolean(8, userBean.isAdmin());

            preparedStatement.executeUpdate();

            connection.commit();
        }
        catch (SQLException e) {
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
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    public synchronized UserBean doRetrive(String username, String password) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        UserBean userBean = new UserBean();

        String searchSql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(searchSql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) {
                userBean.setValid(false);
            }
            else {
                userBean.setUsername(resultSet.getString("username"));
                userBean.setNome(resultSet.getString("nome"));
                userBean.setCognome(resultSet.getString("cognome"));
                userBean.setEmail(resultSet.getString("email"));
                userBean.setPassword(resultSet.getString("password"));
                userBean.setIndirizzo(resultSet.getString("indirizzo"));
                userBean.setTelefono(resultSet.getString("telefono"));
                userBean.setAdmin(resultSet.getBoolean("admin"));
            }
        }
        catch (Exception e) {
                System.out.println("Errore durante il login " + e);
        }
        finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                }
            finally {
                if(connection != null)
                    connection.close();
            }
        }
        return userBean;
    }

}

