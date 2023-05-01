package com.example.dtialphatesting;

public class CustomerModel {

    private String customerNameValue;
    private String customerAddressValue;
    private String customerNumberValue;
    private String customerEmailValue;

    public CustomerModel(String customerNameValue, String customerAddressValue, String customerNumberValue, String customerEmailValue) {
        this.customerNameValue = customerNameValue;
        this.customerAddressValue = customerAddressValue;
        this.customerNumberValue = customerNumberValue;
        this.customerEmailValue = customerEmailValue;
    }

    public String getCustomerNameValue() {
        return customerNameValue;
    }

    public void setCustomerNameValue(String customerNameValue) {
        this.customerNameValue = customerNameValue;
    }

    public String getCustomerAddressValue() {
        return customerAddressValue;
    }

    public void setCustomerAddressValue(String customerAddressValue) {
        this.customerAddressValue = customerAddressValue;
    }

    public String getCustomerNumberValue() {
        return customerNumberValue;
    }

    public void setCustomerNumberValue(String customerNumberValue) {
        this.customerNumberValue = customerNumberValue;
    }

    public String getCustomerEmailValue() {
        return customerEmailValue;
    }

    public void setCustomerEmailValue(String customerEmailValue) {
        this.customerEmailValue = customerEmailValue;
    }
}
