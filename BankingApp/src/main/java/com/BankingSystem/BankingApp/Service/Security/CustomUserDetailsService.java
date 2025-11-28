package com.BankingSystem.BankingApp.Service.Security;

import com.BankingSystem.BankingApp.Model.Security.UserPrinciple;
import com.BankingSystem.BankingApp.Model.Users;
import com.BankingSystem.BankingApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciple(user);
    }
}
