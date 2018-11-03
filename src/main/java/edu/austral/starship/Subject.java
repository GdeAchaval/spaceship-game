package edu.austral.starship;


public interface Subject {
    void attach(Observer observer);

    void dettach(Observer observer);

    void notifyObservers();
}
