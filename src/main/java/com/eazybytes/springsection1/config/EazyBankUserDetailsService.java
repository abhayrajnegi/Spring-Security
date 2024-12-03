package com.eazybytes.springsection1.config;
//
//import com.eazybytes.springsection1.Entity.Customer;
//import com.eazybytes.springsection1.Repository.CustomerRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class EazyBankUserDetailsService implements UserDetailsService {
//
//
//    private final CustomerRepo customerRepo;
//
//    /**
//     * @param username the username identifying the user whose data is required.
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Customer customer=customerRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("user details not found for user "+username));
//        List<GrantedAuthority> grantedAuthorities=List.of(new SimpleGrantedAuthority(customer.getRole()));
//
//        return new User(customer.getEmail(),customer.getPass(),grantedAuthorities);
//    }
//}




import com.eazybytes.springsection1.Repository.CustomerRepository;
import com.eazybytes.springsection1.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EazyBankUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + username));

//        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        List<GrantedAuthority> authorities = customer.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList()); //customer.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).toList();
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}

