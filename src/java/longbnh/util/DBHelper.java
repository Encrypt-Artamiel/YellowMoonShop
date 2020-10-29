/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws NamingException, SQLException {
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) tomcatContext.lookup("ym");
        Connection con = dataSource.getConnection();
        return con;
    }
}
