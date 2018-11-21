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
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.intereface.IRatingRepository;

/**
 *
 * @author pgn
 */
public class RatingDbDao implements IRatingRepository
{
    private DbConnectionProvider conProvider;
    
    public RatingDbDao() throws IOException
    {
        conProvider = new DbConnectionProvider();
    }

    @Override
    public void createRating(Rating rating)
    {
        try(Connection con = conProvider.getConnection())
        {
            String sql = "INSERT INTO Movie(movieId, userId, rating) VALUES(?, ?, ?)";
            PreparedStatement prtr = con.prepareStatement(sql);
            prtr.setInt(1, rating.getMovie());
            prtr.setInt(2, rating.getUser());
            prtr.setInt(3, rating.getRating());
            prtr.execute();
//            Rating r = new Rating(rating.getMovie(), rating.getUser(), rating.getRating());
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteRating(Rating rating)
    {
        try(Connection con = conProvider.getConnection())
        {
            String sql = "DELETE FROM Ratings WHERE rating = ?";
            PreparedStatement prtr = con.prepareStatement(sql);
            prtr.setInt(1, rating.getRating());
            prtr.execute();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Rating> getAllRatings() throws IOException
    {
        List<Rating> ratings = new ArrayList<>();
        
        try (Connection con = conProvider.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Movie;");
            while(rs.next())
            {
                int movieId = rs.getInt("movieId");
                int userId = rs.getInt("userId");
                int rating = rs.getInt("rating");
                Rating r = new Rating(movieId, userId, rating);
                ratings.add(r);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatings(User user)
    {
        return null;
    }

    @Override
    public void updateRating(Rating rating) throws IOException
    {
        try (Connection con = conProvider.getConnection();)
        {
            String sql = "UPDATE Ratings SET userId = ?, rating = ? WHERE movieId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, rating.getUser());
            stmt.setInt(2, rating.getRating());
            stmt.setInt(3, rating.getMovie());
            stmt.execute();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
