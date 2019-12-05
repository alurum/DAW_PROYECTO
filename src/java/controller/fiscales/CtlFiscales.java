/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fiscales;


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
import model.sessions.ClienteFacade;
import model.sessions.FiscalFacade;

/**
 *
 * @author Lalo
 */
@WebServlet(name = "CtlFiscales", urlPatterns = {"/fiscales", "/editar-fiscal", "/agregar-fiscal", "/borrar-fiscal"})
public class CtlFiscales extends HttpServlet {

    
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
            Fiscal fiscal;            
            List<Fiscal> fiscales;
            switch (url) {
                case "/fiscales":                    
                    fiscales = fF.findAll();
                    request.setAttribute("fiscales", fiscales);
                    request.setAttribute("usuario", session.getAttribute("SSusuario"));
                    getServletContext().getRequestDispatcher("/WEB-INF/views/fiscals/fiscales.jsp").forward(request, response);
                    break;
                case "/agregar-fiscal":                    
                    request.setAttribute("titulo", "Agregar fiscal");
                    request.setAttribute("action", "agregar-fiscal");                    
                    getServletContext().getRequestDispatcher("/WEB-INF/views/fiscals/form.jsp").forward(request, response);
                    break;
                case "/editar-fiscal":
                    fiscal = fF.find(Integer.parseInt(request.getParameter("i")));                    
                    request.setAttribute("titulo", "Editar fiscal " + fiscal.getDireccion());
                    request.setAttribute("action", "editar-fiscal");                    
                    request.setAttribute("fiscal", fiscal);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/fiscals/form.jsp").forward(request, response);
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
        Fiscal fiscal = new Fiscal();
        Cliente cliente = new Cliente();
        response.setContentType("text/html;charset=UTF-8");
        switch (url) {
            case "/agregar-fiscal":
                try {                    
                    Fiscal duplicate = fF.findDuplicate(request.getParameter("direccionFiscal"));
                    if (duplicate.getDireccion().equals("disponible")) {
                        fiscal.setIdFis(0);
                        fiscal.setNombre(request.getParameter("nombreFiscal"));
                        fiscal.setDireccion(request.getParameter("direccionFiscal"));
                        fiscal.setUsoCFCI(request.getParameter("usoCFCI"));
                        fiscal.setConPago(Integer.parseInt(request.getParameter("conPago")));                        
                        fiscal.setFormaDePago(request.getParameter("formaDePago"));
                        fF.Insert(fiscal);                        
                        out.print("Registro correcto");                        
                    } else {
                        out.print("Domicilio no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                      response.sendRedirect("http://localhost:30533/Maar/agregar-fiscal");
                }
                break;
            case "/editar-fiscal":
                try {                    
                    fiscal = fF.find(Integer.parseInt(request.getParameter("idFisSource")));                     
                    Fiscal fis = fF.findDuplicateUpdate(request.getParameter("direccionFiscal"), fiscal.getDireccion());
                    if (fis.getDireccion().equals("disponible")) {
                        fiscal.setNombre(request.getParameter("nombreFiscal"));
                        fiscal.setDireccion(request.getParameter("direccionFiscal"));
                        fiscal.setUsoCFCI(request.getParameter("usoCFCI"));
                        fiscal.setConPago(Integer.parseInt(request.getParameter("conPago")));
                        fiscal.setFormaDePago(request.getParameter("formaDePago"));
                        fF.Insert(fiscal);
                        out.print("Registro correcto");
                    } else {
                        out.print("Domicilio no disponible");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/fiscales");
                }
                break;
            case "/borrar-fiscal":
                try {
                    fiscal = fF.find((Integer.parseInt(request.getParameter("idFisSource"))));
                    cliente = cF.findFiscal(Integer.parseInt(request.getParameter("idFisSource")));
                    if (cliente.getNombre().equals("disponible")) {
                        fF.remove(fiscal);
                        out.print("Registro correcto");
                    } else {
                        out.print("Error, la fiscal contiene clientes ligados");
                    }
                } catch (Exception ex) {
                    System.out.println("xxxxxxxxxxxxxxxxSE HA PRODUCIDO EL SIGUIENTE ERROR:" + ex.getMessage() + "xxxxxxxxxxxxxxxx");
                    response.sendRedirect("http://localhost:30533/Maar/fiscales");
                }
                break;
            default:
                response.sendRedirect("http://localhost:30533/Maar/fiscales");
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
