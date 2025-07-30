/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.nic.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ReturnConnection {

    static Logger logger = Logger.getLogger(ReturnConnection.class);
    Connection con; 

    public ReturnConnection() {
    }

    public Connection getConnection() {
     
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");        
//            con = DriverManager.getConnection("jdbc:oracle:thin:@10.153.33.250:1525:devdb", "return", "apdv12");
              con = DriverManager.getConnection("jdbc:oracle:thin:@10.153.181.6:1525:stagedb", "GST_REGISTRATION", "stage123");          
        } catch (Exception e) {
            logger.error("connectin Exception:" + e.getMessage());
        }
        return con;
    }

    public void closeConnnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }
}
