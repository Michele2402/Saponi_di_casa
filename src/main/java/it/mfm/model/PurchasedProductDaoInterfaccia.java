package it.mfm.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchasedProductDaoInterfaccia {

    public void doSaveAll(ArrayList<PurchasedProductBean> purchasedProductBeans) throws SQLException;

    public ArrayList<PurchasedProductBean> doRetrieveAllByOrderId(int id) throws SQLException;

}
