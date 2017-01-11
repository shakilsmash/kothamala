package com.ronniegnr.kothamala.security;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import com.ronniegnr.kothamala.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    private final UserRepository userRepository;

    @Inject
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.debug("Authenticating with email {}", email);
        String emailInLowerCase = email.toLowerCase();
        Optional<User> userFromDatabase =  userRepository.findOneByEmail(emailInLowerCase);
        return userFromDatabase.map(user -> {
            if(user.getStatus() != UserStatus.active) {
                throw new UserNotActivatedException("User " + emailInLowerCase + " is not activated");
            }
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(emailInLowerCase, user.getPasswordHash(), grantedAuthorities);

        }).orElseThrow(() -> new UsernameNotFoundException("User " + emailInLowerCase + " was not found"));
    }
}
