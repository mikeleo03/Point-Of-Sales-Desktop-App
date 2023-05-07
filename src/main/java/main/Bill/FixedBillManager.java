package main.Bill;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.*;

import main.Observer.*;

@XmlRootElement
public class FixedBillManager implements Serializable {
    private List<FixedBill> listFixedBill;

    @JsonIgnore
    public transient Observer observer;

    public FixedBillManager() {
        this.listFixedBill = new ArrayList<>();
        this.observer = new Observer();
    }

    @XmlElement
    public List<FixedBill> getListFixedBill() {
        return this.listFixedBill;
    }

    public void setListFixedBill(List<FixedBill> listBill) {
        this.listFixedBill = listBill;
        this.observer.notifySubscriber();
    }

    public void addFixedBill(FixedBill fixedbill) {
        this.listFixedBill.add(fixedbill);
        this.observer.notifySubscriber();
    }
}  
