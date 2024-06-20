package it.mfm.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class OrderDao implements OrderDaoInterfaccia{

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

    private static final String TABLE_NAME = "ordine";

    @Override
    public void doSave(OrderBean orderBean) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " +
                TABLE_NAME +
                "(id, totale, data_creazione, utente_username) " +
                "VALUES (?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setDouble(2, orderBean.getTotale());
            preparedStatement.setDate(3, new java.sql.Date(orderBean.getData_creazione().getTime()));
            preparedStatement.setString(4, orderBean.getUtente_username());
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
    public OrderBean doRetrieveByUsername(String utente_username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OrderBean orderBean = null;

        String selectSQL = "SELECT * FROM " +
                TABLE_NAME +
                " WHERE utente_username = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, utente_username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderBean = new OrderBean();
                orderBean.setId(resultSet.getInt("id"));
                orderBean.setTotale(resultSet.getDouble("totale"));
                orderBean.setData_creazione(resultSet.getDate("data_creazione"));
                orderBean.setUtente_username(resultSet.getString("utente_username"));
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

        return orderBean;
    }

    @Override
    public ArrayList<OrderBean> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<OrderBean> orderList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderBean orderBean = new OrderBean();
                orderBean.setId(resultSet.getInt("id"));
                orderBean.setTotale(resultSet.getDouble("totale"));
                orderBean.setData_creazione(resultSet.getDate("data_creazione"));
                orderBean.setUtente_username(resultSet.getString("utente_username"));
                orderList.add(orderBean);
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

        return orderList;
    }

    @Override
    public OrderBean doRetrieveByUser(UserBean userBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OrderBean orderBean = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE utente_username = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userBean.getUsername());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                orderBean = new OrderBean();
                orderBean.setId(resultSet.getInt("id"));
                orderBean.setTotale(resultSet.getDouble("totale"));
                orderBean.setData_creazione(resultSet.getDate("data_creazione"));
                orderBean.setUtente_username(resultSet.getString("utente_username"));
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

        return orderBean;
    }

    @Override
    public ArrayList<OrderBean> doRetrieveByDateInterval(Date startDate, Date endDate) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<OrderBean> orderList = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE data_creazione BETWEEN ? AND ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(endDate.getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderBean orderBean = new OrderBean();
                orderBean.setId(resultSet.getInt("id"));
                orderBean.setTotale(resultSet.getDouble("totale"));
                orderBean.setData_creazione(resultSet.getDate("data_creazione"));
                orderBean.setUtente_username(resultSet.getString("utente_username"));
                orderList.add(orderBean);
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

        return orderList;
    }
}
