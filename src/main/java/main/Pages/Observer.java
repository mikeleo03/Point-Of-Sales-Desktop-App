package main.Pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Observer<T> {
    Map <String, List<Subscriber<T>>> subscribers;

    public Observer(String... commands) {
        this.subscribers = new HashMap<>();
        for (String c : commands) {
            this.subscribers.put(c, new ArrayList<>());
        }
    }

    public void subscribe(String command, Subscriber<T> subscriber) {
        List<Subscriber<T>> users = this.subscribers.get(command);
        users.add(subscriber);
    }

    public void unsubscribe(String command, Subscriber<T> listener) {
        List<Subscriber<T>> users = this.subscribers.get(command);
        users.remove(listener);
    }

    public void notify(String command, T t) {
        List<Subscriber<T>> users = this.subscribers.get(command);
        for (Subscriber<T> sub : users) {
            sub.update(command, t);
        }
    }

    public <U> List<U> notifyWithResult(String command, T t) {
        List<Subscriber<T>> users = this.subscribers.get(command);
        ArrayList<U> result = new ArrayList<> ();
        for (Subscriber<T> sub : users) {
            result.add(sub.update(command, t));
        }
        return result;
    }
}
