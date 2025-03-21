/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hi
 */
public class Update extends HttpServlet {

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
            
         int f=0;
            try
            {
                Connection con=DB.createConn();
                Statement st=con.createStatement();
                String un=request.getParameter("t1");
                String cp=request.getParameter("cpass");
                String cnp=request.getParameter("cnpass");
                
                String q1="select * from PROFILE";
                ResultSet rs=st.executeQuery(q1);

                while(rs.next())
                {
                    if(un.equals(rs.getString(1))&& cp.equals(rs.getString(3)))
                    {
                        f=1;
                        break;
                        
                    }
                    else
                    {
                        f=0;
                    }
                }
                if(f==1)
                {
                    if(cp.equals(cnp))
                    {
                        String q2="Update PROFILE set password='"+cp+"' where Username='"+un+"'";
                       st.executeUpdate(q2);
                       out.print("password has been successfully Changed");
                    }
                    else
                    {
                        out.print("Password does not match");
                    }
                    
                }
                else{
                    out.print("Invalid Username or Password");
                
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
