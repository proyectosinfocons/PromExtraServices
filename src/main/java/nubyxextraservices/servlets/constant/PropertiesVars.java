/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nubyxextraservices.servlets.constant;

import desktopframework.Configuracion;

/**
 *
 * @author Luis D
 */
public class PropertiesVars {

    private static String POSTGRESQL_URL;
    private static String POSTGRESQL_USER;
    private static String POSTGRESQL_PASSWORD;
    private static String BLACKLIST_FLAG;

    public static String getPOSTGRESQL_URL() {
        if (POSTGRESQL_URL == null) {
            POSTGRESQL_URL = Configuracion.getValueString("POSTGRESQL_URL");
        } else if (POSTGRESQL_URL.isEmpty()) {
            POSTGRESQL_URL = Configuracion.getValueString("POSTGRESQL_URL");
        } else {

        }
        return POSTGRESQL_URL;
    }

    public static String getPOSTGRESQL_USER() {
        if (POSTGRESQL_USER == null) {
            POSTGRESQL_USER = Configuracion.getValueString("POSTGRESQL_USER");
        } else if (POSTGRESQL_USER.isEmpty()) {
            POSTGRESQL_USER = Configuracion.getValueString("POSTGRESQL_USER");
        } else {

        }
        return POSTGRESQL_USER;
    }

    public static String getPOSTGRESQL_PASSWORD() {
        if (POSTGRESQL_PASSWORD == null) {
            POSTGRESQL_PASSWORD = Configuracion.getValueString("POSTGRESQL_PASSWORD");
        } else if (POSTGRESQL_PASSWORD.isEmpty()) {
            POSTGRESQL_PASSWORD = Configuracion.getValueString("POSTGRESQL_PASSWORD");
        } else {

        }
        return POSTGRESQL_PASSWORD;
    }

    public static String getBLACKLIST_FLAG() {
        if (BLACKLIST_FLAG == null) {
            BLACKLIST_FLAG = Configuracion.getValueString("BLACKLIST_FLAG");
        } else if (BLACKLIST_FLAG.isEmpty()) {
            BLACKLIST_FLAG = Configuracion.getValueString("BLACKLIST_FLAG");
        } else {
            BLACKLIST_FLAG = Configuracion.getValueString("BLACKLIST_FLAG");
        }
        BLACKLIST_FLAG = Configuracion.getValueString("BLACKLIST_FLAG");
        return BLACKLIST_FLAG;
    }
}
