package com.example.mycommunity.provider;

import com.alibaba.fastjson.JSON;
import com.example.mycommunity.dto.AccessTokenDTO;
import com.example.mycommunity.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//Component自动实例化，不用创建新对象，直接使用，ioc功能
@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            // System.out.println("response" + string);
            String token = string.split("&")[0].split("=")[1];
            System.out.println("token=" + token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    public GitHubUser getUser(String accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessTokenDTO)
                .build();
        System.out.println(request+"=request");
        try {
            Response response = client.newCall(request).execute();
            //string接收response
            String string = response.body().string();
            System.out.println(string);
            //解析成json，创建对象
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
