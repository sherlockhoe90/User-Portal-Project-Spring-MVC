package com.inexture.userportal.userportalproject.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inexture.userportal.userportalproject.dao.UserDAO;
import com.inexture.userportal.userportalproject.dao.UserDAOImp;
import com.inexture.userportal.userportalproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

import com.inexture.userportal.userportalproject.utility.HibernateUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewUserDetailsController {

    HttpServletRequest request;
    HttpServletResponse response;

    private static final long serialVersionUID = 1L;
    private static Logger logger = LogManager.getLogger("UserLoginController");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            int currentPage = 1;
//            int recordsPerPage = 5;
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));


            //calling the DAO method to get paginated user data.
            SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
            UserDAO userDAO = new UserDAOImp(sessionFactory);
            List<User> userList = userDAO.displayUser(currentPage, recordsPerPage);
            int totalRecords = userDAO.getNumberOfRows();

            //calculating the total number of pages.
            int nOfPages = totalRecords / recordsPerPage;

            if (nOfPages % recordsPerPage > 0) {

                nOfPages++;
            }
//            else if (nOfPages == 0 && totalRecords> 0) {
//
//                ++nOfPages;
//            }


            //createinf a JSON response.
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("recordsFiltered", totalRecords);
            jsonResponse.addProperty("data", new Gson().toJson(userList));
            jsonResponse.addProperty("totalPages", nOfPages);
            jsonResponse.addProperty("currentPage", currentPage);
            jsonResponse.addProperty("recordsTotal", totalRecords);


            response.setContentType("application/json");
            response.getWriter().write(jsonResponse.toString());
//            response.setContentType("text/css");
//            RequestDispatcher rd = request.getRequestDispatcher("/viewUsers.jsp");
//            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPOST method called, but it shouldn't be.\nThe doGET should've been called using the new ajax setting.");
    }
}