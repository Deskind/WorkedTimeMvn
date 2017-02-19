/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.workedtimemvn.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Desk1nd
 */
public class MysqlUtil {
    private static String jdbcDriver = "com.mysql.jdbc.Driver";
    private static String dbAddress = "jdbc:mysql://localhost:3306?serverTimezone=UTC";
    private static String userName = "root";
    private static String password = "";
    
    public static void dataBasePrepare(){
        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(dbAddress, userName, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("create database if not exists worktime");
            statement.executeUpdate("use worktime");
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
