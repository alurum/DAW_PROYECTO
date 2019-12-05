/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.productos;

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
import model.entities.Categoria;
import model.entities.Pedido;
import model.entities.Producto;
import model.sessions.CategoriaFacade;
import model.sessions.PedidoFacade;
import model.sessions.ProductoFacade;


/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlProductos", urlPatterns = {"/productos", "/editar-producto", "/agregar-producto", "/borrar-producto"})
public class CtlProductos extends HttpServlet {

    @EJB
    private ProductoFacade pF;

    @EJB
    private PedidoFacade pedF;
    
    @EJB
    private CategoriaFacade cF;
    

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
            Producto producto;
            List<Producto> productos;
            List<Categoria> categorias;
            switch (url) {
                case "/productos":                    
                    productos = pF.findAll();
                    request.setAttribute("productos", productos);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/products/productos.jsp").forward(request, response);
                    break;
                case "/agregar-producto":
                    categorias = cF.findAll();
                    request.setAttribute("titulo", "Agregar producto");
                    request.setAttribute("action", "agregar-producto");
                    request.setAttribute("categorias", categorias);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/products/form.jsp").forward(request, response);
                    break;
                case "/editar-producto":
                    producto = pF.find(Integer.parseInt(request.getParameter("i")));
                    categorias = cF.findAll();
                    request.setAttribute("titulo", "Editar producto " + producto.getNombre());
                    request.setAttribute("action", "editar-producto");
                    request.setAttribute("categorias", categorias);
                    request.setAttribute("producto", producto);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/products/form.jsp").forward(request, response);
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
        Producto producto = new Producto();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-producto":
                try {                    
                    Producto duplicate = pF.findDuplicate(request.getParameter("nombreProducto"));
                    if (duplicate.getNombre().equals("disponible")) {
                        producto.setIdPro(0);
                        producto.setNombre(request.getParameter("nombreProducto"));
                        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                        producto.setSabor(request.getParameter("sabor"));
                        producto.setIdCat(cF.find(Integer.parseInt(request.getParameter("idCat"))));
                        pF.Insert(producto);                        
                        out.print("Registro correcto");                        
                    } else {
                        out.print("Nombre no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                      response.sendRedirect("http://localhost:30533/Maar/agregar-producto");
                }
                break;
            case "/editar-producto":
                try {                    
                    producto = pF.find(Integer.parseInt(request.getParameter("idPro")));                     
                    Producto pro = pF.findDuplicateUpdate(request.getParameter("nombreProducto"), producto.getNombre());
                    if (pro.getNombre().equals("disponible")) {
                        producto.setNombre(request.getParameter("nombreProducto"));
                        producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                        producto.setSabor(request.getParameter("sabor"));
                        producto.setIdCat(cF.find(Integer.parseInt(request.getParameter("idCat"))));
                        pF.Update(producto);
                        out.print("Registro correcto");
                    } else {
                        out.print("Nombre no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/productos");
                }
                break;
            case "/borrar-producto":
                try {
                    producto = pF.find((Integer.parseInt(request.getParameter("idPro"))));
                    pedido = pedF.findSucursal(Integer.parseInt(request.getParameter("idPro")));
                    if (pedido.getComentario().equals("disponible")) {
                        pF.remove(producto);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, el producto contiene pedidos ligados");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/productos");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/productos");
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
