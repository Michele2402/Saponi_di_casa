package it.mfm.fakeModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {

    public void doSave(ProductBean product) throws SQLException {
        // Implementation here
    }

    public ArrayList<ProductBean> doRetrieveAll() throws SQLException {
        // Implementation here
        return null;
    }

    public ArrayList<ReviewBean> doRetrieveReviewsById(int id) throws SQLException {
        // Implementation here
        return null;
    }

    ArrayList<ProductBean> doRetrieveByCategory(int id) throws SQLException {
        // Implementation here
        return null;
    }

    public ProductBean doRetreveById(int id) throws SQLException {
        // Implementation here
        return null;
    }

    public void doUpdate(int id, ProductBean product) throws SQLException {
        // Implementation here
    }

    public void doDelete(int id) throws SQLException {
        // Implementation here
    }
}
