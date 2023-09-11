package com.insta_spam.insta_spam.service;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.users.UserAction;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;
import com.insta_spam.insta_spam.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.requests.accounts.*;

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

    public IGClient getUser(String username, String password) {
        IGClient client;
        {
            try {
                client = IGClient.builder()
                        .username(username)
                        .password(password)
                        .login();
                return client;
            } catch (IGLoginException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void uploadPhoto() {
        IGClient client = getUser("jhonforict123", "Jhonjhonjhon123");
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
        IGClient client = getUser("jhonforict123", "Jhonjhonjhon123");
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


    @Override
    public CompletableFuture<AccountsUserResponse> setProfilePicture(byte[] photo) {
        IGClient client = getUser("jhonforict123", "Jhonjhonjhon123");
        return client.actions().upload()
                .photo(photo, String.valueOf(System.currentTimeMillis()))
                .thenCompose(res -> new AccountsChangeProfilePictureRequest(res.getUpload_id())
                        .execute(client));
    }


    public CompletableFuture<AccountsUserResponse> setProfilePicture() {
         String imagePath = "src/main/resources/static/images/ava.jpg";
        try {
            // Читаем изображение из файла в виде массива байтов
            byte[] photoBytes = Files.readAllBytes(Paths.get(imagePath));

            // Вызываем метод с массивом байтов изображения
            return setProfilePicture(photoBytes);
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки чтения файла
            return CompletableFuture.completedFuture(null); // или другой обработчик ошибки
        }
    }
}
