/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.payment;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class PaymentDTO implements Serializable{
    private int paymentID;
    private String paymentName;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentID, String paymentName) {
        this.paymentID = paymentID;
        this.paymentName = paymentName;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getPaymentName() {
        return paymentName;
    }
    
}
