package org.rahul.taskmanagmentapplication.Security;

import org.rahul.taskmanagmentapplication.Entity.User;
import org.rahul.taskmanagmentapplication.Exception.UserNotFound;
import org.rahul.taskmanagmentapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email).orElseThrow(
//                () -> new UserNotFound(String.format("User with email %s not found", email))
//        );
//
//        Set<String> roles= new HashSet<String>();
//        roles.add("ROLE_ADMIN");
//
//        return new UserDetails(user,roles);
//    }
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(
            () -> new UserNotFound(String.format("User with email %s not found", email))
    );

    Set<String> roles= new HashSet<String>();
    roles.add("ROLE_ADMIN");

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),userAuthorities(roles));
}

private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
    return roles.stream().map(role -> new GrantedAuthority() {
        @Override
        public String getAuthority() {
            return role;
        }
    }).collect(Collectors.toList());
}

}
