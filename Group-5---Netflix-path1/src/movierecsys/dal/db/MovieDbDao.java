/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal.db;

import movierecsys.dal.intereface.IMovieRepository;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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

/**
 *
 * @author pgn
 */
public class MovieDbDao implements IMovieRepository
{
    private DbConnectionProvider conProvider;

    public MovieDbDao() throws IOException
    {
        conProvider = new DbConnectionProvider();
    }
    
    

    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        try(Connection con = conProvider.getConnection())
        {
            String sql = "INSERT INTO Movies(ID,Year, Title) VALUES(?,?, ?)";
            PreparedStatement prtr = con.prepareStatement(sql);
            prtr.setInt(1, getHighId());
            prtr.setInt(2, releaseYear);
            prtr.setString(3, title);
            prtr.execute();
            Movie m = new Movie(getHighId(), releaseYear, title);
            return m;
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        try(Connection con = conProvider.getConnection())
        {
            String sql = "DELETE FROM Movies WHERE ID = ?";
            PreparedStatement prtr = con.prepareStatement(sql);
            prtr.setInt(1, movie.getId());
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
    public List<Movie> getAllMovies() throws IOException
    {
        List<Movie> movies = new ArrayList<>();
        
        try (Connection con = conProvider.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Movies;");
            while(rs.next())
            {
                int id = rs.getInt("ID");
                int year = rs.getInt("Year");
                String title = rs.getString("Title");
                Movie movie = new Movie(id, year, title);
                movies.add(movie);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws IOException
    {
        try (Connection con = conProvider.getConnection();)
        {
//            String sqlStatement = "SELECT * FROM Person";
//            Statement statement = con.createStatement();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Movies WHERE ID = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int releaseYear = Integer.valueOf(rs.getString("releaseYear"));
                String title = rs.getString("title");
                Movie m = new Movie(id, releaseYear, title);
                return m;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        try (Connection con = conProvider.getConnection();)
        {
            String sql = "UPDATE Movies SET Year = ?, Title = ? WHERE ID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, movie.getYear());
            stmt.setString(2, movie.getTitle());
            stmt.setInt(3, movie.getId());
            stmt.execute();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Integer getHighId()
    {
        try (Connection con = conProvider.getConnection();)
        {
            String sql = "SELECT * FROM Movies order by ID desc";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int id = 0; 
            if ( rs.next() ){
            id = rs.getInt(1);  
            }
            
          
            return id+1;
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Movie> searchedMovies(String query) {
  Movie m;
  int id;
  int year;
  String title;
        try (Connection con = conProvider.getConnection();)
        {
            List<Movie> searchedMovies = new ArrayList();
            //String sql = "SELECT * FROM Movies WHERE Title like "+"'%"+query+"%'";
            String sql = "SELECT * FROM Movies WHERE Title like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,"%"+query+"%");
            ResultSet rs = stmt.executeQuery();
         
            while ( rs.next()){
            id=rs.getInt("ID");
            year=rs.getInt("Year");
            title=rs.getString("Title");
            m=new Movie(id, year, title);
            searchedMovies.add(m);
            }
            return searchedMovies;
          
       
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    @Override 
    public List<Movie> searchByYear(int year) {
  Movie m;
  int id;

  String title;
        try (Connection con = conProvider.getConnection();)
        {
            List<Movie> searchedMovies = new ArrayList();
            String sql = "SELECT * FROM Movies WHERE Year = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,year);
            ResultSet rs = stmt.executeQuery();
         
            while ( rs.next()){
            id=rs.getInt("ID");
            title=rs.getString("Title");
            m=new Movie(id, year, title);
            searchedMovies.add(m);
            }
            return searchedMovies;
          
       
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
    @Override 
    public List<Movie> searchWhole(int year,String query) {
  Movie m;
  int id;
  
        try (Connection con = conProvider.getConnection();)
        {
            List<Movie> searchedMovies = new ArrayList();
            //String sql = "SELECT * FROM Movies WHERE Title like "+"'%"+query+"%'";
            String sql = "SELECT * FROM Movies WHERE Title like ? AND Year = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,"%"+query+"%");
            stmt.setInt(2,year);
            ResultSet rs = stmt.executeQuery();
         
            while ( rs.next()){
            id=rs.getInt("ID"); 
            m=new Movie(id, year, query);
            searchedMovies.add(m);
            }
            return searchedMovies;
          
       
        } catch (SQLServerException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(MovieDbDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    
    
}