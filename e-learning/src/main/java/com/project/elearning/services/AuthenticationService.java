package com.project.elearning.services;

import com.project.elearning.auth.AuthenticationRequest;
import com.project.elearning.auth.AuthenticationResponse;
import com.project.elearning.auth.RegisterRequest;
import com.project.elearning.config.JwtService;
import com.project.elearning.models.Role;
import com.project.elearning.models.User;
import com.project.elearning.repository.RoleCustomRepo;
import com.project.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleCustomRepo roleCustomRepo;
    private final  UserServices userServices;

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest){
        try {
            User user=userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new NoSuchElementException("User not found"));
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
            List<Role> roles=null;
            if(user!=null){
                roles=roleCustomRepo.getRole(user);
            }
            Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
            Set<Role> set= new HashSet<>();
            roles.stream().forEach(c ->{set.add(new Role(c.getName()));
                authorities.add(new SimpleGrantedAuthority(c.getName()));
            });
            user.setRoles(set);
            set.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));
            var jwtAccessToken= jwtService.generateToken(user,authorities);
            var jwtRefreshToken=jwtService.generateRefreshToken(user,authorities);
            return ResponseEntity.ok(AuthenticationResponse.builder().access_token(jwtAccessToken).refresh_token(jwtRefreshToken).email(user.getEmail()).firstname(user.getFirstname()).build());

        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body("Invalid Crendential");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    public ResponseEntity<?> register(RegisterRequest registerRequest) {

        try {
            if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
              throw new IllegalArgumentException("user with"+registerRequest.getEmail().toString()+"email already exists");
            }
            userServices.saveUser(new User(registerRequest.getFirstname(),registerRequest.getLastname(),registerRequest.getEmail(),registerRequest.getPassword(),new HashSet<>()));
            userServices.addToUser(registerRequest.getEmail(),"ROLE_STUDENT"); //default role
            User user=userRepository.findByEmail(registerRequest.getEmail()).orElseThrow();
            return  ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
