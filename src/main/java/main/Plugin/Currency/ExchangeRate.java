package main.Plugin.Currency;

import java.util.ArrayList;
import java.io.Serializable;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class ExchangeRate implements Serializable  {
    // ATTRIBUTES
    private ArrayList<Currency> listExchangeRate;

    // Constructor
    public ExchangeRate() {
        this.listExchangeRate = new ArrayList<Currency>();
    }

    public ExchangeRate(ArrayList<Currency> list) {
        this.listExchangeRate = list;
    }

    // SETTER-GETTER
    @XmlElement
    public ArrayList<Currency> getListExchangeRate() {
        return this.listExchangeRate;
    }
    
    public void setListExchangeRate(ArrayList<Currency> list) {
        this.listExchangeRate = list;
    }
}
