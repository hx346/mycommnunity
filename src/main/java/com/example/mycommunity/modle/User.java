package com.example.mycommunity.modle;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String Name;
    private String accoutid;
    private String token;
    private long gmtcreat;
    private long gmtmodifide;
    private String avatarurl;

}
