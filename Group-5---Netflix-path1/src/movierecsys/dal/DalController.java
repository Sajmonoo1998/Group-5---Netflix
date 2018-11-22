/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.db.MovieDbDao;
import movierecsys.dal.exception.MrsDalException;
import movierecsys.dal.intereface.IMovieRepository;
import movierecsys.dal.intereface.IRatingRepository;
import movierecsys.dal.intereface.IUserRepository;

/**
 *
 * @author pgn
 */
public class DalController implements MrsDalInterface
{
    
    private final IMovieRepository movieRepo;
    private IUserRepository userRepo;
    private IRatingRepository ratingRepo;

    public DalController() throws IOException
    {
        movieRepo = new MovieDbDao();
    }

    @Override
    public Movie createMovie(int releaseYear, String title) throws MrsDalException
    {  
        try
        {
            return movieRepo.createMovie(releaseYear, title);
        } catch (IOException ex)
        {
            //LOG the error
            System.out.println(ex.getMessage());
            throw new MrsDalException("Could not create movie.", ex);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws MrsDalException
    {
        try {
            movieRepo.deleteMovie(movie);
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Movie> getAllMovies() throws MrsDalException
    {
        List<Movie> movies = new ArrayList<>();
        try {
            movies = movieRepo.getAllMovies();
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws MrsDalException
    {
      Movie m=null;
        try {
            m = movieRepo.getMovie(id);
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return m;
    }

    @Override
    public void updateMovie(Movie movie) throws MrsDalException
    {
        try {
            movieRepo.updateMovie(movie);
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createRating(Rating rating)
    {
    ratingRepo.createRating(rating);
    }

    @Override
    public void deleteRating(Rating rating)
    {
        ratingRepo.deleteRating(rating);
    }

    @Override
    public List<Rating> getAllRatings() throws MrsDalException
    {
        List<Rating> ratings=null;
        try {
            ratings = ratingRepo.getAllRatings();
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatings(User user)
    {
        List<Rating> userRatings;
       userRatings= ratingRepo.getRatings(user);
       return userRatings;
    }

    @Override
    public void updateRating(Rating rating) throws MrsDalException
    {
        try {
            ratingRepo.updateRating(rating);
        } catch (IOException ex) {
            Logger.getLogger(DalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> allusers;
        allusers=userRepo.getAllUsers();
        return allusers;
    }

    @Override
    public User getUser(int id)
    {
        return userRepo.getUser(id);
    }

    @Override
    public void updateUser(User user)
    {
        userRepo.updateUser(user);
    }
    //@Override
     public void createUser(String name){
     userRepo.createUser(name);
     }
     
     public List<Movie> searchedMovies(String query) {
     List<Movie> m;
     m=movieRepo.searchedMovies(query);
     return m;
     }
    public List<Movie> searchByYear(int year) {
     List<Movie> m;
     m=movieRepo.searchByYear(year);
     return m;
     }
    public List<Movie> searchWhole(int year,String query) {
     List<Movie> m;
     m=movieRepo.searchWhole(year, query);
     return m;
     }
     
     
}
