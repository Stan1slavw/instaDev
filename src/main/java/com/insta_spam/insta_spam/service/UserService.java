package com.insta_spam.insta_spam.service;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.insta_spam.insta_spam.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserRepo {
    @Override
    public void login() {
        IGClient client;
        {
            try {
                client = IGClient.builder()
                        .username("jhonforict123")
                        .password("Jhonjhonjhon123")
                        .login();
            } catch (IGLoginException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(client);
    }
}
