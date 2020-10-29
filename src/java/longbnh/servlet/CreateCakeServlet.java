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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longbnh.product.CreateCakeError;
import longbnh.product.ProductDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CreateCakeServlet", urlPatterns = {"/CreateCakeServlet"})
public class CreateCakeServlet extends HttpServlet {

    private final String CREATE_CAKE_PAGE = "createcake.jsp";
    private final Logger LOG = Logger.getLogger(CreateCakeServlet.class);

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
        String url = CREATE_CAKE_PAGE;

        String imageLocation = request.getParameter("locationImage");
        String name = request.getParameter("txtCakeName");
        String description = request.getParameter("txtDescription");
        String create = request.getParameter("txtCreateDate");
        String expired = request.getParameter("txtExpiredDate");
        String txtPrice = request.getParameter("txtPrice");
        String txtQuantity = request.getParameter("txtQuantity");
        boolean foundErr = false;
        CreateCakeError error = new CreateCakeError();

        try {
            ServletContext context = request.getServletContext();
            Map<String, String> siteMap = (Map) context.getAttribute("MAP");

            if (imageLocation.equals("")) {
                error.setEmptyImage("The cake must have image");
                foundErr = true;
            }
            if (name.trim().length() <= 0 || name.trim().length() > 50) {
                error.setInvalidName("The cake's name must contain from 3 to 50 letters");
                foundErr = true;
            }
            if (description.trim().length() <= 0 || description.trim().length() > 100) {
                error.setInvalidDescription("The description must be in 3 to 100 letters");
                foundErr = true;
            }

            float price = Float.parseFloat(txtPrice);
            int quantity = Integer.parseInt(txtQuantity);
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));

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
                ProductDAO dao = new ProductDAO();
                dao.createNewCake(name, imageLocation, description, price, quantity, createDate, expiredDate, categoryID);
                url = siteMap.get("adminPage");
            } else {
                request.setAttribute("ERROR", error);
            }
        } catch (ParseException ex) {
            foundErr = true;
            error.setInvalidDate("Invalid date");
            request.setAttribute("ERROR", error);
        } catch (NumberFormatException ex) {
            foundErr = true;
            error.setInvalidInput("Please enter the right format !!");
            request.setAttribute("ERROR", error);
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
