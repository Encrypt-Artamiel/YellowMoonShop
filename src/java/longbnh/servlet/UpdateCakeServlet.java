/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import longbnh.product.CreateCakeError;
import longbnh.product.ProductDAO;
import longbnh.record.RecordDAO;
import longbnh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateCakeServlet", urlPatterns = {"/UpdateCakeServlet"})
public class UpdateCakeServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(UpdateCakeServlet.class);

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
        String image = request.getParameter("locationImage");
        String name = request.getParameter("txtCakeName");
        String create = request.getParameter("txtCreateDate");
        String expired = request.getParameter("txtExpiredDate");
        CreateCakeError error = new CreateCakeError();
        boolean foundErr = false;
        ServletContext context = request.getServletContext();
        Map<String, String> siteMap = (Map) context.getAttribute("MAP");
        String url = siteMap.get("updatePage");
        String proID = request.getParameter("txtProductID");
        try {
            if (name.trim().length() <= 0 || name.trim().length() > 50) {
                error.setInvalidName("The cake's name must contain from 3 to 50 letters");
                foundErr = true;
            }
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            int productID = Integer.parseInt(proID);
            int statusID = Integer.parseInt(request.getParameter("status"));

            if (price < 0) {
                error.setInvalidPrice("Price must be positive number");
                foundErr = true;
            }

            if (quantity < 0) {
                error.setInvalidQuantity("Quantity must be positive number");
                foundErr = true;
            }

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Timestamp ts = new Timestamp(format.parse(create).getTime());
            Date createDate = new Date(ts.getTime());

            ts = new Timestamp(format.parse(expired).getTime());
            Date expiredDate = new Date(ts.getTime());
            if (createDate.after(expiredDate) || createDate.equals(expiredDate)) {
                error.setInvalidDate("Create date must before the expired date");
                foundErr = true;
            }

            if (!foundErr) {
                ProductDAO productDAO = new ProductDAO();
                boolean result = productDAO.updateCake(name, image, price, categoryID, quantity, createDate, expiredDate, statusID, productID);
                if (result) {
                    HttpSession session = request.getSession(false);
                    UsersDTO user = (UsersDTO) session.getAttribute("USER");
                    String userID = user.getUserID();
                    Date localDate = Date.valueOf(LocalDate.now());

                    RecordDAO recoredDAO = new RecordDAO();
                    recoredDAO.recordUpdate(userID, productID, localDate);

                    url = siteMap.get("adminPage");
                }
            } else {
                request.setAttribute("ERR", error);
                request.setAttribute("PRODUCTID", proID);
            }

        } catch (NumberFormatException ex) {
            error.setInvalidInput("Please enter the valid input");
            request.setAttribute("ERR", error);
            request.setAttribute("PRODUCTID", proID);
            foundErr = true;
            LOG.error("NumberFormatException : " + ex.getMessage());
        } catch (ParseException ex) {
            error.setInvalidDate("Invalid date");
            request.setAttribute("ERR", error);
            request.setAttribute("PRODUCTID", proID);
            foundErr = true;
            LOG.error("ParseException : " + ex.getMessage());
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
        } finally {
            if (foundErr) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }
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
