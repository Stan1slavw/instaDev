package com.insta_spam.insta_spam.service;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.users.UserAction;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.insta_spam.insta_spam.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.io.File;

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

    public IGClient getUser() {
        IGClient client;
        {
            try {
                client = IGClient.builder()
                        .username("jhonforict123")
                        .password("Jhonjhonjhon123")
                        .login();
                return client;
            } catch (IGLoginException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void uploadPhoto() {
        IGClient client = getUser();
        client.actions()
                .timeline()
                .uploadPhoto(new File("src/main/resources/static/images/ava.jpg"), "My Caption")
                .thenAccept(response -> {
                    System.out.println("Successfully uploaded photo!");
                })
                .join(); // block current thread until complete
    }

    @Override
    public void getInfoAboutUser() {
        IGClient client = getUser();
        client.actions().users()
                .findByUsername("samsonova.marketing")
                .thenCompose(UserAction::getFriendship)
                .thenAccept(friendship -> {
                    System.out.println(friendship.isFollowing());
                    System.out.println(friendship.is_bestie());
                    System.out.println(friendship.isBlocking());
                    System.out.println(friendship.isFollowed_by());
                })
                .join();

    }


}
