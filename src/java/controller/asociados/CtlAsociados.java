/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.asociados;

import static controller.CtlLogin.getMD5;
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
import model.entities.Rol;
import model.sessions.AsociadoFacade;
import model.sessions.RolFacade;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlAsociados", urlPatterns = {"/asociados", "/editar-asociado", "/agregar-asociado", "/borrar-asociado", "/profile"})
public class CtlAsociados extends HttpServlet {

    @EJB
    private AsociadoFacade aF;
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
            Asociado asociado;
            List<Rol> roles;
            switch (url) {
                case "/asociados":                       
                    List<Asociado> asociados = aF.findAllExcept((String) session.getAttribute("SSusuario"));
                    request.setAttribute("asociados", asociados);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/usuarios.jsp").forward(request, response);
                    break;
                case "/agregar-asociado":
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Agregar usuario");
                    request.setAttribute("action", "agregar-asociado");
                    request.setAttribute("roles", roles);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/form.jsp").forward(request, response);
                    break;
                case "/editar-asociado":
                    asociado = aF.findByUsuario(request.getParameter("i"));
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Editar usuario " + asociado.getUsuario());
                    request.setAttribute("action", "editar-asociado");
                    request.setAttribute("roles", roles);
                    request.setAttribute("asociado", asociado);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/form.jsp").forward(request, response);
                    break;
                case "/profile":
                    request.setAttribute("titulo", "Editar usuario " + session.getAttribute("SSusuario"));
                    request.setAttribute("SSidAso", session.getAttribute("SSidAso"));
                    request.setAttribute("SSusuario", session.getAttribute("SSusuario"));
                    request.setAttribute("SScontraseña", session.getAttribute("SScontraseña"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/profile.jsp").forward(request, response);
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
        Asociado asociado = new Asociado();
        Rol rol = new Rol();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-asociado":
                try {
                    if (request.getParameter("password").equals(request.getParameter("Rpassword"))) {                        
                        Asociado duplicate = aF.findDuplicate(request.getParameter("usuario"));
                        if (duplicate.getUsuario().equals("disponible")) {
                            asociado.setIdAso(0);
                            asociado.setNombre(request.getParameter("nombreUsuario"));
                            asociado.setSalario(Double.parseDouble(request.getParameter("salario") + ".0"));
                            asociado.setCelular(request.getParameter("celular"));
                            asociado.setDireccion(request.getParameter("direccionAsociado"));
                            asociado.setUsuario(request.getParameter("usuario"));
                            asociado.setContraseña(getMD5(request.getParameter("password")));
                            asociado.setIdRol(rF.find(Integer.parseInt(request.getParameter("idRol"))));
                            aF.Insert(asociado);
                            out.print("Registro correcto");                               
                        } else {
                            out.print("Usuario no disponible");                                                      
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/agregar-asociado");
                }
                break;
            case "/editar-asociado":
                try {                        
                    Asociado aso = aF.find(parseInt(request.getParameter("idAso")));
                    if ((aso.getContraseña().equals(getMD5(request.getParameter("password"))) && aso.getContraseña().equals(getMD5(request.getParameter("Rpassword")))) || (request.getParameter("password").equals(request.getParameter("Rpassword")))) {                        
                        Asociado  us = aF.findDuplicateUpdate(request.getParameter("usuario"), aso.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            if (aso.getContraseña().equals(getMD5(request.getParameter("password"))) && aso.getContraseña().equals(getMD5(request.getParameter("Rpassword")))) {
                                aso.setContraseña((request.getParameter("password")));
                            } else {
                                aso.setContraseña(getMD5(request.getParameter("password")));
                            }
                            aso.setNombre(request.getParameter("nombreUsuario"));
                            aso.setSalario(Double.parseDouble(request.getParameter("salario")));
                            aso.setCelular(request.getParameter("celular"));
                            aso.setDireccion(request.getParameter("direccionAsociado"));
                            aso.setUsuario(request.getParameter("usuario"));
                            aso.setIdRol(rF.find(Integer.parseInt(request.getParameter("idRol"))));
                            aF.Update(aso);
                            out.print("Registro correcto");
                        } else {
                            out.print("Usuario no disponible");
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/asociados");
                }
                break;
            case "/borrar-asociado":
                try {
                    
                    asociado = aF.findByUsuario(request.getParameter("usuario"));                                                            
                    
                    
                        aF.remove(asociado);
                        out.print("Registro correcto");                    
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/asociados");
                }
                break;
            case "/profile":
                try {
               Asociado     aso = aF.find(parseInt((request.getParameter("SSidAso"))));
                    if ((aso.getContraseña().equals(getMD5(request.getParameter("SSpassword"))) && aso.getContraseña().equals(getMD5(request.getParameter("SSRpassword")))) || (request.getParameter("SSpassword").equals(request.getParameter("SSRpassword")))) {
                        Asociado us = aF.findDuplicateUpdate(request.getParameter("SSusuario"), aso.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            if (aso.getContraseña().equals(request.getParameter("SSpassword")) && aso.getContraseña().equals(request.getParameter("SSRpassword"))) {
                                aso.setContraseña((request.getParameter("SSpassword")));
                            } else {
                                aso.setContraseña(getMD5(request.getParameter("SSpassword")));
                            }
                            aso.setUsuario(request.getParameter("SSusuario"));
                            aF.Update(asociado);
                            out.print("Registro correcto");
                        } else {
                            out.print("Usuario no disponible");
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/asociados");
                }
                break;

            default:
                response.sendRedirect("http://localhost:30533/Maar/asociados");

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
