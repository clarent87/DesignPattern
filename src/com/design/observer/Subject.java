package com.design.observer;

public interface Subject { // observable
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
