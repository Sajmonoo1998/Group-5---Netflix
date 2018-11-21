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
import movierecsys.dal.DalController;
import movierecsys.dal.exception.MrsDalException;

/**
 *
 * @author Szymon
 */
public interface MRSdbLogicFacade {
        
    public Movie createMovie(int releaseYear, String title);
    public void deleteMovie(Movie movie);
    public List<Movie> getAllMovies();
    public Movie getMovie(int id);
    public void updateMovie(Movie movie);
    public void createRating(Rating rating);
    public void deleteRating(Rating rating);
    public List<Rating> getAllRatings();
    public List<Rating> getRatings(User user);
    public void updateRating(Rating rating);
    public List<User> getAllUsers();
    public User getUser(int id);
    
    public void updateUser(User user);
     public void createUser(String name);
}
