package it.mfm.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDaoInterfaccia {

    public int doSave(OrderBean orderBean) throws SQLException;

    public OrderBean doRetrieveByUsername(String utente_username) throws SQLException;

    public ArrayList<OrderBean> doRetrieveAll() throws SQLException;

    public OrderBean doRetrieveByUser(UserBean userBean) throws SQLException;

    public ArrayList<OrderBean> doRetrieveByDateInterval(Date startDate, Date endDate) throws SQLException;
}
