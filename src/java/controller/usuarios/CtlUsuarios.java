/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import controller.CtlLogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entities.Asociado;
import model.sessions.AsociadoFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlUsuarios", urlPatterns = {"/usuarios", "/editar-usuario", "/agregar-usuario", "/borrar-usuario"})
public class CtlUsuarios extends HttpServlet {

    @EJB
    private AsociadoFacade uF;

    String url = "";

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
        HttpSession session = request.getSession(false);
        if (session != null){
        url = request.getServletPath();        
        Asociado usuario;        
        switch (url) {
            case "/usuarios":
                List<Asociado> asociados = uF.findAll();                
                request.setAttribute("datos", asociados);
                request.setAttribute("usuario", session.getAttribute("usuario"));
                getServletContext().getRequestDispatcher("/WEB-INF/views/usuarios.jsp").forward(request, response);
                break;
            case "/editar-usuario":                
                usuario = uF.findByUsuario(request.getParameter("i"));    
                usuario.setContraseña(usuario.getContraseña());
                request.setAttribute("titulo", "Editar usuario " + usuario.getNombre());
                request.setAttribute("action", "editar-usuario");
                request.setAttribute("dato", usuario);
                getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
                break;
            case "/agregar-usuario":
                request.setAttribute("titulo", "Agregar usuario");
                request.setAttribute("action", "agregar-usuario");
                getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
                break;                                

        }
        } else {        
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
        PrintWriter out = response.getWriter();
        Asociado asociado = null;
        try {
            if (url.equals("/editar-usuario")) {
                int id = Integer.parseInt(request.getParameter("idUsuario"));
                asociado = uF.find(id);
                if (!asociado.getContraseña().equals(request.getParameter("repassword"))) {
                    out.println("Passwords diferentes");
                } else {
                    asociado.setUsuario(request.getParameter("usuario"));
                    asociado.setContraseña(CtlLogin.getMD5(request.getParameter("password")));                    
                    uF.edit(asociado);
                    request.setAttribute("resultado", "Actualizacion exitosa");
                    out.print("Actualizacion exitosa");
                    response.sendRedirect("usuarios");
                }
            }
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }

        try {
            if (url.equals("/agregar-usuario")) {

                asociado = new Asociado();
                if (!request.getParameter("password").equals(request.getParameter("repassword"))) {
                    out.println("Passwords diferentes");
                } else {
                    //usuario.setIdUsuario(2);
                    asociado.setUsuario(request.getParameter("usuario"));
                    asociado.setContraseña(CtlLogin.getMD5(request.getParameter("password")));                    
                    uF.create(asociado);
                    //request.setAttribute("resultado", "Insercion exitosa");
                    out.println("Registro exitoso");
                    response.sendRedirect("usuarios");
                }
            }
        } catch (Exception ex) {
            out.print(ex.getMessage());
        }

        if (url.equals("/borrar-usuario")) {
            asociado = uF.findByUsuario(request.getParameter("usuario"));           
            uF.remove(asociado);                         
            request.setAttribute("resultado", "Actualizacion exitosa");
                       

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
