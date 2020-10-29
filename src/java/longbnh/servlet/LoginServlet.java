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
import longbnh.users.UsersDAO;
import longbnh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final Logger LOG = Logger.getLogger(LoginServlet.class);

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
        String url = LOGIN_PAGE;
        try {
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");

            UsersDAO dao = new UsersDAO();
            boolean result = dao.checkLogin(userID, password);
            if (result) {
                UsersDTO user = dao.getUser();

                HttpSession session = request.getSession();
                session.setAttribute("USER", user);
                if (user.getRoleID() == 1) {
                    url = siteMap.get("adminPage");
                } else {
                    url = siteMap.get("homePage");
                }
            } else {
                String error = "User is not found";
                request.setAttribute("ERROR", error);
            }
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
        } finally {
            if (url.equals(LOGIN_PAGE)) {
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
