package com.sfaci.aviones;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by dam on 13/12/17.
 */
public class Avion extends Observable
    implements Observer {

    private int altura;
    private int velocidad;

    private List<Observer> observers;

    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers();
    }

    public void subir(int metros) {
        altura += metros;
        notifyObservers("subir");
    }

    public void bajar(int metros) {
        altura -= metros;
        notifyObservers("bajar");
    }

    public void acelerar(int cantidad) {
        velocidad += cantidad;
        notifyObservers("acelerar");
    }

    public void frenar(int cantidad) {
        velocidad -= cantidad;
        notifyObservers("frenar");
    }

    @Override
    public void update(Observable o, Object arg) {

        String cambio = (String) arg;
        switch(cambio) {
            case "frenar":
                break;
            // Más casos
        }
    }

    @Override
    public void notifyObservers(Object cambio) {

        for (Observer observer : observers)
            observer.update(this, cambio);
    }
}
