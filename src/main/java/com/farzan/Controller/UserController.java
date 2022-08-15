package com.farzan.Controller;

import com.farzan.Entity.User;
import com.farzan.Exception.InternalServerError;
import com.farzan.Exception.UserNotFound;
import com.farzan.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    @Autowired
    Repository repository;

    //Create User
    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody User user)
    {
        try {
            User newUser = new User(user.getName(),
                    user.getFamily(),
                    user.getEmail());
            repository.save(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    //Get all Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> users = new ArrayList<User>();
            repository.findAll().forEach(users::add);

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e)
        {
            throw new InternalServerError(e.getMessage());
        }
}
   // Get User by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable("id") Long id,
                                           @RequestBody User user)
    {
        Optional<User> userData = repository.findById(id);

        if (userData.isPresent())
        {
            User user1 = userData.get();

            user1.setEmail(user.getEmail());
            user1.setName(user.getName());
            user1.setFamily(user.getFamily());

            return new ResponseEntity<>(repository.save(user1), HttpStatus.OK);

        } else {
            throw new UserNotFound("Invalid User Id");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> DeleteUser(@PathVariable("id") Long id)
    {
        Optional<User> user = repository.findById(id);

        if (user.isPresent())
        {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
        {
            throw new UserNotFound("Invalid User id");
        }
    }

}
