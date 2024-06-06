package hotel_reservation_system;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mahmoud
 */
public class dbConn {
     public static Connection DBConnection() {
        
         Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            conn = DriverManager.getConnection("jdbc:oracle:thin:HOTEL DATABASE/123@localhost:1521/XE");
//HOTEL DATABASE database name//123 password

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
     
        return conn;
    
}
}
