package com.cliente.rasmoo.plus.controllers;

import com.cliente.rasmoo.plus.dtos.UserDto;
import com.cliente.rasmoo.plus.models.UserModel;
import com.cliente.rasmoo.plus.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    // ponto de injeção por atributo
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto));
    }

}
