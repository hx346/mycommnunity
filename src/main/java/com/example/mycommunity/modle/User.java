package com.example.mycommunity.modle;

public class User {
    private Integer id;
    private String name;
    private String accounId;
    private String token;
    private long gmtCreat;
    private long getModifide;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccounId() {
        return accounId;
    }

    public void setAccounId(String accounId) {
        this.accounId = accounId;
    }



    public long getGmtCreat() {
        return gmtCreat;
    }

    public void setGmtCreat(long gmtCreat) {
        this.gmtCreat = gmtCreat;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGetModifide() {
        return getModifide;
    }

    public void setGetModifide(long getModifide) {
        this.getModifide = getModifide;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounId='" + accounId + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreat=" + gmtCreat +
                ", getGmtCreat=" + getModifide +
                '}';
    }
}
