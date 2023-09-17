package com.insta_spam.insta_spam.repo;

import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;
import com.insta_spam.insta_spam.entity.InstagramUser;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TestUserRepo{
    List<String> getInfoAboutUser(InstagramUser instagramUser);

    CompletableFuture<AccountsUserResponse> setProfilePicture(byte[] photo, InstagramUser instagramUser);
}
