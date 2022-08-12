/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.entities;

/**
 *
 * @author LENOVO
 */
public class MemberErrorObject {
    private String idError, passwordError, fullnameError, addressError, phoneError;
    
    public MemberErrorObject(){
        
    }

    public MemberErrorObject(String idError, String passwordError, String fullnameError, String addressError, String phoneError) {
        this.idError = idError;
        this.passwordError = passwordError;
        this.fullnameError = fullnameError;
        this.addressError = addressError;
        this.phoneError = phoneError;
    }

    /**
     * @return the idError
     */
    public String getIdError() {
        return idError;
    }

    /**
     * @param idError the idError to set
     */
    public void setIdError(String idError) {
        this.idError = idError;
    }

    /**
     * @return the passwordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * @param passwordError the passwordError to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * @return the fullnameError
     */
    public String getFullnameError() {
        return fullnameError;
    }

    /**
     * @param fullnameError the fullnameError to set
     */
    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    /**
     * @return the addressError
     */
    public String getAddressError() {
        return addressError;
    }

    /**
     * @param addressError the addressError to set
     */
    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    /**
     * @return the phoneError
     */
    public String getPhoneError() {
        return phoneError;
    }

    /**
     * @param phoneError the phoneError to set
     */
    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    
}
