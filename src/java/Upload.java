/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author hi
 */
public class Upload extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String DB_URL = "jdbc:derby://localhost:1527/Gauri";
        String DB_USER = "pdf";   // your DB username
        String DB_PASSWORD = "pdf";  // your DB password
        try
        {
            int i=Integer.parseInt(request.getParameter("id"));
            response.setContentType("Write.html");
            Part filePart = request.getPart("pdfFile");  // 'pdfFile' is the form field name
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) 
            {
                String sql = "INSERT INTO BOOK1(ID,FNAME, FDATA) VALUES (?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) 
                {
                    ps.setInt(1,i);
                    ps.setString(2, fileName);
                    ps.setBlob(3, fileContent);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) 
                    {
                        //PrintWriter out = response.getWriter();
                        out.println("<html><body><h3>PDF uploaded successfully!</h3></body></html>");
                    }
                }
            }
            catch (Exception e) 
            {
               
               
                out.println(e);
            }
        }
        catch(Exception e)
        {
            out.print(e);
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
