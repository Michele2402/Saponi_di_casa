package it.mfm.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDao implements ReviewDaoInterfaccia{

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

    private static final String TABLE_NAME = "recensione";

    public synchronized void doSave(ReviewBean reviewBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME +
                " (utente_username, valutazione, testo, data, prodotto_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, reviewBean.getUtente_username());
            preparedStatement.setInt(2, reviewBean.getValutazione());
            preparedStatement.setString(3, reviewBean.getTesto());
            preparedStatement.setDate(4, new java.sql.Date(reviewBean.getData().getTime()));
            preparedStatement.setInt(5, reviewBean.getProdotto_id());
            preparedStatement.executeUpdate();

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

}
