
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hi
 */
public class DB {
    static Connection createConn()
    {
        Connection conn=null;
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Student","ss","ss");
        }
        catch(Exception e)
        {
            
        }
        return conn;
    }
}
