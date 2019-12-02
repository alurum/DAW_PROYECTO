/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sucursales;

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
import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Sucursal;
import model.sessions.ClienteFacade;
import model.sessions.PedidoFacade;
import model.sessions.SucursalFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlSucursales", urlPatterns = {"/sucursales", "/editar-sucursal", "/agregar-sucursal", "/borrar-sucursal"})
public class CtlSucursales extends HttpServlet {

    @EJB
    private SucursalFacade sF;

    @EJB
    private ClienteFacade cF;

    @EJB
    private PedidoFacade pF;

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
            Sucursal sucursal = new Sucursal();
            List<Sucursal> sucursales;
            List<Cliente> clientes;
            switch (url) {
                case "/sucursales":
                    sucursales = sF.findAll();
                    request.setAttribute("sucursales", sucursales);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sucursales/sucursales.jsp").forward(request, response);
                    break;
                case "/agregar-sucursal":
                    clientes = cF.findAll();
                    request.setAttribute("titulo", "Agregar sucursal");
                    request.setAttribute("action", "agregar-sucursal");
                    request.setAttribute("clientes", clientes);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sucursales/form.jsp").forward(request, response);
                    break;
                case "/editar-sucursal":
                    sucursal = sF.find(Integer.parseInt(request.getParameter("i")));
                    clientes = cF.findAll();
                    request.setAttribute("titulo", "Editar sucursal " + sucursal.getNombre());
                    request.setAttribute("action", "editar-sucursal");
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("dato", sucursal);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sucursales/form.jsp").forward(request, response);
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
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        Sucursal sucursal = new Sucursal();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-sucursal":
                try {
                    Sucursal suc = new Sucursal();
                    suc = sF.findDuplicate(request.getParameter("direccion"));
                    if (suc.getNombre().equals("disponible")) {
                        sucursal.setIdSuc(0);
                        sucursal.setNombre(request.getParameter("nombre"));
                        sucursal.setDireccion(request.getParameter("direccion"));
                        sucursal.setNotienda(request.getParameter("notienda"));
                        sucursal.setIdClien(cF.find(Integer.parseInt(request.getParameter("idClien"))));
                        sF.create(sucursal);
                        out.print("Registro correcto");
                    } else {
                        out.print("Dirección de sucursal no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/agregar-sucursal");
                }
                break;
            case "/editar-sucursal":
                try {
                    cliente = cF.find(Integer.parseInt(request.getParameter("idClien")));
                    sucursal = sF.find(Integer.parseInt(request.getParameter("idSuc")));
                    Sucursal suc = new Sucursal();
                    suc = sF.findDuplicateUpdate(request.getParameter("direccion"), sucursal.getDireccion());
                    if (suc.getDireccion().equals("disponible")) {
                        sucursal.setNombre(request.getParameter("nombre"));
                        sucursal.setDireccion(request.getParameter("direccion"));
                        sucursal.setNotienda(request.getParameter("notienda"));
                        sucursal.setIdClien(cliente);
                        sF.edit(sucursal);
                        out.print("Registro correcto");
                    } else {
                        out.print("Dirección de sucursal no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/editar-sucursal");
                }
                break;
            case "/borrar-sucursal":
                try {
                    sucursal = sF.find((Integer.parseInt(request.getParameter("idSuc"))));
                    pedido = pF.findSucursal(Integer.parseInt(request.getParameter("idSuc")));
                    if (pedido.getComentario().equals("disponible")) {
                        sF.remove(sucursal);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, esta sucursal contiene pedidos ligados");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/sucursales");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/sucursales");
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
