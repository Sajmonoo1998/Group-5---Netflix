/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class UserDAO
{
    private static final String USER_SOURCE = "data/users.txt";
    
    /**
     * Gets a list of all known users.
     * @return List of users.
     */
    public List<User> getAllUsers() throws IOException
    {
        List<User> getAllUsers = new ArrayList<>();
        File file = new File(USER_SOURCE);
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                if(!line.isEmpty())
                {
                    try
                    {
                        User u = stringArrayToUser(line);
                        getAllUsers.add(u);
                    } catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
        return getAllUsers;
    }
    
    private User stringArrayToUser(String line)
    {
        String[] arrUsers = line.split(",");

        int id = Integer.parseInt(arrUsers[0]);
        String name = arrUsers[1];
        User u = new User(id, name);
        return u;
    }
    
    /**
     * Gets a single User by its ID.
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id) throws IOException
    {
        List<User> all = getAllUsers();
        int index = Collections.binarySearch(all, new User(id, ""), (User o1, User o2) -> Integer.compare(o1.getId(), o2.getId()));
        if (index >= 0)
        {
            return all.get(index);
        } else
        {
            throw new IllegalArgumentException("No movie with ID: " + id + " is found.");
        }
    }
    
    /**
     * Updates a user so the persistence storage reflects the given User object.
     * @param user The updated user.
     */
    public void updateUser(User user) throws IOException
    {
        File tmp = new File("data/tmp_movies.txt");
        List<User> allUsers = getAllUsers();
        allUsers.removeIf((User u) -> u.getId() == user.getId());
        allUsers.add(user);
        Collections.sort(allUsers, (User o1, User o2) -> Integer.compare(o1.getId(), o2.getId()));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp)))
        {
            for (User u : allUsers)
            {
                bw.write(u.getId() + "," + u.getName());
                bw.newLine();
            }
        }
        Files.copy(tmp.toPath(), new File(USER_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmp.toPath());
    }
    
}
