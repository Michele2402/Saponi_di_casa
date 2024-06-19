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

public class CategoryDao implements CategoryDaoInterfaccia{

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

    private static final String TABLE_NAME = "categoria";

    @Override
    public void doSave(CategoryBean category) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " +
                TABLE_NAME +
                " (id, nome, descrizione) " +
                "VALUES (?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getNome());
            preparedStatement.setString(3, category.getDescrizione());
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
    public ArrayList<CategoryBean> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<CategoryBean> categoryList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setId(resultSet.getInt("id"));
                categoryBean.setNome(resultSet.getString("nome"));
                categoryBean.setDescrizione(resultSet.getString("descrizione"));
                categoryList.add(categoryBean);
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

        return categoryList;
    }

    @Override
    public CategoryBean doRetrieveById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CategoryBean categoryBean = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                categoryBean = new CategoryBean();
                categoryBean.setId(resultSet.getInt("id"));
                categoryBean.setNome(resultSet.getString("nome"));
                categoryBean.setDescrizione(resultSet.getString("descrizione"));
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

        return categoryBean;
    }
}
