package it.mfm.fakeModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {

    public int doSave(OrderBean orderBean) throws SQLException {
        // method body
        return 0;

    }

    public ArrayList<OrderBean> doRetrieveByUsername(String utente_username) throws SQLException {
        // method body
        return null;
    }

    public ArrayList<OrderBean> doRetrieveAll() throws SQLException {
        // method body
        return null;
    }

    public OrderBean doRetrieveByUser(UserBean userBean) throws SQLException {
        // method body
        return null;
    }

    public ArrayList<OrderBean> doRetrieveByDateInterval(Date startDate, Date endDate) throws SQLException {
        // method body
        return null;
    }
}
