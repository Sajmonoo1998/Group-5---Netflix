/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll.util;

import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
/**
 *
 * @author pgn
 */
public class MovieSearcher
{
    public List<Movie> search(List<Movie> searchBase, String query)
    {   
        if(query.equals(null) || query=="")  return searchBase;
        List<Movie> movies = new ArrayList();
        List<Movie> foundMovies = new ArrayList();
        movies = searchBase;
        
        for (Movie mov : movies) {
         
           if(mov.getTitle().replace(" ","").toLowerCase().trim().contains(query.toLowerCase())){
            foundMovies.add(mov);
           }
        }
        
        return foundMovies;
    }
    
    public List<Movie> searchByYear(List<Movie> searchByYearBase, int theYear)
    {   
        
        
        List<Movie> foundMoviesByYears = new ArrayList();
        
        
        for (Movie movie : searchByYearBase) {
         
            if(movie.getYear()==theYear){
            foundMoviesByYears.add(movie);
           }
        }
        
        return foundMoviesByYears;
    }
    
    
    public List<Movie> searchWhole(List<Movie> searchByYearBase, int theYear, String query)
    {   
        
        
        List<Movie> foundWhole = new ArrayList();
        
        
        for (Movie movies : searchByYearBase) {
         
            if(movies.getYear()==theYear && movies.getTitle().equals(query)){
            foundWhole.add(movies);
           }
        }
        
        return foundWhole;
    }
    
    
    
    
}
