/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import longbnh.users.UsersDAO;
import longbnh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    private final Logger LOG = Logger.getLogger(LoginGoogleServlet.class);

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
        PrintWriter out = response.getWriter();
        try {
            String tokenID = request.getParameter("idtoken");

            HttpTransport transport = new NetHttpTransport();
            JsonFactory json = new JacksonFactory();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(transport, json);
            GoogleIdToken token = verifier.verify(tokenID);
            if (token != null) {
                Payload payload = token.getPayload();
                String email = payload.getEmail();
                String id = payload.getSubject();

                UsersDAO userDAO = new UsersDAO();
                boolean checkMember = userDAO.checkLogin(email, id);
                if (!checkMember) {
                    String name = (String) payload.get("name");
                    userDAO.createAccount(email, id, name);
                    userDAO.checkLogin(email, id);
                    UsersDTO user = userDAO.getUser();
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                } else {
                    userDAO.checkLogin(email, id);
                    UsersDTO user = userDAO.getUser();
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                }
            }
        } catch (GeneralSecurityException ex) {
            LOG.error("GeneralSecurityException : " + ex.getMessage());
        } catch (IOException ex) {
            LOG.error("IOException : " + ex.getMessage());
        } catch (NamingException ex) {
            LOG.error("NamingException : " + ex.getMessage());
        } catch (SQLException ex) {
            LOG.error("SQLException : " + ex.getMessage());
        } finally {
            out.println(url);
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
