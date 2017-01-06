package com.ronniegnr.kothamala.web.rest;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.service.UserService;
import com.ronniegnr.kothamala.web.rest.util.HeaderUtil;
import com.ronniegnr.kothamala.web.rest.util.PaginationUtil;
import com.ronniegnr.kothamala.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /users : Creates a new user.
     * <p>
     * Creates a new user if the email is not already used.
     * </p>
     *
     * @param managedUserVM the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with with status 400 (Bad Request) if teh email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody ManagedUserVM managedUserVM) throws URISyntaxException {
        log.debug("REST request to save User : {}", managedUserVM);
        Optional<User> existingUser = userService.findOneByEmail(managedUserVM.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("User", "emailexists", "Email already in use"))
                    .body(null);
        } else {
            User newUser = userService.createUser(managedUserVM);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert("User", newUser.getEmail()))
                    .body(new ManagedUserVM(newUser));
        }
    }

    /**
     * PUT /users : Updates an existing user
     * <p>
     * Updates an existing uer if the email is not already used.
     * </p>
     *
     * @param managedUserVM the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user,
     * or with status 400 (Bad Request) if the email is already in use
     * or with status 500 (Internal Server Error) if the the user coudn't be updated.
     */
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ManagedUserVM managedUserVM) {
        log.debug("REST request to update User : {}", managedUserVM);
        Optional<User> existingUser = userService.findOneByEmail(managedUserVM.getEmail());
        if (existingUser.isPresent() && !(existingUser.get().getId() == managedUserVM.getId())) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert("User", "emailexists", "Email already in use"))
                    .body(null);
        } else {
            userService.updateUser(managedUserVM);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert("User", managedUserVM.getEmail()))
                    .body(new ManagedUserVM(userService.fineOne(managedUserVM
                            .getId())
                            .get()
                    ));
        }
    }

    /**
     * GET /users : get all users.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     * @throws URISyntaxException if the pagination headers couldn't be generated
     */
    @GetMapping("/users")
    public ResponseEntity<List<ManagedUserVM>> getAllUsers(Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get all users");
        Page<User> page = userService.findAll(pageable);
        List<ManagedUserVM> managedUserVMs = page.getContent()
                .stream()
                .map(ManagedUserVM::new)
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeader(page, "/api/users");
        return new ResponseEntity<>(managedUserVMs, headers, HttpStatus.OK);
    }

    /**
     * GET /users/:id : gets the user
     *
     * @param id the id of the user to get
     * @return the ResponseEntity with status 200 (OK)
     * ort with status 404 (NOT_FOUND) if user is not found with given id
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<ManagedUserVM> getUser(@PathVariable long id) {
        log.debug("REST request to get user with id : {}", id);
        return userService.fineOne(id)
                .map(ManagedUserVM::new)
                .map(managedUserVM -> new ResponseEntity(managedUserVM, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE /users/:id : deletes the user
     *
     * @param id the id of the user to delete
     * @return the ResponseEntity with status code 200 (OK)
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        log.debug("REST request to delete user with id : {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert("User", id))
                .body(null);
    }
}