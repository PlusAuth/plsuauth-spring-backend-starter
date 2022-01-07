package com.plusauth.starter.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

  @GetMapping
  public String findAll() {
    return "All Users List";
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public String create() {
    return "New User Created";
  }

  @PutMapping
  public String update() {
    return "User Updated";
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping
  public String delete() {
    return "User Deleted";
  }
}