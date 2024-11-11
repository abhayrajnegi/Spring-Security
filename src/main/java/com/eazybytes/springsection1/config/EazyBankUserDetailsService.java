package com.eazybytes.springsection1.config;

import com.eazybytes.springsection1.Entity.Customer;
import com.eazybytes.springsection1.Repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EazyBankUserDetailsService implements UserDetailsService {


    private final CustomerRepo customerRepo;

    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer=customerRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user details not found for user "+username));
        List<GrantedAuthority> grantedAuthorities=List.of(new SimpleGrantedAuthority(customer.getRole()));

        return new User(customer.getEmail(),customer.getPass(),grantedAuthorities);
    }
}
