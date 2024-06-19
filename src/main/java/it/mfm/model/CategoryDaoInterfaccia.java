package it.mfm.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoryDaoInterfaccia {

    public void doSave(CategoryBean category) throws SQLException;

    public ArrayList<CategoryBean> doRetrieveAll() throws SQLException;

    public CategoryBean doRetrieveById(int id) throws SQLException;
}
