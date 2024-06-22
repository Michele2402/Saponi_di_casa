package it.mfm.model;

import java.sql.SQLException;

public interface PaymentMethodDaoInterfaccia {

    public void doSave(PaymentMethodBean paymentMethodBean) throws SQLException;

    public void doDeleteByNumber(String numero_di_carta) throws SQLException;

}
