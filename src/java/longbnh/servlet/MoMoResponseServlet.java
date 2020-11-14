/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longbnh.cart.CartObject;
import longbnh.orderdetail.OrderDetailDAO;
import longbnh.product.ProductDAO;
import longbnh.product.ProductDTO;
import longbnh.util.EncodeHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MoMoResponseServlet", urlPatterns = {"/MoMoResponseServlet"})
public class MoMoResponseServlet extends HttpServlet {

    private final String TRACKING_PAGE = "tracking.jsp";
    private final String VIEW_CART = "viewcart.jsp";
    private final Logger LOG = Logger.getLogger(MoMoResponseServlet.class);

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
        String requestId = request.getParameter("requestId");
        String orderID = request.getParameter("orderId");
        String errorCode = request.getParameter("errorCode");
        String transId = request.getParameter("transId");
        String amount = request.getParameter("amount");
        String message = request.getParameter("message");
        String localMessage = request.getParameter("localMessage");
        String responseTime = request.getParameter("responseTime");
        String payType = request.getParameter("payType");
        String extraData = request.getParameter("extraData");
        String signatureRes = request.getParameter("signature");
        String url = VIEW_CART;
        try {
            if (signatureRes != null) {
                String partnerCode = "MOMOYM7M20201113";
                String accessKey = "V8nq9GnGviGbZeI6";
                String signature = "partnerCode=" + partnerCode + "&accessKey=" + accessKey + "&requestId=" + requestId
                        + "&amount=" + amount + "&orderId=" + orderID + "&orderInfo=testMoMo&&orderType=momo_wallet&transId="
                        + transId + "&message=" + message + "&localMessage=" + localMessage + "&responseTime=" + responseTime
                        + "&errorCode=" + errorCode + "&payType=" + payType + "&extraData=" + extraData;
                String result = EncodeHelper.HMACSHA256(signature);
                if (result.equals(signatureRes)) {
                    System.out.println("ok");
                    HttpSession session = request.getSession(false);
                    CartObject cart = (CartObject) session.getAttribute("CART");
                    ProductDAO productDAO = new ProductDAO();
                    request.setAttribute("ORDER_ID", orderID);
                    request.setAttribute("TOTAL", cart.getTotal());
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    Map<Integer, ProductDTO> cakes = cart.getItems();
                    for (Map.Entry<Integer, ProductDTO> entry : cakes.entrySet()) {
                        Integer productID = entry.getKey();
                        ProductDTO cake = entry.getValue();

                        int quantity = cake.getQuantity();
                        float totalPrice = cake.getPrice() * quantity;
                        orderDetailDAO.addCakeToOrder(orderID, productID, quantity, totalPrice);
                        productDAO.updateQuantityAfterOrder(productID, quantity);
                    }
                    session.removeAttribute("CART");
                    url = TRACKING_PAGE;
                }
            }
        } catch (InvalidKeyException ex) {
            LOG.error("InvalidKeyException : " + ex.getMessage());
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("NoSuchAlgorithmException : " + ex.getMessage());
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
