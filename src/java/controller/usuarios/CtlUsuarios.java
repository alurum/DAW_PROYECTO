/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import controller.CtlLogin;
import static controller.CtlLogin.getMD5;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entities.Asociado;
import model.entities.Rol;
import model.sessions.AsociadoFacade;
import model.sessions.RolFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlUsuarios", urlPatterns = {"/usuarios", "/editar-usuario", "/agregar-usuario", "/borrar-usuario", "/profile"})
public class CtlUsuarios extends HttpServlet {

    @EJB
    private AsociadoFacade uF;
    @EJB
    private RolFacade rF;

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
        if (session != null) {
            url = request.getServletPath();
            Asociado usuario;
            List<Rol> roles;
            switch (url) {
                case "/usuarios":
                    List<Asociado> asociados = uF.findAll();
                    request.setAttribute("datos", asociados);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/usuarios.jsp").forward(request, response);
                    break;
                case "/editar-usuario":
                    usuario = uF.findByUsuario(request.getParameter("i"));
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Editar usuario " + usuario.getNombre());
                    request.setAttribute("action", "editar-usuario");
                    request.setAttribute("roles", roles);
                    request.setAttribute("dato", usuario);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
                    break;
                case "/agregar-usuario":
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Agregar usuario");
                    request.setAttribute("action", "agregar-usuario");
                    request.setAttribute("roles", roles);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
                    break;
                case "/profile":
                    request.setAttribute("titulo", "Editar usuario " + session.getAttribute("SSusuario"));
                    request.setAttribute("SSusuario", session.getAttribute("SSusuario"));
                    request.setAttribute("SScontraseña", session.getAttribute("SScontraseña"));
                    request.setAttribute("SSidAso", session.getAttribute("SSidAso"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
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
        Asociado asociado = new Asociado();
        Rol rol = new Rol();
        String resultado = "Error";   
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-usuario":                
                try {
                rol = rF.find(Integer.parseInt(request.getParameter("idRol").trim()));                
                if (request.getParameter("password").equals(request.getParameter("Rpassword"))) {
                        Asociado us = uF.findDuplicate(request.getParameter("usuario"));
                        if (us.getUsuario().equals("disponible")) {
                            asociado.setIdAso(0);
                            asociado.setNombre(request.getParameter("nombre"));                            
                            asociado.setSalario(Double.parseDouble(request.getParameter("salario").trim() + ".0"));                                                                                 
                            asociado.setCelular(Integer.parseInt(request.getParameter("celular").trim()));
                            asociado.setDireccion(request.getParameter("direccion"));
                            asociado.setUsuario(request.getParameter("usuario"));
                            asociado.setContraseña(getMD5(request.getParameter("password")));
                            asociado.setIdRol(rol);
                            uF.create(asociado);
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
                break;

                
                
                
            case "/editar-usuario":
                rol = rF.find(Integer.parseInt(request.getParameter("idRol").trim()));
                try {
                    asociado = uF.find(parseInt((request.getParameter("idAso"))));
                    if (request.getParameter("password").equals(request.getParameter("Rpassword"))) {
                        Asociado us = uF.findDuplicateUpdate(request.getParameter("usuario"), asociado.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            asociado.setNombre(request.getParameter("nombre"));
                            asociado.setSalario(Double.parseDouble(request.getParameter("salario")));
                            asociado.setCelular(Integer.parseInt(request.getParameter("celular")));
                            asociado.setDireccion(request.getParameter("direccion"));
                            asociado.setUsuario(request.getParameter("usuario"));
                            asociado.setContraseña(getMD5(request.getParameter("password")));
                            asociado.setIdRol(rol);
                            uF.edit(asociado);
                            resultado = "Registro correcto";
                            response.sendRedirect("http://localhost:30533/Maar/usuarios");
                        } else {
                            resultado = "Usuario no disponible";
                        }
                    } else {
                        resultado = "Contraseñas diferentes";
                    }
                    request.setAttribute("resultado", resultado);
                } catch (Exception ex) {
                    Logger.getLogger(CtlLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "/borrar-usuario":
                asociado = uF.findByUsuario(request.getParameter("usuario"));
                uF.remove(asociado);
                resultado = "Registro correcto";                
                try (PrintWriter out = response.getWriter()) {
                    out.print(resultado);
                }
                break;
                
            case "/profile":
                 
                    asociado = uF.find(parseInt((request.getParameter("SSidAso"))));
                    if (asociado.getContraseña().equals(request.getParameter("SSpassword")) && asociado.getContraseña().equals(request.getParameter("SSRpassword"))  || (request.getParameter("SSpassword").equals(request.getParameter("SSRpassword")))   )  {
                    Asociado us = uF.findDuplicateUpdate(request.getParameter("SSusuario"), asociado.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            asociado.setUsuario(request.getParameter("SSusuario"));
                            asociado.setContraseña(getMD5(request.getParameter("SSpassword")));                            
                            uF.edit(asociado);
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
                
                
                break;
                
            
            default:
                    response.sendRedirect("http://localhost:30533/Maar/usuarios");

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
