/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import longbnh.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class CartObject implements Serializable {

    private Map<Integer, ProductDTO> items;
    private float total;

    public void addCakeToCart(int productID, ProductDTO cake) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        if (this.items.containsKey(productID)) {
            cake = this.items.get(productID);
            cake.setQuantity(cake.getQuantity() + 1);
        }
        this.items.put(productID, cake);

    }

    public void removeCake(int productID) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(productID)) {
            this.items.remove(productID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void updateQuantityCake(int productID, int newQuantity) {
        if (this.items.containsKey(productID)) {
            ProductDTO cake = this.items.get(productID);
            if (newQuantity <= 0) {
                removeCake(productID);
            } else {
                cake.setQuantity(newQuantity);
                this.items.put(productID, cake);
            }
        }

    }

    public void caculateTotal() {
        this.total = 0;
        if (this.items != null) {
            for (Map.Entry<Integer, ProductDTO> entry : this.items.entrySet()) {
                ProductDTO cake = entry.getValue();
                this.total += cake.getPrice() * cake.getQuantity();
            }
        }
    }

    public Map<Integer, ProductDTO> getItems() {
        return items;
    }

    public float getTotal() {
        return total;
    }

}
