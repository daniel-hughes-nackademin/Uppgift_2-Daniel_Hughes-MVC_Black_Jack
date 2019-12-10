package Interfaces;

public interface Subject {

    void setObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyUpdate(Object object);
}
