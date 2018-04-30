package br.com.casadocodigo.loja.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogConstants {

    public static final String LOG_INFO = "INFO";
    public static final String LOG_ERROR = "ERROR";
    public static final String LOG_WARNING = "WARNING";


    public static void getLogInfo(String msg){
        Logger.getLogger(LOG_INFO).log(Level.INFO,  String.format("%s:%s",
                LOG_INFO, msg));
    }

    public static void getLogError(String msg){
        Logger.getLogger(LOG_ERROR).log(Level.SEVERE, String.format("%s:%s",
                LOG_ERROR, msg));
    }


}
