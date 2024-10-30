package nubyxextraservices.servlets;

import desktopframework.Configuracion;
import desktopframework.Log;
import desktopframework.web.Header;
import desktopframework.web.ServicioWeb;
import nubyxextraservices.servlets.constant.PropertiesVars;
import nubyxextraservices.servlets.dao.BlacklistDao;
import nubyxextraservices.servlets.services.BlacklistService;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ProcessingThread extends Thread {
    private final JSONObject requestBody;
    private final String servletName;
    private final String logHeader;
    private final String phoneAddress;
    private final BlacklistDao blacklistDao;
    private final BlacklistService blacklistService;

    public ProcessingThread(JSONObject requestBody, String phoneAddress, String logHeader, String servletName) {
        this.blacklistDao = new BlacklistDao();
        this.blacklistService = new BlacklistService(blacklistDao);
        this.phoneAddress = phoneAddress;
        this.requestBody = requestBody;
        this.servletName = servletName;
        this.logHeader = logHeader;
    }

    @Override
    public void run() {
        HttpResponse<String> refHttpResponse;
        List<Header> listaHeaders = new ArrayList();
        Log.info("Entrando al metodo processing c1 "+requestBody);
        listaHeaders.add(new Header("Content-Type", "application/json"));
        Log.info("Entrando al metodo processing c2 "+listaHeaders);
        int statusCode;
        Log.info("Entrando al metodo processing c3 ");
        if (PropertiesVars.getBLACKLIST_FLAG().equals("1")) {
            Log.info("Entrando al metodo processing c4 ");
            boolean isBlacklisted = blacklistService.isPhoneNumberBlacklisted(phoneAddress);
            Log.info("Entrando al metodo processing c5 ");
            try {
                Log.info("Entrando al metodo processing c6 ");
                if (!isBlacklisted) {//Phone number is not in blacklist
                    Log.info("Entrando al metodo processing c7 ");
                    try {
                        Log.info("Entrando al metodo processing c8 ");
                        String subject = requestBody.getString("subject");
                        String url;
                        Log.info("Prueba procesing v1 response "+subject);
                        switch (subject) {
                            case "Promperu_WA_UAT":
                                Log.info("Prueba procesing v2 response "+subject);
                                url = Configuracion.getValueString("ENDPOINT_PROMPTER");
                                Log.info("Prueba procesing v3 response "+url);
                                refHttpResponse = ServicioWeb.postWithHeaders(url, listaHeaders, requestBody.toString());
                                Log.info("Prueba procesing v33 response "+refHttpResponse);
                                statusCode = refHttpResponse.statusCode();
                                Log.info(logHeader + "INPUT: url = " + url, servletName);
                                break;
                            default:
                                break;
                        }
                    } catch (NullPointerException | JSONException ex) {
                        Log.error(logHeader, ex, servletName);
                    }
                } else {//Phone number is in blacklist
                    Log.info(logHeader + "Número de WhatsApp se encuentra en la blacklist: " + phoneAddress, servletName);
                }
            } catch (Exception ee) {
                Log.error(logHeader, ee, servletName);
            }
        } else {
            try {
                String subject = requestBody.getString("subject");
                String url;
                Log.info("Prueba procesing v4 response "+subject);
                switch (subject) {
                    case "Promperu_WA_UAT":
                        Log.info("Prueba procesing v5 response "+subject);
                        url = Configuracion.getValueString("ENDPOINT_PROMPTER");
                        Log.info("Prueba procesing v6 response "+url);
                        refHttpResponse = ServicioWeb.postWithHeaders(url, listaHeaders, requestBody.toString());
                        Log.info("Prueba procesing v7 response "+refHttpResponse);
                        statusCode = refHttpResponse.statusCode();
                        Log.info(logHeader + "INPUT: url = " + url, servletName);
                        break;
                    default:
                        break;
                }

            } catch (NullPointerException | JSONException ex) {
                Log.error(logHeader, ex, servletName);
            }
        }

        Log.info(logHeader + "***************** " + servletName + " -- Fin de Servicio ********************", servletName);
    }
}
