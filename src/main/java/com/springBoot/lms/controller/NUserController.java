package com.springBoot.lms.controller;

import com.springBoot.lms.model.NUser;
import com.springBoot.lms.service.NUserService;
import com.springBoot.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class NUserController {

    @Autowired
    private JwtUtil jwtUtil;

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

    @GetMapping("/get-token")
    public ResponseEntity<?> getToken(Principal principal) {
        try {
            String token =jwtUtil.createToken(principal.getName());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /*
    * AIM : to get details based on logIn User role
    * METHOD : GET
    * Input : Principal interface to get logIn username
    * Response : Based on role of the logzin user
    */

    @GetMapping("/details")
    public Object getDetByUsername(Principal principal){
        String username = principal.getName();

        Object object = nUserService.getUserInfo(username);
        return object;
    }


}
