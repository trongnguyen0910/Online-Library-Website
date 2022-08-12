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
public class BookErrorObject {
    private String idError, nameError, urlError, publisherIdError, publishingYearError, priceError, quantityError;
    
    public BookErrorObject(){
        
    }

    public BookErrorObject(String idError, String nameError, String urlError, String publisherIdError, String publishingYearError, String priceError, String quantityError) {
        this.idError = idError;
        this.nameError = nameError;
        this.urlError = urlError;
        this.publisherIdError = publisherIdError;
        this.publishingYearError = publishingYearError;
        this.priceError = priceError;
        this.quantityError = quantityError;
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
     * @return the nameError
     */
    public String getNameError() {
        return nameError;
    }

    /**
     * @param nameError the nameError to set
     */
    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    /**
     * @return the urlError
     */
    public String getUrlError() {
        return urlError;
    }

    /**
     * @param urlError the urlError to set
     */
    public void setUrlError(String urlError) {
        this.urlError = urlError;
    }

    /**
     * @return the publisherIdError
     */
    public String getPublisherIdError() {
        return publisherIdError;
    }

    /**
     * @param publisherIdError the publisherIdError to set
     */
    public void setPublisherIdError(String publisherIdError) {
        this.publisherIdError = publisherIdError;
    }

    /**
     * @return the publishingYearError
     */
    public String getPublishingYearError() {
        return publishingYearError;
    }

    /**
     * @param publishingYearError the publishingYearError to set
     */
    public void setPublishingYearError(String publishingYearError) {
        this.publishingYearError = publishingYearError;
    }

    /**
     * @return the priceError
     */
    public String getPriceError() {
        return priceError;
    }

    /**
     * @param priceError the priceError to set
     */
    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    /**
     * @return the quantityError
     */
    public String getQuantityError() {
        return quantityError;
    }

    /**
     * @param quantityError the quantityError to set
     */
    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    
    
}
