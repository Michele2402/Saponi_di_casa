package it.mfm.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class PaymentMethodDao implements PaymentMethodDaoInterfaccia{

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

    private static final String TABLE_NAME = "metodo_di_pagamento";

    @Override
    public synchronized void doSave(PaymentMethodBean paymentMethodBean) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME
                + "(numero_di_carta, tipo, cvv, nome, cognome, data_di_Scadenza, utente_username) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, paymentMethodBean.getNumero_di_carta());
            preparedStatement.setString(2, paymentMethodBean.getTipo());
            preparedStatement.setString(3, paymentMethodBean.getCvv());
            preparedStatement.setString(4, paymentMethodBean.getNome());
            preparedStatement.setString(5, paymentMethodBean.getCognome());
            preparedStatement.setDate(6, new Date(paymentMethodBean.getData_di_Scadenza().getTime()));
            preparedStatement.setString(7, paymentMethodBean.getUtente_username());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
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
    public synchronized void doDeleteByNumber(int numero_di_carta) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE numero_di_carta = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, numero_di_carta);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
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

}
