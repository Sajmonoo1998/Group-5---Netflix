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
import java.sql.SQLException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRating(Rating rating)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rating> getAllRatings() throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rating> getRatings(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRating(Rating rating) throws IOException
    {
        try (Connection con = conProvider.getConnection();)
        {
            String sql = "UPDATE Rating SET userId = ?, ratings = ? WHERE movieId = ?";
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
