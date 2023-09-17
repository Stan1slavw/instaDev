package com.insta_spam.insta_spam.service;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.users.UserAction;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsChangeProfilePictureRequest;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;
import com.insta_spam.insta_spam.entity.InstagramUser;
import com.insta_spam.insta_spam.repo.TestUserRepo;
import com.insta_spam.insta_spam.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TestUserService implements TestUserRepo {
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
    public List<String> getInfoAboutUser(InstagramUser instagramUser) {
        IGClient client = getUser(instagramUser.getLogin(), instagramUser.getPassword());
        List<String> resultList = new ArrayList<>();
        client.actions().users()
                .findByUsername("samsonova.marketing")
                .thenCompose(UserAction::getFriendship)
                .thenAccept(friendship -> {
                    if (friendship.isFollowing()) {
                        resultList.add("Вы подписаны на пользователя");
                    } else {
                        resultList.add("Вы не подписаны на пользователя");
                    }
                    if (friendship.is_bestie()) {
                        resultList.add("Пользователь находится в списке лучших друзей");
                    } else {
                        resultList.add("Пользователя нет в списке лучших друзей");
                    }
                    if (friendship.isBlocking()) {
                        resultList.add("Пользователь заблокирован");
                    } else {
                        resultList.add("Пользователь не заблокирован");
                    }
                    if (friendship.isFollowed_by()) {
                        resultList.add("Пользователь подписан на вас");
                    } else {
                        resultList.add("Пользователь не подписан на вас");
                    }
                })
                .join();
        return resultList;

    }

    @Override
    public CompletableFuture<AccountsUserResponse> setProfilePicture(byte[] photo, InstagramUser instagramUser) {
        IGClient client = getUser(instagramUser.getLogin(), instagramUser.getPassword());
        return client.actions().upload()
                .photo(photo, String.valueOf(System.currentTimeMillis()))
                .thenCompose(res -> new AccountsChangeProfilePictureRequest(res.getUpload_id())
                        .execute(client));
    }
}
