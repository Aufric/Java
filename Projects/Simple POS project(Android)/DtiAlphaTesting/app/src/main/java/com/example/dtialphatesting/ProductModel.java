package com.example.dtialphatesting;

public class ProductModel {

    private String productNameValue;
    private String ProductCategoryValue;
    private double ProductPriceValue;
    private String qrbarCodeValue;


    public ProductModel(String productNameValue, String productCategoryValue, double productPriceValue, String qrbarcCodeValue) {
        this.productNameValue = productNameValue;
        ProductCategoryValue = productCategoryValue;
        ProductPriceValue = productPriceValue;
        this.qrbarCodeValue = qrbarcCodeValue;
    }

    public String getProductNameValue() {
        return productNameValue;
    }

    public void setProductNameValue(String productNameValue) {
        this.productNameValue = productNameValue;
    }

    public String getProductCategoryValue() {
        return ProductCategoryValue;
    }

    public void setProductCategoryValue(String productCategoryValue) {
        ProductCategoryValue = productCategoryValue;
    }

    public double getProductPriceValue() {
        return ProductPriceValue;
    }

    public void setProductPriceValue(double productPriceValue) {
        ProductPriceValue = productPriceValue;
    }

    public String getQrbarcCodeValue() {
        return qrbarCodeValue;
    }

    public void setQrbarcCodeValue(String qrbarcCodeValue) {
        this.qrbarCodeValue = qrbarcCodeValue;
    }
}
