package com.project.elearning.services;

import com.project.elearning.models.Role;
import com.project.elearning.models.User;

public interface UserServices {

    User saveUser(User user);

    Role saveRole(Role role);

    void addToUser(String username,String roleman);
}

