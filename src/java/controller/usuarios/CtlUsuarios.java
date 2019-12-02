/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.usuarios;

import static controller.CtlLogin.getMD5;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
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
                    List<Asociado> asociados = uF.findAllExcept((String) session.getAttribute("SSusuario"));
                    request.setAttribute("datos", asociados);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/usuarios.jsp").forward(request, response);
                    break;
                case "/agregar-usuario":
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Agregar usuario");
                    request.setAttribute("action", "agregar-usuario");
                    request.setAttribute("roles", roles);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/users/form.jsp").forward(request, response);
                    break;
                case "/editar-usuario":
                    usuario = uF.findByUsuario(request.getParameter("i"));
                    roles = rF.findAll();
                    request.setAttribute("titulo", "Editar usuario " + usuario.getUsuario());
                    request.setAttribute("action", "editar-usuario");
                    request.setAttribute("roles", roles);
                    request.setAttribute("dato", usuario);
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
            case "/agregar-usuario":
                try {
                    if (request.getParameter("password").equals(request.getParameter("Rpassword"))) {
                        Asociado us = uF.findDuplicate(request.getParameter("usuario"));
                        if (us.getUsuario().equals("disponible")) {
                            asociado.setIdAso(0);
                            asociado.setNombre(request.getParameter("nombre"));
                            asociado.setSalario(Double.parseDouble(request.getParameter("salario") + ".0"));
                            asociado.setCelular(request.getParameter("celular").trim());
                            asociado.setDireccion(request.getParameter("direccion"));
                            asociado.setUsuario(request.getParameter("usuario"));
                            asociado.setContraseña(getMD5(request.getParameter("password")));
                            asociado.setIdRol(rF.find(Integer.parseInt(request.getParameter("idRol"))));
                            uF.create(asociado);
                            out.print("Registro correcto");
                        } else {
                            out.print("Usuario no disponible");
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                }
                break;
            case "/editar-usuario":
                try {
                    rol = rF.find(Integer.parseInt(request.getParameter("idRol")));
                    asociado = uF.find(parseInt((request.getParameter("idAso"))));
                    if ((asociado.getContraseña().equals(getMD5(request.getParameter("password"))) && asociado.getContraseña().equals(getMD5(request.getParameter("Rpassword")))) || (request.getParameter("password").equals(request.getParameter("Rpassword")))) {
                        Asociado us = uF.findDuplicateUpdate(request.getParameter("usuario"), asociado.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            if (asociado.getContraseña().equals(getMD5(request.getParameter("password"))) && asociado.getContraseña().equals(getMD5(request.getParameter("Rpassword")))) {
                                asociado.setContraseña((request.getParameter("password")));
                            } else {
                                asociado.setContraseña(getMD5(request.getParameter("password")));
                            }
                            asociado.setNombre(request.getParameter("nombre"));
                            asociado.setSalario(Double.parseDouble(request.getParameter("salario")));
                            asociado.setCelular(request.getParameter("celular"));
                            asociado.setDireccion(request.getParameter("direccion"));
                            asociado.setUsuario(request.getParameter("usuario"));
                            asociado.setIdRol(rol);
                            uF.edit(asociado);
                            out.print("Registro correcto");
                        } else {
                            out.print("Usuario no disponible");
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                }
                break;
            case "/borrar-usuario":
                try {
                    asociado = uF.findByUsuario(request.getParameter("usuario"));
                    if (asociado.getUsuario().equals(request.getParameter("SSusr"))) {
                        out.print("Error, usuario en uso!");
                    } else {
                        uF.remove(asociado);
                        out.print("Registro correcto");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                }
                break;
            case "/profile":
                try {
                    asociado = uF.find(parseInt((request.getParameter("SSidAso"))));
                    if ((asociado.getContraseña().equals(getMD5(request.getParameter("SSpassword"))) && asociado.getContraseña().equals(getMD5(request.getParameter("SSRpassword")))) || (request.getParameter("SSpassword").equals(request.getParameter("SSRpassword")))) {
                        Asociado us = uF.findDuplicateUpdate(request.getParameter("SSusuario"), asociado.getUsuario());
                        if (us.getUsuario().equals("disponible")) {
                            if (asociado.getContraseña().equals(request.getParameter("SSpassword")) && asociado.getContraseña().equals(request.getParameter("SSRpassword"))) {
                                asociado.setContraseña((request.getParameter("SSpassword")));
                            } else {
                                asociado.setContraseña(getMD5(request.getParameter("SSpassword")));
                            }
                            asociado.setUsuario(request.getParameter("SSusuario"));
                            uF.edit(asociado);
                            out.print("Registro correcto");
                        } else {
                            out.print("Usuario no disponible");
                        }
                    } else {
                        out.print("Passwords diferentes");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
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
