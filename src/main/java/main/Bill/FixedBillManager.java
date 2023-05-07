package main.Bill;

import java.io.Serializable;
import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class FixedBillManager implements Serializable {
    private List<FixedBill> listFixedBill;

    public FixedBillManager() {
        this.listFixedBill = new ArrayList<>();
    }

    @XmlElement
    public List<FixedBill> getListFixedBill() {
        return this.listFixedBill;
    }

    public void setListFixedBill(List<FixedBill> listBill) {
        this.listFixedBill = listBill;
    }

    public void addFixedBill(FixedBill fixedbill) {
        this.listFixedBill.add(fixedbill);
    }
}  
