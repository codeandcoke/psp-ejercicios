package org.sfsoft.descargaficheros;

import java.text.DecimalFormat;

/**
 * Created by dam on 9/11/16.
 */
public class Util {

    public static String pasarAMegas(float numeroBytes) {

        DecimalFormat df = new DecimalFormat("#,## MB 0.00");
        return df.format((float) numeroBytes / (1024 * 1024));
    }

    public static String pasarAMegasSegundo(float numeroBytesSegundo) {

        DecimalFormat df = new DecimalFormat("#,## MB/s 0.00");
        return df.format(numeroBytesSegundo / (1024 * 1024));
    }
}
