package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.text.rtf.RTFEditorKit;

import com.techelevator.authentication.PasswordHasher;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private PasswordHasher passwordHasher;

    /**
     * Create a new user dao with the supplied data source and the password hasher
     * that will salt and hash all the passwords for users.
     *
     * @param dataSource an SQL data source
     * @param passwordHasher an object to salt and hash passwords
     */
    @Autowired
    public JdbcUserDao(DataSource dataSource, PasswordHasher passwordHasher) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordHasher = passwordHasher;
    }

    /**
     * Save a new user to the database. The password that is passed in will be
     * salted and hashed before being saved. The original password is never
     * stored in the system. We will never have any idea what it is!
     *
     * @param userName the user name to give the new user
     * @param password the user's password
     * @param role the user's role
     * @return the new user
     */
    @Override
    public User saveUser(String userName, String password, String role) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));
        long newId = jdbcTemplate.queryForObject(
                "INSERT INTO users(username, password, salt, role) VALUES (?, ?, ?, ?) RETURNING id", Long.class,
                userName, hashedPassword, saltString, role);

        User newUser = new User();
        newUser.setId(newId);
        newUser.setUsername(userName);
        newUser.setRole(role);

        return newUser;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(newPassword, salt);
        String saltString = new String(Base64.encode(salt));

        jdbcTemplate.update("UPDATE users SET password=?, salt=? WHERE id=?", hashedPassword, saltString, user.getId());
    }

    /**
     * Look for a user with the given username and password. Since we don't
     * know the password, we will have to get the user's salt from the database,
     * hash the password, and compare that against the hash in the database.
     *
     * @param userName the user name of the user we are checking
     * @param password the password of the user we are checking
     * @return true if the user is found and their password matches
     */
    @Override
    public User getValidUserWithPassword(String userName, String password) {
        String sqlSearchForUser = "SELECT * FROM users WHERE UPPER(username) = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
        if (results.next()) {
            String storedSalt = results.getString("salt");
            String storedPassword = results.getString("password");
            String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
            if (storedPassword.equals(hashedPassword)) {
                return mapResultToUser(results);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Get all of the users from the database.
     * @return a List of user objects
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sqlSelectAllUsers = "SELECT id, u.username, role, full_name, phone, city, fav_brands, fav_types FROM users as u WHERE id = ? " + 
        		"JOIN userdetails WHERE user_id = id Order By full_name";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);

        while (results.next()) {
            User user = mapResultToUserWithUserDetail(results);
            users.add(user);
        }

        return users;
    }
    
    @Override
    public boolean isUserCheckedIn(long id, long eventId) {
    	boolean checkedIn = false;
    	
    	String sql = "SELECT id from userstoevent " + 
    				"WHERE event_id = ? AND id = ?;";
    	SqlRowSet result = jdbcTemplate.queryForRowSet(sql, eventId, id);
    	
    	if(result.next()) {
    		checkedIn = true;
    	}
    	
    	return checkedIn;
    }



    @Override
    public User getUserByUsername(String username) {
        String sqlSelectUserByUsername = "SELECT id, username, role FROM users WHERE username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByUsername, username);

        if (results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
    }
    
    @Override
    public List<User> getAdminIds() {
        String sqlSelectUserByRole = "SELECT id, username, role FROM users WHERE role = 'admin'";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByRole);
        
        List<User> adminUsers = new ArrayList<User>();

        if (results.next()) {
            adminUsers.add(mapResultToUser(results));
        } 
        return adminUsers;
    }
    
    @Override
    public User getUserById(Long id) {
        String sqlSelectUserByUsername = "SELECT id, username, role FROM users WHERE id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUserByUsername, id);

        if (results.next()) {
            return mapResultToUser(results);
        } else {
            return null;
        }
    }
    private User mapResultToUser(SqlRowSet results) {
        User user = new User();
        user.setId(results.getLong("id"));
        user.setUsername(results.getString("username"));
        user.setRole(results.getString("role"));
     
        return user;
    }
    
    private User mapResultToUserWithUserDetail(SqlRowSet results) {
        User user = new User();
        user = mapResultToUser(results);
        user.setUserDetails(mapResultsToUserDetail(results));
     
        return user;
    }
    
    private UserDetail mapResultsToUserDetail(SqlRowSet results) {
    	UserDetail uD = new UserDetail();
    	
    	uD.setFullName(results.getString("full_name"));
    	uD.setPhoneNumber(results.getString("phone"));
    	uD.setCityOfResidence(results.getString("city"));
    	uD.setFavBrandsOfWhiskeys(results.getString("fav_brands"));
    	uD.setFavTypesOfWhiskeys(results.getString("fav_types"));
    	uD.setUsername(results.getString("username"));
    	
    	return uD;
    }

}
