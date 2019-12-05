/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.clientes;

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
import model.entities.Fiscal;
import model.entities.Sucursal;
import model.sessions.ClienteFacade;
import model.sessions.FiscalFacade;
import model.sessions.SucursalFacade;
/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlClientes", urlPatterns = {"/clientes", "/editar-cliente", "/agregar-cliente", "/borrar-cliente"})
public class CtlClientes extends HttpServlet {

    @EJB
    private SucursalFacade sF;

    @EJB
    private ClienteFacade cF;

    @EJB
    private FiscalFacade fF;

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
            Cliente cliente;
            List<Fiscal> fiscales;
            List<Cliente> clientes;
            switch (url) {
                case "/clientes":                    
                    clientes = cF.findAll();
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/clients/clientes.jsp").forward(request, response);
                    break;
                case "/agregar-cliente":
                    fiscales = fF.findAll();
                    request.setAttribute("titulo", "Agregar cliente");
                    request.setAttribute("action", "agregar-cliente");
                    request.setAttribute("fiscales", fiscales);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/clients/form.jsp").forward(request, response);
                    break;
                case "/editar-cliente":
                    cliente = cF.find(Integer.parseInt(request.getParameter("i")));
                    fiscales = fF.findAll();
                    request.setAttribute("titulo", "Editar cliente " + cliente.getNombre());
                    request.setAttribute("action", "editar-cliente");
                    request.setAttribute("fiscales", fiscales);
                    request.setAttribute("cliente", cliente);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/clients/form.jsp").forward(request, response);
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
        Cliente cliente = new Cliente();
        Sucursal sucursal = new Sucursal();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-cliente":
                try {                    
                    Cliente duplicate = cF.findDuplicate(request.getParameter("direccionCliente"));
                    if (duplicate.getDireccion().equals("disponible")) {
                        cliente.setIdClien(0);
                        cliente.setNombre(request.getParameter("nombreCliente"));
                        cliente.setDireccion(request.getParameter("direccionCliente"));
                        cliente.setTel(request.getParameter("telefono"));
                        cliente.setIdFis(fF.find(Integer.parseInt(request.getParameter("idFis"))));
                        cF.Insert(cliente);                        
                        out.print("Registro correcto");                        
                    } else {
                        out.print("Domicilio no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                      response.sendRedirect("http://localhost:30533/Maar/agregar-cliente");
                }
                break;
            case "/editar-cliente":
                try {                    
                    cliente = cF.find(Integer.parseInt(request.getParameter("idClienSource")));                     
                    Cliente cli = cF.findDuplicateUpdate(request.getParameter("direccionCliente"), cliente.getDireccion());
                    if (cli.getDireccion().equals("disponible")) {
                        cliente.setNombre(request.getParameter("nombreCliente"));
                        cliente.setDireccion(request.getParameter("nombreCliente"));
                        cliente.setTel(request.getParameter("telefono"));
                        cliente.setIdFis(fF.find(Integer.parseInt(request.getParameter("idFis"))));
                        cF.Update(cliente);
                        out.print("Registro correcto");
                    } else {
                        out.print("Domicilio no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/clientes");
                }
                break;
            case "/borrar-cliente":
                try {
                    cliente = cF.find((Integer.parseInt(request.getParameter("idClienSource"))));
                    sucursal = sF.findSucursal(Integer.parseInt(request.getParameter("idClienSource")));
                    if (sucursal.getNombre().equals("disponible")) {
                        cF.remove(cliente);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, el cliente contiene sucursales ligadas");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/clientes");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/clientes");
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
