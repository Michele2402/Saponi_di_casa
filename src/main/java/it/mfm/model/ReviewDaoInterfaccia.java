package it.mfm.model;

import java.sql.SQLException;

public interface ReviewDaoInterfaccia {

    public void doSave(ReviewBean reviewBean) throws SQLException;

}
