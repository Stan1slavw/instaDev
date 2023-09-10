package com.insta_spam.insta_spam.repo;

import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;

import java.util.concurrent.CompletableFuture;

public interface UserRepo {
    public void login();
    public void uploadPhoto();
    public void getInfoAboutUser();
//    public void sendMessage();
    CompletableFuture<AccountsUserResponse> setProfilePicture(byte[] photo);
}
