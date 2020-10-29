/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.product;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CreateCakeError implements Serializable {

    private String invalidName;
    private String emptyImage;
    private String invalidDescription;
    private String invalidPrice;
    private String invalidQuantity;
    private String invalidDate;
    private String invalidInput;

    public String getInvalidName() {
        return invalidName;
    }

    public void setInvalidName(String invalidName) {
        this.invalidName = invalidName;
    }

    public String getEmptyImage() {
        return emptyImage;
    }

    public void setEmptyImage(String emptyImage) {
        this.emptyImage = emptyImage;
    }

    public String getInvalidPrice() {
        return invalidPrice;
    }

    public void setInvalidPrice(String invalidPrice) {
        this.invalidPrice = invalidPrice;
    }

    public String getInvalidQuantity() {
        return invalidQuantity;
    }

    public void setInvalidQuantity(String invalidQuantity) {
        this.invalidQuantity = invalidQuantity;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getInvalidInput() {
        return invalidInput;
    }

    public void setInvalidInput(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    public String getInvalidDescription() {
        return invalidDescription;
    }

    public void setInvalidDescription(String invalidDescription) {
        this.invalidDescription = invalidDescription;
    }

}
