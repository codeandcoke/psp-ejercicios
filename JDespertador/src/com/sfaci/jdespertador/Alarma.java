package com.sfaci.jdespertador;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Alarma extends SwingWorker<Void, Void> {

    private LocalTime fecha;
    private String mensaje;

    public Alarma(int hora, int minuto, String mensaje) {

        fecha = LocalTime.of(hora, minuto);
        this.mensaje = mensaje;
    }

    @Override
    protected Void doInBackground() throws Exception {

        while (ChronoUnit.SECONDS.between(LocalTime.now(), fecha) != 0) {

            Thread.sleep(1000);
        }

        firePropertyChange("alarma", null, mensaje);

        return null;
    }
}
