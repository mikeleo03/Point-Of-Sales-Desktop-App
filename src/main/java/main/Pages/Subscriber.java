package main.Pages;

public interface Subscriber<T> {
    <U> U update(String command, T t);
}
