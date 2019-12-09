package Interfaces;

import Utility.GameUpdate;

public interface Subject {

    void setObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyUpdate(GameUpdate gameUpdate);
}
