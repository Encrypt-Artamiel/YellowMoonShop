/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.status;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class StatusDTO implements Serializable{
    private int statusID;
    private String statusName;

    public StatusDTO(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    public StatusDTO() {
    }

    public int getStatusID() {
        return statusID;
    }

    public String getStatusName() {
        return statusName;
    }
}
