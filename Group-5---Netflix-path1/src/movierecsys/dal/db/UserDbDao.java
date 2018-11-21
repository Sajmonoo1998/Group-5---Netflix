/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.intereface.IRatingRepository;
import movierecsys.dal.intereface.IUserRepository;

/**
 *
 * @author pgn
 */
public class UserDbDao implements IUserRepository
{

    private DbConnectionProvider conProvider;
    
    public UserDbDao() throws IOException
    {
        conProvider = new DbConnectionProvider();
    }
    
    
    @Override
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        try (Connection con = conProvider.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");
            while(rs.next())
            {
                int ID = rs.getInt("ID");
                String name = rs.getString("Name");
                
                User u = new User(ID, name);
                users.add(u);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUser(int id)
    {
        try (Connection con = conProvider.getConnection();)
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Users WHERE ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                
                String name = rs.getString("Name");
                User u = new User(id, name);
                return u;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateUser(User user)
    {
try (Connection con = conProvider.getConnection();)
        {
            String sql = "UPDATE Users SET Name=? WHERE ID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getId());
            stmt.execute();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    public void createUser(String name){
    
   
        
        
        try(Connection con = conProvider.getConnection())
        {
             int id=0;
        for (User u : getAllUsers()) {
            if(u.getId()==id) id++;
            else break;
        }
            String sql = "INSERT INTO Users(ID, Name) VALUES(?, ?)";
            PreparedStatement prtr = con.prepareStatement(sql);
            prtr.setInt(1, id);
            prtr.setString(2, name);
            prtr.execute();
            
            
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
