/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "LoadUpdateListServlet", urlPatterns = {"/LoadUpdateListServlet"})
public class LoadUpdateListServlet extends HttpServlet {

    private final String LIST_PAGE_TO_UPDATE = "update.jsp";
    private final Logger LOG = Logger.getLogger(LoadUpdateListServlet.class);

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
        String current = request.getParameter("currentPage");
        ServletContext context = request.getServletContext();
        Map<String, String> siteMap = (Map) context.getAttribute("MAP");
        String url = siteMap.get("loadUpdateList");
        try {
            int currentPage;
            if (current == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(current);
            }
            ProductDAO productDAO = new ProductDAO();
            productDAO.countTotalPage();
            int totalPage = productDAO.getTotalPage();
            request.setAttribute("TOTAL_PAGE", totalPage);

            productDAO.getListProductToUpdate(currentPage - 1);
            List<ProductDTO> list = productDAO.getList();
            request.setAttribute("LIST", list);

            url = LIST_PAGE_TO_UPDATE;

        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
            url = siteMap.get("loadUpdateList") + "?currentPage=1";
        } catch (NumberFormatException ex) {
            LOG.error("NumberFormatException : " + ex.getMessage());
            url = siteMap.get("loadUpdateList") + "?currentPage=1";
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
