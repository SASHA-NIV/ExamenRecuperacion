/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.MedicoJpaController;
import dto.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SASHA
 */
@WebServlet(name = "ListarMedico", urlPatterns = {"/listarmedico"})
public class ListarMedico extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
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
        response.setContentType("application/json;charset=UTF-8");
        MedicoJpaController ctrl = new MedicoJpaController();
        List<Medico> medicos = ctrl.findMedicoEntities();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < medicos.size(); i++) {
            Medico m = medicos.get(i);
            json.append("{")
                .append("\"codiMedi\":").append(m.getCodiMedi()).append(",")
                .append("\"ndniMedi\":\"").append(m.getNdniMedi()).append("\",")
                .append("\"appaMedi\":\"").append(m.getAppaMedi()).append("\",")
                .append("\"apmaMedi\":\"").append(m.getApmaMedi()).append("\",")
                .append("\"nombMedi\":\"").append(m.getNombMedi()).append("\",")
                .append("\"fechNaciMedi\":\"").append(sdf.format(m.getFechNaciMedi())).append("\",")
                .append("\"logiMedi\":\"").append(m.getLogiMedi()).append("\"")
                .append("}");

            if (i < medicos.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        try (PrintWriter out = response.getWriter()) {
            out.print(json.toString());
        }
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
