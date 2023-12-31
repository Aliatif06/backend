package com.project.elearning.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.elearning.models.User;
import com.project.elearning.repository.RoleCustomRepo;
import com.project.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${secret.key}")
    private String secretkey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleCustomRepo roleCustomRepo;



    public String generateToken(User user, Collection<SimpleGrantedAuthority>authorities){
        Algorithm algorithm=Algorithm.HMAC256(secretkey.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+50*60*1000))
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }


    public String generateRefreshToken(User user, Collection<SimpleGrantedAuthority>authorities){
        Algorithm algorithm=Algorithm.HMAC256(secretkey.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+70*60*1000))
                .sign(algorithm);
    }


}
