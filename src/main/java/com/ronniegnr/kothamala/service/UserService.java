package com.ronniegnr.kothamala.service;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import com.ronniegnr.kothamala.repository.UserRepository;
import com.ronniegnr.kothamala.service.util.RandomUtil;
import com.ronniegnr.kothamala.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Service class for manager users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;




    public User createUser(ManagedUserVM managedUserVM) {
        User user = new User();
        user.setEmail(managedUserVM.getEmail());
        user.setFirstName(managedUserVM.getFirstName());
        user.setLastName(managedUserVM.getLastName());
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setStatus(UserStatus.active);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

}
