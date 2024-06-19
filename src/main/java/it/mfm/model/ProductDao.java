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

public class ProductDao implements ProductDaoInterfaccia{

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

    private static final String TABLE_NAME = "prodotto";

    @Override
    public synchronized void doSave(ProductBean productBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " +
                TABLE_NAME +
                " (id, nome, descrizione, prezzo, immagine, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, productBean.getId());
            preparedStatement.setString(2, productBean.getNome());
            preparedStatement.setString(3, productBean.getDescrizione());
            preparedStatement.setInt(4, productBean.getPrezzo());
            preparedStatement.setString(5, productBean.getImmagine());
            preparedStatement.setInt(6, productBean.getCategoria_id());
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
    public synchronized void doUpdate(ProductBean productBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE " +
                TABLE_NAME +
                " SET nome = ?, descrizione = ?, prezzo = ?, immagine = ?, categoria_id = ? WHERE id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, productBean.getNome());
            preparedStatement.setString(2, productBean.getDescrizione());
            preparedStatement.setInt(3, productBean.getPrezzo());
            preparedStatement.setString(4, productBean.getImmagine());
            preparedStatement.setInt(5, productBean.getCategoria_id());
            preparedStatement.setInt(6, productBean.getId());
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
    public synchronized void doDelete(ProductBean productBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, productBean.getId());
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
    public ProductBean doRetrieveById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ProductBean productBean = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                productBean = new ProductBean();
                productBean.setId(resultSet.getInt("id"));
                productBean.setNome(resultSet.getString("nome"));
                productBean.setDescrizione(resultSet.getString("descrizione"));
                productBean.setPrezzo(resultSet.getInt("prezzo"));
                productBean.setImmagine(resultSet.getString("immagine"));
                productBean.setCategoria_id(resultSet.getInt("categoria_id"));
            }
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

        return productBean;
    }

    @Override
    public ArrayList<ProductBean> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ProductBean> productList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductBean productBean = new ProductBean();
                productBean.setId(resultSet.getInt("id"));
                productBean.setNome(resultSet.getString("nome"));
                productBean.setDescrizione(resultSet.getString("descrizione"));
                productBean.setPrezzo(resultSet.getInt("prezzo"));
                productBean.setImmagine(resultSet.getString("immagine"));
                productBean.setCategoria_id(resultSet.getInt("categoria_id"));
                productList.add(productBean);
            }
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

        return productList;
    }

    @Override
    public ArrayList<ProductBean> doRetrieveByCategory(int categoria_id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ProductBean> productList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE categoria_id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, categoria_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProductBean productBean = new ProductBean();
                productBean.setId(resultSet.getInt("id"));
                productBean.setNome(resultSet.getString("nome"));
                productBean.setDescrizione(resultSet.getString("descrizione"));
                productBean.setPrezzo(resultSet.getInt("prezzo"));
                productBean.setImmagine(resultSet.getString("immagine"));
                productBean.setCategoria_id(resultSet.getInt("categoria_id"));
                productList.add(productBean);
            }
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

        return productList;
    }

    @Override
    public ArrayList<ReviewBean> doRetrieveReviewsById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ReviewBean> reviewList = new ArrayList<>();

        String selectSQL = "SELECT * FROM recensione WHERE prodotto_id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ReviewBean reviewBean = new ReviewBean();
                reviewBean.setId(resultSet.getInt("id"));
                reviewBean.setUtente_username(resultSet.getString("utente_username"));
                reviewBean.setValutazione(resultSet.getInt("valutazione"));
                reviewBean.setTesto(resultSet.getString("testo"));
                reviewBean.setData(resultSet.getDate("data"));
                reviewBean.setProdotto_id(resultSet.getInt("prodotto_id"));
                reviewList.add(reviewBean);
            }
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

        return reviewList;
    }
}
