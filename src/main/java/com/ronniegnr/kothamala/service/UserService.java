package com.ronniegnr.kothamala.service;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.domain.enumeration.UserStatus;
import com.ronniegnr.kothamala.repository.UserRepository;
import com.ronniegnr.kothamala.service.util.RandomUtil;
import com.ronniegnr.kothamala.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service class for managing user.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Inject
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(ManagedUserVM managedUserVM) {
        User user = new User();
        user.setEmail(managedUserVM.getEmail());
        user.setFirstName(managedUserVM.getFirstName());
        user.setLastName(managedUserVM.getLastName());
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setStatus(UserStatus.active);
        userRepository.save(user);
        log.debug("Created information for User : {}", user);
        return user;
    }

    public void updateUser(ManagedUserVM managedUserVM) {
        Optional.of(userRepository.findOne(managedUserVM.getId()))
                .ifPresent(user -> {
                    user.setEmail(managedUserVM.getEmail());
                    user.setFirstName(managedUserVM.getFirstName());
                    user.setLastName(managedUserVM.getLastName());
                    user.setStatus(managedUserVM.getStatus());
                    User updatedUser = userRepository.save(user);
                    log.debug("Updated information for User : {}", updatedUser);
                });

    }

    public void deleteUser(long id) {
        Optional.of(userRepository.findOne(id))
                .ifPresent(user -> {
                    userRepository.delete(id);
                    log.debug("Delete User : {}", user);
                });
    }

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<User> fineOne(long id) {
        return Optional.of(userRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }
}
