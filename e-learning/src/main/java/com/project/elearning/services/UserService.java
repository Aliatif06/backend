package com.project.elearning.services;

import com.project.elearning.models.Role;
import com.project.elearning.models.User;
import com.project.elearning.repository.RoleRepository;
import com.project.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService implements UserServices{
@Autowired
private RoleRepository roleRepository;
@Autowired
private UserRepository userRepository;


private  final PasswordEncoder passwordEncoder;

  @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
       this.userRepository = userRepository;
       this.passwordEncoder=passwordEncoder;
    }



public List<User> findAll(){
      return userRepository.findAll();
}
public User Save(User Theuser){
return userRepository.save(Theuser);
}

    public User findbyID(int theid){
        Optional<User> user=userRepository.findById(theid);
        User theuser=null;
        if(user.isPresent()){
            theuser=user.get();
        }else{
            throw new RuntimeException("not found"+theid);
        }
        return theuser;
    }

    public User save(User user){
      User theuser=userRepository.save(user);
      return theuser;
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public void deleteById(int theId)
    {
        userRepository.deleteById(theId);
    }

    @Override
    public User saveUser(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(new HashSet<>());

        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addToUser(String username, String roleman) {
     if(!userRepository.findByEmail(username).isPresent()){
         throw new IllegalArgumentException("User with email"+username+"does not exist");
     }
     Role role =roleRepository.findByName(roleman);
     if(role==null){
            throw new IllegalArgumentException("Role with name"+roleman+"does not exist");
        }
     User user  = userRepository.findByEmail(username).get();
     user.getRoles().add(role);

    }
}
