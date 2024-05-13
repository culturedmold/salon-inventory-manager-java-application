package com.example.saloninventorymanager.domain_objects;

import java.text.NumberFormat;
import java.util.Locale;

public class InventoryItem {
    private Integer prodId;
    private String prodName;
    private Double prodCost;

    private final String prodCostFormatted;
    private final String prodRetailFormatted;
    private Double prodRetail;
    private Integer prodQuantity;

    public InventoryItem(Integer prodId, String prodName, Double prodCost, Double prodRetail, Integer prodQuantity) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodCost = prodCost;
        this.prodRetail = prodRetail;
        this.prodQuantity = prodQuantity;

        prodCostFormatted = NumberFormat.getCurrencyInstance(Locale.of("en", "us")).format(prodCost);
        prodRetailFormatted = NumberFormat.getCurrencyInstance(Locale.of("en", "us")).format(prodRetail);
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getProdCost() {
        return prodCost;
    }

    public void setProdCost(Double prodCost) {
        this.prodCost = prodCost;
    }

    public Double getProdRetail() {
        return prodRetail;
    }

    public void setProdRetail(Double prodRetail) {
        this.prodRetail = prodRetail;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public String getProdCostFormatted() {
        return prodCostFormatted;
    }

    public String getProdRetailFormatted() {
        return prodRetailFormatted;
    }
}
