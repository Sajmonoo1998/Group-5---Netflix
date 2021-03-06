/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movierecsys.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.bll.exception.MovieRecSysException;
import movierecsys.bll.util.MovieSearcher;
import movierecsys.dal.DalController;
import movierecsys.dal.MovieDAO;
import movierecsys.dal.RatingDAO;
import movierecsys.dal.UserDAO;
import movierecsys.dal.db.MovieDbDao;
import movierecsys.dal.db.RatingDbDao;
import movierecsys.dal.db.UserDbDao;
import movierecsys.dal.exception.MrsDalException;
import movierecsys.dal.intereface.IMovieRepository;

/**
 *
 * @author pgn
 */
public class MRSManager implements MRSLogicFacade {

    private final MovieDAO movieDAO;
    private final RatingDAO ratingDao;
    private final UserDAO userDao;
    private final MovieSearcher ms;
    
    public MRSManager() throws IOException
    {
        movieDAO = new MovieDAO();
        ratingDao = new RatingDAO();
        userDao = new UserDAO();
        ms =new MovieSearcher();
        
        
    }
    
    
    
    @Override
    public List<Rating> getRecommendedMovies(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getMovieReccomendations(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> searchMovies(String query)
    {
        List<Movie> lm = new ArrayList();
        try
        {
            //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            lm = ms.search(getAllMovies(), query);
        } catch (MovieRecSysException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lm;
    }
    
    public List<Movie> searchMoviesByYear(int theYear)
    {
        List<Movie> lm = new ArrayList();
       
        try {
            lm = ms.searchByYear(getAllMovies(), theYear);
        } catch (MovieRecSysException ex) {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lm;
    }
    
    public List<Movie> searchWhole(int theYear,String query)
    {
 List<Movie> lm = new ArrayList();
       
        try {
            lm = ms.searchWhole(getAllMovies(), theYear, query);
        } catch (MovieRecSysException ex) {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lm;
    }  
    
    
    
    
    

    @Override
    public Movie createMovie(int year, String title) 
    {
        Movie m = null;
        try {
            m = movieDAO.createMovie(year, title);
           
        } catch (IOException ex) {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @Override
    public void updateMovie(Movie movie)
    {
        try
        {
            movieDAO.updateMovie(movie);
            
            
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMovie(Movie movie)
    {
        try
        {
            movieDAO.deleteMovie(movie);
            
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rateMovie(Movie movie, User user, int rating)
    {
        Rating r = new Rating(movie.getId(), user.getId(), rating);
        try
        {
            ratingDao.createRating(r);
           
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User createNewUser(String name)
    {
        try {
            userDao.createUser(name);
            
        } catch (IOException ex) {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public User getUserById(int id)
    {
        User u = null;
        try
        {
            u = userDao.getUser(id);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> allUsers = null;
        try
        {
            allUsers = userDao.getAllUsers();
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    /**
     * Gets all movies.
     * @return List of movies.
     * @throws MovieRecSysException
     */
    @Override
    public List<Movie> getAllMovies() throws MovieRecSysException
    {
        try
        {
            return movieDAO.getAllMovies();
        } catch (IOException ex)
        {
//            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex); You could log an exception
            throw new MovieRecSysException("Could not read all movies. Cause: " + ex.getMessage());
        }
    }

}
