/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.roles;

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

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlRoles", urlPatterns = {"/roles", "/editar-rol", "/agregar-rol", "/borrar-rol"})
public class CtlRoles extends HttpServlet {

    @EJB
    private RolFacade rF;

    @EJB
    private AsociadoFacade aF;

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
            Rol rol = new Rol();
            List<Rol> roles;            
            switch (url) {
                case "/roles":                    
                    roles = rF.findAll();                    
                    request.setAttribute("roles", roles);                    
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/roles/roles.jsp").forward(request, response);
                    break;
                case "/agregar-rol":
                    request.setAttribute("titulo", "Agregar rol");
                    request.setAttribute("action", "agregar-rol");
                    getServletContext().getRequestDispatcher("/WEB-INF/views/roles/form.jsp").forward(request, response);
                    break;
                case "/editar-rol":
                    rol = rF.find(Integer.parseInt(request.getParameter("i")));
                    request.setAttribute("titulo", "Editar rol " + rol.getNombre());
                    request.setAttribute("action", "editar-rol");
                    request.setAttribute("rol", rol);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/roles/form.jsp").forward(request, response);
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
        Rol rol = new Rol();        
        Asociado asociado = new Asociado();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-rol":
                try {
                    Rol duplicate = rF.findDuplicate(request.getParameter("nombreRol"));                
                    if (duplicate.getNombre().equals("disponible")) {
                        rol.setIdRol(0);
                        rol.setNombre(request.getParameter("nombreRol"));                                                                   
                            rF.Insert(rol);                           
                            out.print("Registro correcto");                            
                    } else {
                        out.print("Rol no disponible");                                              
                    }
                   } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/agregar-rol");
                }
                break;
            case "/editar-rol":
                try {
                    rol = rF.find(Integer.parseInt(request.getParameter("idRol")));
                    Rol duplicate = rF.findDuplicateUpdate(request.getParameter("nombreRol"), rol.getNombre());                    
                    if (duplicate.getNombre().equals("disponible")) {
                        rol.setNombre(request.getParameter("nombreRol"));
                        rF.Update(rol);                        
                        out.print("Registro correcto");                    
                    } else {
                        out.print("Rol no disponible");
                    }                     
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/roles");
                }
                break;
            case "/borrar-rol":
                try {
                    rol = rF.find((Integer.parseInt(request.getParameter("idRol"))));
                    asociado = aF.findRol(Integer.parseInt(request.getParameter("idRol")));
                    if (asociado.getUsuario().equals("disponible")) {
                        rF.remove(rol);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, el rol contiene usuarios ligados");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/roles");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/roles");
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
