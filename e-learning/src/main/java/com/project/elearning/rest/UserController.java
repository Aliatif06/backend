package com.project.elearning.rest;

import com.project.elearning.models.User;
import com.project.elearning.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findUsers(){
        return userService.findAll();
    }

    @GetMapping("/users/{userid}")
    public User getEmployee(@PathVariable int userid) {

        User theuser = userService.findbyID(userid);

        if (theuser == null) {
            throw new RuntimeException("Employee id not found - " + userid);
        }

        return theuser;
    }


    @PostMapping("/users")
    public User addUser(@RequestBody User theuser){
        theuser.setId(0);
        User user=userService.Save(theuser);

return user;

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theuser) {


        User dbuser = userService.Save(theuser);

        return dbuser;
    }

    @DeleteMapping("/users/{userid}")
    public String deleteUser(@PathVariable int userid) {

        User theuser = userService.findbyID(userid);

        // throw exception if null

        if (theuser == null) {
            throw new RuntimeException("User id not found - " + userid);
        }

        userService.deleteById(userid);

        return "Deleted employee id - " + userid;
    }

}
