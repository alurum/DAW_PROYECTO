/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.categorias;


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
import model.entities.Producto;
import model.sessions.CategoriaFacade;
import model.sessions.ProductoFacade;


/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlCategorias", urlPatterns = {"/categorias", "/editar-categoria", "/agregar-categoria", "/borrar-categoria"})
public class CtlCategorias extends HttpServlet {

    @EJB
    private CategoriaFacade cF;

    @EJB
    private ProductoFacade pF;

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
            Categoria categoria = new Categoria();
            List<Categoria> categorias;            
            switch (url) {
                case "/categorias":                    
                    categorias = cF.findAll();                    
                    request.setAttribute("categorias", categorias);                    
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/categories/categorias.jsp").forward(request, response);
                    break;
                case "/agregar-categoria":
                    request.setAttribute("titulo", "Agregar categoria");
                    request.setAttribute("action", "agregar-categoria");
                    getServletContext().getRequestDispatcher("/WEB-INF/views/categories/form.jsp").forward(request, response);
                    break;
                case "/editar-categoria":
                    categoria = cF.find(Integer.parseInt(request.getParameter("i")));
                    request.setAttribute("titulo", "Editar categoria " + categoria.getNombre());
                    request.setAttribute("action", "editar-categoria");
                    request.setAttribute("categoria", categoria);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/categories/form.jsp").forward(request, response);
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
        Categoria categoria = new Categoria();        
        Producto producto = new Producto();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-categoria":
                try {
                    Categoria duplicate = cF.findDuplicate(request.getParameter("nombreCategoria"));                
                    if (duplicate.getNombre().equals("disponible")) {
                        categoria.setIdCat(0);
                        categoria.setNombre(request.getParameter("nombreCategoria"));                                                                   
                            cF.Insert(categoria);                           
                            out.print("Registro correcto");                            
                    } else {
                        out.print("Nombre no disponible");                                              
                    }
                   } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/agregar-categoria");
                }
                break;
            case "/editar-categoria":
                try {
                    categoria = cF.find(Integer.parseInt(request.getParameter("idCatSource")));
                    Categoria duplicate = cF.findDuplicateUpdate(request.getParameter("nombreCategoria"), categoria.getNombre());                    
                    if (duplicate.getNombre().equals("disponible")) {
                        categoria.setNombre(request.getParameter("nombreCategoria"));
                        cF.Update(categoria);                        
                        out.print("Registro correcto");                    
                    } else {
                        out.print("Nombre no disponible");
                    }                     
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/categorias");
                }
                break;
            case "/borrar-categoria":
                try {
                    categoria = cF.find((Integer.parseInt(request.getParameter("idCatSource"))));
                    producto = pF.findCategoria(Integer.parseInt(request.getParameter("idCatSource")));
                    if (producto.getNombre().equals("disponible")) {
                        cF.remove(categoria);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, productos ligados");
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
