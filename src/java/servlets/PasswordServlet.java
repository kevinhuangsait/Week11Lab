/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 831719
 */
public class PasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        
        if (id != null)
        {
            request.setAttribute("id", id);
            this.getServletContext().getRequestDispatcher("/WEB-INF/password.jsp").forward(request, response);
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AccountService as = new AccountService();
        
        if(action == "reset")
        {
            String email = request.getParameter("email");
            String path = this.getServletContext().getRealPath("/WEB-INF");
            String url = request.getRequestURL().toString();
            try {
                as.resetPassword(email, path, url);
            } catch (Exception ex) {
                Logger.getLogger(PasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String message = "If the address you entered is valid, you will receive an email very soon."
                    + "Please check your email for your password.";
            request.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        }
        else if(action == "setPass")
        {
            String newPass = request.getParameter("newPass");
            String id = request.getParameter("id");
            boolean change = as.changePassword("id", newPass);
            request.setAttribute("change", change);
            this.getServletContext().getRequestDispatcher("/WEB-INF/password.jsp").forward(request, response);
        }
    }
}
