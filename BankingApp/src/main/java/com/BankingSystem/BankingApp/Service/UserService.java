package com.BankingSystem.BankingApp.Service;

import com.BankingSystem.BankingApp.Model.Users;
import com.BankingSystem.BankingApp.Repository.UserRepository;

import com.BankingSystem.BankingApp.Service.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    public Users createUser(Users user) {
        Users existingUser = userRepo.findByUserName(user.getUserName());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
    }

    public String login(String username, String password) {


        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        Users user = userRepo.findByUserName(username);
//        System.out.println(user.getUserName() + " " + user.getPassword());
//        if (user == null) {
//            throw new RuntimeException("User not found!");
//        }
//        else if (!passwordEncoder.matches(password,user.getPassword())) {
//
//            throw new RuntimeException("Invalid username or password");
//        }
//        System.out.println(password + " " +user.getPassword());
//        return user;
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getUserName(),user.getId(),user.getRole().name());
        }
        return "fail";
    }
    public void logout() {
        System.out.println("Logout successful (stateless mode)");
    }
}

