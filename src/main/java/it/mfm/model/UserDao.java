package it.mfm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDao implements UserDaoInterfaccia {

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

    @Override
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
            preparedStatement.setInt(8, userBean.getAdmin());

            preparedStatement.executeUpdate();

            connection.commit();
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
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

    @Override
    public synchronized UserBean doRetriveByUsernameAndPassword(String username, String password) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        UserBean userBean = new UserBean();

        String searchSql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(searchSql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                userBean.setUsername(resultSet.getString("username"));
                userBean.setNome(resultSet.getString("nome"));
                userBean.setCognome(resultSet.getString("cognome"));
                userBean.setEmail(resultSet.getString("email"));
                userBean.setPassword(resultSet.getString("password"));
                userBean.setIndirizzo(resultSet.getString("indirizzo"));
                userBean.setTelefono(resultSet.getString("telefono"));
                userBean.setAdmin(resultSet.getInt("admin"));
            }
            else {userBean = null;}
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
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
        return userBean;
    }

    @Override
    public synchronized ArrayList<PaymentMethodBean> doRetrivePaymentMethods(UserBean userBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<PaymentMethodBean> bean = new ArrayList<PaymentMethodBean>();

        String searchSql = "SELECT * FROM metodo_di_pagamento" + " WHERE utente_username = ?";

        try{
            connection = ds.getConnection();

            preparedStatement = connection.prepareStatement(searchSql);
            preparedStatement.setString(1, userBean.getUsername());

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                PaymentMethodBean paymentMethodBean = new PaymentMethodBean();
                paymentMethodBean.setUtente_username(resultSet.getString("utente_username"));
                paymentMethodBean.setNome(resultSet.getString("nome"));
                paymentMethodBean.setCognome(resultSet.getString("cognome"));
                paymentMethodBean.setNumero_di_carta(resultSet.getString("numero_di_carta"));
                paymentMethodBean.setData_di_Scadenza(resultSet.getDate("data_di_Scadenza"));
                paymentMethodBean.setCvv(resultSet.getString("cvv"));
                bean.add(paymentMethodBean);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
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
        return bean;
    }

    @Override
    public synchronized void doUpdate(UserBean user) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE " + TABLE_NAME
                + " SET password = ?, nome = ?, cognome = ?, email = ?, indirizzo = ?, telefono = ?, admin = ? "
                + " WHERE username = ? ";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getNome());
            preparedStatement.setString(3, user.getCognome());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getIndirizzo());
            preparedStatement.setInt(6, user.getAdmin());
            preparedStatement.setString(7, user.getUsername());
            preparedStatement.executeUpdate();

            connection.commit();
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    @Override
    public synchronized void doDelete(UserBean user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE username = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally{
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
            }
            finally{
                if (connection != null)
                    connection.close();
            }
        }
    }

}