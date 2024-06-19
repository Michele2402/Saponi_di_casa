package it.mfm.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchasedProductDaoInterfaccia {

    public void doSaveAll() throws SQLException;

    public ArrayList<PurchasedProductBean> doRetrieveAllByOrderIds(int id) throws SQLException;

}
