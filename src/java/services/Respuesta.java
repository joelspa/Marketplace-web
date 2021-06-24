/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;


/**
 *
 * @author josec
 */
public class Respuesta {
    
    private boolean isOK;
    private String message;

    public Respuesta() {
        isOK = true;
    }

    public Respuesta(boolean isOK, String message) {
        this.isOK = isOK;
        this.message = message;
    }

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
    
}
