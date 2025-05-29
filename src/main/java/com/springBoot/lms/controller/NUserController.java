package com.springBoot.lms.controller;

import com.springBoot.lms.model.NUser;
import com.springBoot.lms.service.NUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class NUserController {

    @Autowired
    private NUserService nUserService;

    /*
    * AIM : Insert user into DB
    * PATH : /api/user/signup
    * Method : POST
    * Input : NUser as RequestBody
    * Response : Inserted User
    */
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody NUser nUser){
        return ResponseEntity.status(HttpStatus.OK).body(nUserService.addUser(nUser));
    }
}
