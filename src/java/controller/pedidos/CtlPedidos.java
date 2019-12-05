/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.pedidos;

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
import model.entities.Producto;
import model.entities.Sucursal;
import model.entities.Venta;
import model.sessions.ClienteFacade;
import model.sessions.PedidoFacade;
import model.sessions.ProductoFacade;
import model.sessions.SucursalFacade;
import model.sessions.VentaFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlPedidos", urlPatterns = {"/pedidos", "/editar-pedido", "/agregar-pedido", "/borrar-pedido"})
public class CtlPedidos extends HttpServlet {

    @EJB
    private PedidoFacade pF;

    @EJB
    private ProductoFacade proF;

    @EJB
    private ClienteFacade cF;

    @EJB
    private SucursalFacade sF;

    @EJB
    private VentaFacade vF;

    String url = "";
    
    private int counter = 0;

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
            Pedido pedido;
            List<Pedido> pedidos;
            List<Producto> productos;
            List<Cliente> clientes;
            List<Sucursal> sucursales;            
            switch (url) {
                case "/pedidos": 
                   if (counter>0){
                        pF.remove(pF.getId());
                        counter = 0;} 
                    pedidos = pF.findAll();
                    request.setAttribute("pedidos", pedidos);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/orders/pedidos.jsp").forward(request, response);
                    break;
                case "/agregar-pedido":
                    productos = proF.findAll();
                    clientes = cF.findAll();
                    sucursales = sF.findAll();                    
                    request.setAttribute("titulo", "Agregar pedido");
                    request.setAttribute("action", "agregar-pedido");
                    request.setAttribute("productos", productos);
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("sucursales", sucursales);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/orders/form.jsp").forward(request, response);
                    break;
                case "/editar-pedido":
                    pedido = pF.find(Integer.parseInt(request.getParameter("i")));
                    productos = proF.findAll();
                    clientes = cF.findAll();
                    sucursales = sF.findAll();
                    request.setAttribute("titulo", "Editar pedido " + pedido.getIdPed());
                    request.setAttribute("action", "editar-pedido");
                    request.setAttribute("productos", productos);
                    request.setAttribute("pedido", pedido);
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("sucursales", sucursales);                    
                    getServletContext().getRequestDispatcher("/WEB-INF/views/orders/form.jsp").forward(request, response);
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
        Venta venta = new Venta();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-pedido":
                try {                    
                        pedido.setIdPed(0);                        
                        pedido.setIdPro(proF.find(Integer.parseInt(request.getParameter("idProPed"))));
                        pedido.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                        pedido.setDireccion(request.getParameter("direccionPedido"));                        
                        pedido.setComentario(request.getParameter("comentario"));                        
                        pedido.setIdClien(cF.find(Integer.parseInt(request.getParameter("idClienPed"))));
                        pedido.setIdSuc(sF.find(Integer.parseInt(request.getParameter("idSucPed"))));
                        pF.Insert(pedido);                        
                        out.print("Registro correcto");  
                         counter = 1;
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                      response.sendRedirect("http://localhost:30533/Maar/agregar-pedido");
                }
                break;
            case "/editar-pedido":
                try {                    
                    pedido = pF.find(Integer.parseInt(request.getParameter("idPedSource")));                                         
                    
                        pedido.setIdPro(proF.find(Integer.parseInt(request.getParameter("idProPed"))));
                        pedido.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                        pedido.setDireccion(request.getParameter("direccionPedido"));                        
                        pedido.setComentario(request.getParameter("comentario"));                        
                        pedido.setIdClien(cF.find(Integer.parseInt(request.getParameter("idClienPed"))));
                        pedido.setIdSuc(sF.find(Integer.parseInt(request.getParameter("idSucPed"))));
                        
                        pF.Update(pedido);
                        out.print("Registro correcto");                    
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/pedidos");
                }
                break;
            case "/borrar-pedido":
                try {
                    pedido = pF.find((Integer.parseInt(request.getParameter("idPedSource"))));                   
                    venta = vF.findPedido(Integer.parseInt(request.getParameter("idPedSource")));
                    if (venta.getModoPago().equals("disponible")) {
                        pF.remove(pedido);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, el pedido contiene ventas ligadas");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/pedidos");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/pedidos");
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
