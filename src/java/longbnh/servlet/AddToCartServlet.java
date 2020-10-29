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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longbnh.cart.CartObject;
import longbnh.product.ProductDAO;
import longbnh.product.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(AddToCartServlet.class);

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
        String url = siteMap.get("homePage");
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            int productID = Integer.parseInt(request.getParameter("txtProductID"));

            ProductDAO productDAO = new ProductDAO();

            Map<Integer, ProductDTO> cakeInCart = cart.getItems();
            int quantity = 1;
            if (cakeInCart != null) {
                if (cakeInCart.containsKey(productID)) {
                    quantity = cakeInCart.get(productID).getQuantity() + 1;
                }
            }
            boolean check = productDAO.checkValidProduct(productID, quantity);
            if (check) {
                productDAO.getProductByID(productID);
                ProductDTO cake = productDAO.getProduct();
                cake.setQuantity(1);

                cart.addCakeToCart(productID, cake);
                cart.caculateTotal();
            } else {
                cart.removeCake(productID);
            }
            session.setAttribute("CART", cart);

            String searchValue = request.getParameter("txtSearch");
            if (searchValue != null) {
                String price = request.getParameter("price");
                String categoryID = request.getParameter("categoryID");

                url = siteMap.get("search") + "?txtSearch=" + searchValue + "&price=" + price + "&categoryID=" + categoryID;
            }
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (NumberFormatException ex) {
            LOG.error("NumberFormatException : " + ex.getMessage());
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
