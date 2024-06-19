package it.mfm.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDaoInterfaccia {

    public void doSave(UserBean user) throws SQLException;

    public UserBean doRetriveByUsernameAndPassword(String username, String password) throws SQLException;

    public ArrayList<PaymentMethodBean> doRetrivePaymentMethods(UserBean userBean) throws SQLException;

    public void doUpdate(UserBean user) throws SQLException;

    public void doDelete(UserBean user) throws SQLException;
}
