package it.mfm.model;

import it.mfm.model.ProductBean;

import java.util.HashMap;

public class Cart {

    HashMap<ProductBean, Integer> products; // Products in the cart

    public Cart() {
        products = new HashMap<ProductBean, Integer>();
    }

    public HashMap<ProductBean, Integer> getProducts() {
        return products;
    }

    public void addProduct(ProductBean product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product)+1);
        } else {
            products.put(product, 1);
        }
    }

    public void decreaseProduct(ProductBean product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1) {
                products.put(product, products.get(product)-1);
            } else {
                products.remove(product);
            }
        }
    }

    public void removeProduct(ProductBean product) {
        products.remove(product);
    }

    public double geTotal() {
        double total = 0;
        for (ProductBean product : products.keySet()) {
            total += product.getPrezzo() * products.get(product);
        }
        return total;
    }


}
