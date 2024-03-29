/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entities.Asociado;
import model.entities.Rol;
import model.sessions.AsociadoFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtllLogin", urlPatterns = {"/entrar", "/registrar", "/logout"})
public class CtlLogin extends HttpServlet {

    private String url = "";
    @EJB
    private AsociadoFacade uF;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
             url = request.getServletPath();    
              if (url.equals("/logout")) {                                 
             HttpSession session = request.getSession(false);
             session.invalidate();
             response.sendRedirect("http://localhost:30533/Maar/");             
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
        url = request.getServletPath();
        Asociado usr = new Asociado();
        Rol rol = new Rol();
        response.setContentType("text/html;charset=UTF-8");
        if (url.equals("/entrar")) {
            String usuario = request.getParameter("usuario");
            String contraseña = getMD5(request.getParameter("contraseña"));
            try {
                usr = uF.findByUsuario(usuario);
                String resultado = "";
                RequestDispatcher rd;
                if (usr.getUsuario().equals(usuario) && usr.getContraseña().equals(contraseña)) {
                    resultado = "Datos correctos";
                    HttpSession session = request.getSession();    
                    session.setAttribute("SSidAso", usr.getIdAso());                    
                    session.setAttribute("SSnombre", usr.getNombre());                    
                    session.setAttribute("SSsalario", usr.getSalario());                    
                    session.setAttribute("SScelular", usr.getCelular());                    
                    session.setAttribute("SSdireccion", usr.getDireccion());                    
                    session.setAttribute("SSusuario", usr.getUsuario());                    
                    session.setAttribute("SScontraseña", usr.getContraseña());
                    session.setAttribute("SSidRol", usr.getIdRol());                    
                } else {
                    resultado = "Error";
                }                
                try (PrintWriter out = response.getWriter()) {
                    out.print(resultado);
                }
            } catch (Exception ex) {
                Logger.getLogger(CtlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (url.equals("/registrar")) {            
            rol.setIdRol(2);
            rol.setNombre("repartidor");
            String contraseña = getMD5(request.getParameter("Rcontraseña1"));
            try {
                String resultado = "Error";                
                if (request.getParameter("Rcontraseña1").equals(request.getParameter("Rcontraseña2"))) {                    
                    Asociado us = uF.findDuplicate(request.getParameter("Rusuario"));                   
                    if (us.getUsuario().equals("disponible")) {
                        usr.setIdAso(0);
                        usr.setNombre(request.getParameter("nombre"));
                        usr.setSalario(0.00);
                        usr.setCelular(request.getParameter("celular"));
                        usr.setDireccion(request.getParameter("direccion"));
                        usr.setUsuario(request.getParameter("Rusuario"));
                        usr.setContraseña(contraseña);
                        usr.setIdRol(rol);
                        uF.create(usr);
                        resultado = "Registro correcto";
                    } else {
                        resultado = "Usuario no disponible";
                    }
                } else {
                    resultado = "Contraseñas diferentes";
                }
                request.setAttribute("resultado", resultado);
                try (PrintWriter out = response.getWriter()) {
                    out.print(resultado);
                }
            } catch (Exception ex) {
                Logger.getLogger(CtlLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
                      
                     
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
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
