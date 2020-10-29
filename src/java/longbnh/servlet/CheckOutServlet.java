/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import longbnh.cart.CartObject;
import longbnh.orderdetail.OrderDetailDAO;
import longbnh.orders.OrdersDAO;
import longbnh.product.ProductDAO;
import longbnh.product.ProductDTO;
import longbnh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String TRACKING_PAGE = "tracking.jsp";
    private final String VIEW_CART = "viewcart.jsp";
    private final Logger LOG = Logger.getLogger(CheckOutServlet.class);

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
        Map<String, String> siteMap = (Map)context.getAttribute("MAP");
        String url = siteMap.get("homePage");
        try {
            String userID = null;
            float total;
            HttpSession session = request.getSession(false);
            if (session != null) {
                UsersDTO userDTO = (UsersDTO) session.getAttribute("USER");
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (userDTO != null) {
                    userID = userDTO.getUserID();
                    url = TRACKING_PAGE;
                }
                if (cart != null) {
                    total = cart.getTotal();
                    String name = request.getParameter("txtName");
                    String phone = request.getParameter("txtPhoneNum");
                    String address = request.getParameter("txtAddress");
                    Date currentDate = Date.valueOf(LocalDate.now());

                    Map<Integer, ProductDTO> cakes = cart.getItems();
                    ProductDAO productDAO = new ProductDAO();
                    for (Map.Entry<Integer, ProductDTO> entry : cakes.entrySet()) {
                        Integer productID = entry.getKey();
                        ProductDTO cake = entry.getValue();
                        int quantity = cake.getQuantity();

                        boolean checkValid = productDAO.checkValidProduct(productID, quantity); //check status, quantity dư trong kho
                        if (!checkValid) {
                            String error = "Somthing wrong, this item has problem. Please remove it from your cart ";
                            request.setAttribute("ERROR", error);
                            request.setAttribute("ID_ERROR", productID);
                            url = VIEW_CART;
                            return;
                        }
                    }

                    int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                    OrdersDAO orderDAO = new OrdersDAO();
                    orderDAO.createOrder(userID, total, currentDate, name, phone, address, paymentID);

                    orderDAO.getLastOrderID(total, currentDate, name, phone, address);
                    String orderID = orderDAO.getOrderID();
                    request.setAttribute("ORDER_ID", orderID);
                    request.setAttribute("TOTAL", cart.getTotal());
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    
                    for (Map.Entry<Integer, ProductDTO> entry : cakes.entrySet()) {
                        Integer productID = entry.getKey();
                        ProductDTO cake = entry.getValue();

                        int quantity = cake.getQuantity();
                        float totalPrice = cake.getPrice() * quantity;
                        orderDetailDAO.addCakeToOrder(orderID, productID, quantity, totalPrice);
                        productDAO.updateQuantityAfterOrder(productID, quantity);
                    }
                    session.removeAttribute("CART");
                    
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
