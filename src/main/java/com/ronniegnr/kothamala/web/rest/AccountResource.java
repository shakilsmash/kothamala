package com.ronniegnr.kothamala.web.rest;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.service.UserService;
import com.ronniegnr.kothamala.service.dto.UserDTO;
import com.ronniegnr.kothamala.web.rest.util.HeaderUtil;
import com.ronniegnr.kothamala.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing account
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserService userService;

    @Inject
    public AccountResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /register : register the user, if given email address is not already used for an existing account.
     *
     * @param managedUserVM the user information which will be used to create the user
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with with status 400 (Bad Request) if teh email is already in use
     * @throws URISyntaxException if the location URI syntaxt is incorrect
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) throws URISyntaxException {
        log.debug("REST request to register a Account : {}", managedUserVM);
        Optional<User> existingUser = userService.findOneByEmail(managedUserVM.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("User", "emailexists,", "Email already in use"))
                    .body(null);
        } else {
            User user = userService.createUser(managedUserVM);
            return ResponseEntity.created(new URI("/api/users/" + user.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert("User", user.getEmail()))
                    .body(new ManagedUserVM(user));
        }
    }

    /**
     * GET /activate : activate the user.
     *
     * @param key the activation key
     * @return the ResponseEntity with status 200 (OK) and the activated user in body,
     * or status 500 (Internal Server Error) if the user couldn't be activated
     */
    @GetMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestParam(value = "key") String key) {
        log.debug("REST request to active user with key ", key);
        return userService.activateRegistration(key)
                .map(user -> new ResponseEntity<>(HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * GET /authenticate : check if the user is authenticated, and return its logic.
     *
     * @param request the HTTP request
     * @return the email if the user is authenticated
     */
    @GetMapping("/authenticate")
    public String isAuthenticate(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * GET /account : get current user information.
     *
     * @return the ResponseEntity with status 200 (OK) and the current user in body,
     * or status 500 (Internal Server Error) if the user couldn't be returned
     */
    @GetMapping("/account")
    public ResponseEntity<UserDTO> getAccount() {
        return Optional.of(userService.findOneWithAuthorities())
                .map(user -> new ResponseEntity<>(new UserDTO(user.get()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /*public ResponseEntity<String> saveAccount(@Valid @ResponseBody ManagedUserVM managedUserVM) {
        log.debug("REST request to update current users account with" + managedUserVM);
        return userService.findOneByEmail(managedUserVM.getEmail())
                .ifPresent(user -> {
                    return userService.updateUser(managedUserVM);
                }).

    }*/

}
