/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.orders;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import longbnh.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class OrdersTrackingObject implements Serializable {

    private String name;
    private String phone;
    private String address;
    private String payment;
    private float totalPrice;
    private Date orderDate;
    private List<ProductDTO> listProduct;

    public OrdersTrackingObject(String name, String phone, String address, String payment, float totalPrice, Date orderDate, List<ProductDTO> listProduct) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.payment = payment;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.listProduct = listProduct;
    }

    public OrdersTrackingObject() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPayment() {
        return payment;
    }

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

}
