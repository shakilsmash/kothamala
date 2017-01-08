package com.ronniegnr.kothamala.web.rest;

import com.ronniegnr.kothamala.domain.User;
import com.ronniegnr.kothamala.service.UserService;
import com.ronniegnr.kothamala.web.rest.util.PaginationUtil;
import com.ronniegnr.kothamala.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<ManagedUserVM>> getAllUsers(Pageable pageable) throws URISyntaxException {
        Page<User> page = userService.findAll(pageable);
        List<ManagedUserVM> managedUserVMs = page.getContent()
                .stream()
                .map(ManagedUserVM::new)
                .collect(Collectors.toList());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeader(page, "/api/users");
        return new ResponseEntity<>(managedUserVMs, headers, HttpStatus.OK);
    }

}