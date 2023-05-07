package main.Pages.KasirPage;

public class CustomerTuple {
    private String customerName;
    private Integer customerId;
    
    public CustomerTuple(String name, Integer id) {
        this.customerName = name;
        this.customerId = id;
    }
    
    public String getCustomerName() {
        return this.customerName;
    }
    
    public Integer getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerName(String name) {
        this.customerName = name;
    }
    
    public void setCustomerId(Integer id) {
        this.customerId = id;
    }
}
