/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author pgn
 */
public class DbConnectionProvider
{

    private SQLServerDataSource ds;
    private static final String PROP_FILE = "database.settings";

    public DbConnectionProvider() throws FileNotFoundException, IOException
    {
        Properties databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(PROP_FILE));
        ds = new SQLServerDataSource();
        ds.setServerName("Server");
        ds.setDatabaseName("Database");
        ds.setUser("User");
        ds.setPassword("Password");
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }

}
