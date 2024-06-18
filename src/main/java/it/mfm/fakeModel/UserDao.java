package it.mfm.fakeModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {


    public static ArrayList<PaymentMethodBean> doRetrievePaymentMethods(UserBean userBean) throws SQLException {
        return null;
    }

    public UserBean doRetrieveByUsernamePassword(String username, String password) throws SQLException {
        return null;
    }

    public void doSave(UserBean userBean) throws SQLException{
    }

    public void doUpdate(UserInformation userInformation, String username) throws SQLException {
    }
}

