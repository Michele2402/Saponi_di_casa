package it.mfm.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchasedProductDao implements PurchasedProductDaoInterfaccia{

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

    private static final String TABLE_NAME = "prodotto_acquistato";

    public void doSaveAll(ArrayList<PurchasedProductBean> purchasedProductBeans) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " +
                TABLE_NAME +
                " (nome, prezzo, quantità, ordine_id) " +
                "VALUES (?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            connection.setAutoCommit(false);

            for (PurchasedProductBean purchasedProduct : purchasedProductBeans) {
                preparedStatement.setString(1, purchasedProduct.getNome());
                preparedStatement.setDouble(2, purchasedProduct.getPrezzo());
                preparedStatement.setInt(3, purchasedProduct.getQuantita());
                preparedStatement.setInt(4, purchasedProduct.getOrdine_id());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
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

    public ArrayList<PurchasedProductBean> doRetrieveAllByOrderId(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<PurchasedProductBean> purchasedProductList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE ordine_id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PurchasedProductBean purchasedProduct = new PurchasedProductBean();
                purchasedProduct.setId(resultSet.getInt("id"));
                purchasedProduct.setNome(resultSet.getString("nome"));
                purchasedProduct.setPrezzo(resultSet.getDouble("prezzo"));
                purchasedProduct.setQuantita(resultSet.getInt("quantità"));
                purchasedProduct.setOrdine_id(resultSet.getInt("ordine_id"));
                purchasedProductList.add(purchasedProduct);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
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

        return purchasedProductList;
    }


}