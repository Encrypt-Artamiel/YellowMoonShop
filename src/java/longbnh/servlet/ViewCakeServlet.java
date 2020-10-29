/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longbnh.product.ProductDAO;
import longbnh.product.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ViewCakeServlet", urlPatterns = {"/ViewCakeServlet"})
public class ViewCakeServlet extends HttpServlet {

    private final String VIEW_CAKE_PAGE = "viewcake.jsp";
    private final String ERROR_PAGE = "adminpage.jsp";
    private final Logger LOG = Logger.getLogger(ViewCakeServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Map<String, String> siteMap = (Map) context.getAttribute("MAP");
        String url = siteMap.get("loadUpdateList");
        try {
            int productID = Integer.parseInt(request.getParameter("txtProductID"));
            ProductDAO dao = new ProductDAO();
            boolean result = dao.getProductByID(productID);
            if (result) {
                ProductDTO product = dao.getProduct();
                request.setAttribute("PRODUCT", product);

                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String createDate = format.format(product.getCreateDate());
                request.setAttribute("CREATE_DATE", createDate);
                String expiredDate = format.format(product.getExpirationDate());
                request.setAttribute("EXPIRED_DATE", expiredDate);

                url = VIEW_CAKE_PAGE;
            }
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (NumberFormatException ex) {
            LOG.error("NumberFormatException : " + ex.getMessage());
            url = ERROR_PAGE;
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
