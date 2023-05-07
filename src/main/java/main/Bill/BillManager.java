package main.Bill;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class BillManager implements Serializable {
    private List<Bill> listBill;

    public BillManager() {
        this.listBill = new ArrayList<>();
    }

    @XmlElement
    public List<Bill> getListBill() {
        return this.listBill;
    }

    public void setListBill(List<Bill> listBill) {
        this.listBill = listBill;
    }

    public void addBill(Bill bill) {
        this.listBill.add(bill);
    }

    public Boolean isEmpty() {
        return this.listBill.isEmpty();
    }
}   
