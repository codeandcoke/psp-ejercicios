package com.sfaci.jdespertador;

import javax.swing.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Alarma extends SwingWorker<Void, Void> {

    private LocalTime fecha;
    private String mensaje;
    private boolean cancelada;

    public Alarma(int hora, int minuto, String mensaje) {

        fecha = LocalTime.of(hora, minuto);
        this.mensaje = mensaje;
        cancelada = false;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    protected Void doInBackground() throws Exception {

        while (ChronoUnit.SECONDS.between(LocalTime.now(), fecha) != 0) {

            Thread.sleep(1000);

            if (isCancelled())
                cancelada = true;
                break;
        }

        if (!cancelada)
            firePropertyChange("alarma", null, mensaje);
        else
            firePropertyChange("alarma_cancelada", false, true);

        return null;
    }
}
