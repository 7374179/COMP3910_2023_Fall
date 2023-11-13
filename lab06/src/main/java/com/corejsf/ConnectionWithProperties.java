package com.corejsf;

import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;


public class ConnectionWithProperties
{
    /**
     * Just connect to a database with properties.
     * Run using maven with:
     * mvn exec:java -Dexec.mainClass="ca.bcit.infosys.jdbcexamples.inventory.ConnectionWithProperties"
     * @param args
     */
    public static void main (String args[])
    {
    	Properties dbProperties = new Properties();
        try
        {
            dbProperties.load(ConnectionWithProperties.class.getResourceAsStream("/db.properties"));
        }
        catch (Exception e)
        {
            System.out.println("Properties file failed to load.");
            return;
        }
        
        String driver = dbProperties.getProperty("jdbc.driver");
        String url = dbProperties.getProperty("jdbc.url");
        String user = dbProperties.getProperty("jdbc.user");
        String password = dbProperties.getProperty("jdbc.password");
        try
        {
            Class.forName(driver);
        }
        catch (Exception e)
        {
            System.out.println("Driver failed to load.");
            return;
        }

        try
        {
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("\nConnected using properties file!");
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.exit(0);
    }
}

