package main.Observer;

import java.util.*;

public class Observer {
    private List<Subscriber> listSubcriber;

    public Observer() {
        this.listSubcriber = new ArrayList<>();
    }

    public void subscribe(Subscriber subscriber) {
        this.listSubcriber.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        this.listSubcriber.remove(subscriber);
    }

    public void notifySubscriber() {
        this.listSubcriber.forEach(subscriber -> subscriber.update());
    }
}
