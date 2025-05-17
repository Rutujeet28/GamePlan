package com.example.gameplan; // Change the package name if necessary

public class Post {
    private String postId;
    private String userId;
    private String postText;
    private String imageUrl;

    public Post() {
        // Default constructor required for Firebase Realtime Database
    }

    public Post(String postId, String userId, String postText, String imageUrl) {
        this.postId = postId;
        this.userId = userId;
        this.postText = postText;
        this.imageUrl = imageUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
