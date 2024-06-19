package it.mfm.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDaoInterfaccia {

    public void doSave(ProductBean productBean) throws SQLException;

    public void doUpdate(ProductBean productBean) throws SQLException;

    public void doDelete(ProductBean productBean) throws SQLException;

    public ProductBean doRetrieveById(int id) throws SQLException;

    ArrayList<ProductBean> doRetrieveAll() throws SQLException;

    ArrayList<ProductBean> doRetrieveByCategory(int categoria_id) throws SQLException;

    ArrayList<ReviewBean>  doRetrieveReviewsById(int id) throws SQLException;
}
