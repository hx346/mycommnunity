package com.example.mycommunity.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private long id;
    private  String bio;
    private  String login;
    private String avatar_url;

}
