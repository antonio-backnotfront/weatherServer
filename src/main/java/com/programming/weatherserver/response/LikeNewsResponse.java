package com.programming.weatherserver.response;

import lombok.Getter;

import java.util.Set;

@Getter
public class LikeNewsResponse {
    private Integer likes;
    private Set<String> usersLiked;

    public LikeNewsResponse(Integer likes, Set<String> usersLiked) {
        this.likes = likes;
        this.usersLiked = usersLiked;
    }
}
