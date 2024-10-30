/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nubyxextraservices.servlets;

import desktopframework.Aplicacion;
import desktopframework.ConexionBDConfiguracion;
import desktopframework.Configuracion;
import desktopframework.Log;
import desktopframework.web.Header;
import desktopframework.web.ServicioWeb;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Luis D
 */
public class mainWebhookStatusEndpoint extends HttpServlet {

    private final String SERVLET = "mainWebhookStatusEndpoint";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String SUBHEADER = "processRequest() - ";
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Aplicacion.directorio = "C:\\home\\luisInference\\nubyxextraservices"; //Ubicacion de directorio config AWS
        Configuracion.actualizar();
        ConexionBDConfiguracion.actualizar();
        Log.actualizar();

        List<Header> listaHeaders = new ArrayList();
        HttpResponse<String> refHttpResponse;
        listaHeaders.add(new Header("Content-Type", "application/json"));
        int statusCode;

        String uuid = UUID.randomUUID().toString() + "-";
        final String LOG_HEADER = uuid + SERVLET + SUBHEADER;

        Log.info(LOG_HEADER + "***************** " + SERVLET + " -- Inicio de Servicio ********************", SERVLET);
        Log.debug(LOG_HEADER + "ContextPath = " + request.getContextPath(), SERVLET);//Obtener contexto de URI
        Log.debug(LOG_HEADER + "Method = " + request.getMethod(), SERVLET);//Obtener metodo del request (Get o Post)
        Log.debug(LOG_HEADER + "QueryString = " + request.getQueryString(), SERVLET);
        Log.debug(LOG_HEADER + "PathInfo = " + request.getPathInfo(), SERVLET);

        JSONObject request_body = ServicioWeb.leerJsonDelRequest(request);
        Log.info(LOG_HEADER + "INPUT: body = " + request_body.toString(), SERVLET);

        try {
            String subject = request_body.getString("subject");
            String url;
            Log.info("Prueba endpoint 123 "+subject);
            if (subject.equals("Promperu_WA_UAT")) {
                Log.info("Prueba endpoint v1 " + subject);
                url = Configuracion.getValueString("ENDPOINT_STATUS_PROMPTER");
                Log.info("Prueba endpoint v2 " + url);
                refHttpResponse = ServicioWeb.postWithHeaders(url, listaHeaders, request_body.toString());
                Log.info("Prueba endpoint v2 response " + refHttpResponse);
                statusCode = refHttpResponse.statusCode();
                Log.info(LOG_HEADER + "INPUT: url = " + url, SERVLET);
                Log.info(LOG_HEADER + "OUTPUT: statusCode = " + statusCode, SERVLET);
                Log.info(LOG_HEADER + "OUTPUT: body = " + refHttpResponse.body(), SERVLET);
            }
        } catch (NullPointerException | JSONException ex) {
            Log.error(LOG_HEADER, ex, SERVLET);
        }
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        out.flush();
        Log.info(LOG_HEADER + "***************** " + SERVLET + " -- Fin de Servicio ********************", SERVLET);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        out.flush();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
