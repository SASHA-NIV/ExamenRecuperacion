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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author SASHA
 */
@WebServlet(name = "AgregarMedico", urlPatterns = {"/agregarmedico"})
public class AgregarMedico extends HttpServlet {

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
        try {
            Medico nuevo = new Medico();
            nuevo.setNdniMedi(request.getParameter("ndniMedi"));
            nuevo.setAppaMedi(request.getParameter("appaMedi"));
            nuevo.setApmaMedi(request.getParameter("apmaMedi"));
            nuevo.setNombMedi(request.getParameter("nombMedi"));

            String fechaStr = request.getParameter("fechNaciMedi");
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
            nuevo.setFechNaciMedi(fecha);

            String login = request.getParameter("logiMedi");
            String clave = request.getParameter("passMedi");

            nuevo.setLogiMedi(login);
            nuevo.setPassMedi(BCrypt.hashpw(clave, BCrypt.gensalt()));

            MedicoJpaController ctrl = new MedicoJpaController();
            ctrl.create(nuevo);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Médico registrado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Error al registrar médico.");
        }
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
