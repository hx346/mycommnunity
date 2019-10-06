package com.example.mycommunity.modle;

public class User {
    private Integer id;
    private String Name;
    private String accoutid;
    private String token;
    private long gmtcreart;
    private long gmtmodifide;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", accoutid='" + accoutid + '\'' +
                ", token='" + token + '\'' +
                ", gmtcreat=" + gmtcreart +
                ", getmodifide=" + gmtmodifide +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccoutid() {
        return accoutid;
    }

    public void setAccoutid(String accoutid) {
        this.accoutid = accoutid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getGmtcreat() {
        return gmtcreart;
    }

    public void setGmtcreat(long gmtcreat) {
        this.gmtcreart = gmtcreat;
    }

    public long getGetmodifide() {
        return gmtmodifide;
    }

    public void setGetmodifide(long getmodifide) {
        this.gmtmodifide = getmodifide;
    }
}
