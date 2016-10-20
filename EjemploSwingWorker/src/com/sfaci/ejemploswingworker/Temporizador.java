package com.sfaci.ejemploswingworker;

import javax.swing.*;

/**
 * Created by profesor on 05/10/2016.
 */
public class Temporizador extends SwingWorker<Void, Integer> {

    private long tiempo;
    private boolean terminado;
    private boolean pausado;

    public Temporizador(int tiempo) {

        this.tiempo = tiempo * 1000;
        terminado = false;
        pausado = false;
    }

    public void terminar() {
        terminado = true;
    }

    public void pausar() {
        pausado = true;
    }

    public void continuar() {
        pausado = false;
    }

    @Override
    protected Void doInBackground() throws Exception {

        int progreso = 0;
        long cuentaAtras = tiempo;
        long tiempoPasado = 0;

        while ((cuentaAtras > 0) && (!terminado)) {

            Thread.sleep(100);

            if (pausado)
                continue;

            cuentaAtras -= 100;
            tiempoPasado += 100;
            progreso = (int) (tiempoPasado * 100 / tiempo);
            setProgress(progreso);
        }

        firePropertyChange("fin", 0, 1);

        return null;
    }
}
