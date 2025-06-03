package com.springBoot.lms.service;

import org.springframework.stereotype.Service;

import com.springBoot.lms.model.Author;
import com.springBoot.lms.model.NUser;
import com.springBoot.lms.repository.AuthorRepository;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private NUserService userService;

    public AuthorService(AuthorRepository authorRepository, NUserService userService) {
        this.authorRepository = authorRepository;
        this.userService = userService;
    }

    public Author postAuthor(Author author) {
        /** Assign role AUTHOR to this user */
        NUser user = author.getUser();
        user.setRole("AUTHOR");

        /* Fetch User from Author and add to DB */
        user = userService.addUser(author.getUser());

        /* Attach this user to author again */
        author.setUser(user);

        /* Activate Author - later let executive do this.. */
        author.setActive(true);

        /* Save Author in Db */
        return authorRepository.save(author);
    }

}