/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ventas;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.entities.Asociado;
import model.entities.Cliente;
import model.entities.Pedido;
import model.entities.Venta;
import model.sessions.AsociadoFacade;
import model.sessions.ClienteFacade;
import model.sessions.PedidoFacade;
import model.sessions.VentaFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlVentas", urlPatterns = {"/ventas", "/editar-venta", "/agregar-venta", "/borrar-venta"})
public class CtlVentas extends HttpServlet {

    @EJB
    private VentaFacade vF;
          

    @EJB
    private ClienteFacade cF;

    @EJB
    private AsociadoFacade aF;

    @EJB
    private PedidoFacade pF;
    
    
   

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
            Venta venta;
            List<Venta> ventas;
            List<Cliente> clientes;
            List<Asociado> asociados;            
            List<Pedido> pedidos;            
            switch (url) {
                case "/ventas":                    
                    ventas = vF.findAll();
                    request.setAttribute("ventas", ventas);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sales/ventas.jsp").forward(request, response);
                    break;
                case "/agregar-venta":                    
                    clientes = cF.findAll();
                    asociados = aF.findAll();
                    pedidos = pF.findAll();                    
                    request.setAttribute("titulo", "Agregar venta");
                    request.setAttribute("action", "agregar-venta");
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("asociados", asociados);                    
                    request.setAttribute("pedidos", pedidos);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sales/form.jsp").forward(request, response);
                    break;
                case "/editar-venta":
                    venta = vF.find(Integer.parseInt(request.getParameter("i")));
                    clientes = cF.findAll();
                    asociados = aF.findAll();
                    pedidos = pF.findAll();                    
                    request.setAttribute("titulo", "Editar venta " + venta.getIdVen());
                    request.setAttribute("action", "editar-venta");                    
                    request.setAttribute("venta", venta);
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("asociados", asociados);                    
                    request.setAttribute("pedidos", pedidos);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/sales/form.jsp").forward(request, response);
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
        Venta venta = new Venta();        
        DateFormat format = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);        
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-venta":
                try {                    
                    Date date = format.parse(request.getParameter("fecha"));
                        venta.setIdVen(0);                        
                        venta.setDevolucione(Integer.parseInt(request.getParameter("devolucione")));
                        venta.setFecha(date);
                        venta.setModoPago(request.getParameter("modoPago"));                        
                        venta.setIdClien(cF.find(Integer.parseInt(request.getParameter("idClienVen"))));
                        venta.setIdAso(aF.find(Integer.parseInt(request.getParameter("idAsoVen"))));
                        venta.setIdPed(pF.find(Integer.parseInt(request.getParameter("idPedVen"))));
                        vF.Insert(venta);                        
                        out.print("Registro correcto"); 
                        response.sendRedirect("http://localhost:30533/Maar/ventas");
                         counter = 1;
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                      response.sendRedirect("http://localhost:30533/Maar/agregar-venta");
                }
                break;
            case "/editar-venta":
                try {                    
                    venta = vF.find(Integer.parseInt(request.getParameter("idVen")));                                         
                        Date date = format.parse(request.getParameter("fecha"));
                        venta.setDevolucione(Integer.parseInt(request.getParameter("devolucione")));
                        venta.setFecha(date);
                        venta.setModoPago(request.getParameter("modoPago"));                        
                        venta.setIdClien(cF.find(Integer.parseInt(request.getParameter("idClienVen"))));
                        venta.setIdAso(aF.find(Integer.parseInt(request.getParameter("idAsoVen"))));
                        venta.setIdPed(pF.find(Integer.parseInt(request.getParameter("idPedVen"))));                        
                        vF.Update(venta);
                        out.print("Registro correcto");  
                        response.sendRedirect("http://localhost:30533/Maar/ventas");
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/ventas");
                }
                break;
            case "/borrar-venta":
                try {
                    venta = vF.find((Integer.parseInt(request.getParameter("idVen"))));                                                           
                        vF.remove(venta);
                        out.print("Registro correcto");                    
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/ventas");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/ventas");
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
