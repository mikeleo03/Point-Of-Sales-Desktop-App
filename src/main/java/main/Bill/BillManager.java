package main.Bill;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;

import main.Observer.*;

@XmlRootElement
public class BillManager implements Serializable {
    private List<Bill> listBill;

    @JsonIgnore
    public transient Observer observer;

    public BillManager() {
        this.listBill = new ArrayList<>();
        this.observer = new Observer();
    }

    @XmlElement
    public List<Bill> getListBill() {
        return this.listBill;
    }

    public void setListBill(List<Bill> listBill) {
        this.listBill = listBill;
        this.observer.notifySubscriber();
    }

    public void addBill(Bill bill) {
        this.listBill.add(bill);
        this.observer.notifySubscriber();
    }

    @JsonIgnore
    public Boolean isEmpty() {
        return this.listBill.isEmpty();
    }
}   
