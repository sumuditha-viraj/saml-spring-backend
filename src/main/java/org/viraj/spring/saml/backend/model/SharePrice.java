package org.viraj.spring.saml.backend.model;

public class SharePrice {

    private String name;
    private Double stockPrice;

    public SharePrice(String name, Double stockPrice){
        this.name = name;
        this.stockPrice = stockPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
