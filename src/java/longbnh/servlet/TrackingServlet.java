/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longbnh.orderdetail.OrderDetailDAO;
import longbnh.orders.OrdersTrackingObject;
import longbnh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "TrackingServlet", urlPatterns = {"/TrackingServlet"})
public class TrackingServlet extends HttpServlet {

    private final String TRACKING_PAGE = "tracking.jsp";
    private final Logger LOG = Logger.getLogger(TrackingServlet.class);

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
        String url = TRACKING_PAGE;
        try {
            HttpSession session = request.getSession(false);
            UsersDTO user = (UsersDTO) session.getAttribute("USER");
            if (user == null) {
                ServletContext context = request.getServletContext();
                Map<String, String> siteMap = (Map) context.getAttribute("MAP");
                url = siteMap.get("homePage");
            } else {
                String orderID = request.getParameter("txtOrderID");
                request.setAttribute("ORDER_ID", orderID);
                if (orderID.trim().length() > 0) {
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    boolean result = orderDetailDAO.getTrackingOrder(orderID, user.getUserID());
                    if (result) {
                        OrdersTrackingObject object = orderDetailDAO.getOrderTracking();
                        request.setAttribute("TRACKING_OBJECT", object);
                    }
                }
            }
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
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
